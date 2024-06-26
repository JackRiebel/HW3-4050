package assignment.dictionary;

/*

 */

//
import java.util.*;
import java.io.*;
import java.util.Dictionary;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.BiFunction;

/**

 */
import java.util.LinkedList;

public class MyHashTable<K, V> {
    private class HashNode<K, V> {
        K key;
        V value;
        HashNode<K, V> next;

        public HashNode(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private LinkedList<HashNode<K, V>>[] bucketArray;
    private int numBuckets;
    private int size;

    public MyHashTable() {
        bucketArray = new LinkedList[10];
        numBuckets = 10;
        size = 0;

        for (int i = 0; i < numBuckets; i++)
            bucketArray[i] = new LinkedList<>();
    }
    private int getBucketIndex(K key) {
        return key == null ? 0 : customHashCode(key) % numBuckets;
    }

    private int customHashCode(K key) {
        String keyAsString = key.toString();
        int hash = 0;
        for (int i = 0; i < keyAsString.length(); i++) {
            hash = 31 * hash + keyAsString.charAt(i);
        }
        // Ensure the hash code is non-negative
        return hash & 0x7fffffff;
    }


    public int size() { return size; }
    public boolean isEmpty() { return size() == 0; }

    private int getBucketIndex(K key) {
        int hashCode = key.hashCode();
        int index = hashCode % numBuckets;
        return index;
    }

    public V get(K key) {
        int bucketIndex = getBucketIndex(key);
        LinkedList<HashNode<K, V>> chain = bucketArray[bucketIndex];

        for (HashNode<K, V> node : chain)
            if (node.key.equals(key))
                return node.value;

        return null;
    }

    public void add(K key, V value) {
        int bucketIndex = getBucketIndex(key);
        LinkedList<HashNode<K, V>> chain = bucketArray[bucketIndex];

        for (HashNode<K, V> node : chain) {
            if (node.key.equals(key)) {
                node.value = value;
                return;
            }
        }

        size++;
        chain.add(new HashNode<>(key, value));

        if ((1.0 * size) / numBuckets >= 0.7) {
            rehash();
        }
    }

    public V remove(K key) {
        int bucketIndex = getBucketIndex(key);
        HashNode<K, V> prev = null;
        LinkedList<HashNode<K, V>> chain = bucketArray[bucketIndex];

        for (HashNode<K, V> node : chain) {
            if (node.key.equals(key)) {
                size--;
                if (prev != null)
                    prev.next = node.next;
                else
                    chain.remove(node);
                return node.value;
            }
            prev = node;
        }

        return null;
    }

    private void rehash() {
        LinkedList<HashNode<K, V>>[] temp = bucketArray;
        bucketArray = new LinkedList[numBuckets * 2];
        numBuckets = 2 * numBuckets;
        size = 0;
        for (int i = 0; i < numBuckets; i++) {
            bucketArray[i] = new LinkedList<>();
        }

        for (LinkedList<HashNode<K, V>> bucket : temp) {
            for (HashNode<K, V> node : bucket) {
                add(node.key, node.value);
            }
        }
    }
}
    public void clear() {
        bucketArray = new LinkedList[numBuckets]; // Reinitialize the array of linked lists
        size = 0;
        for (int i = 0; i < numBuckets; i++) {
            bucketArray[i] = new LinkedList<>(); // Initialize each bucket with a new linked list
        }
    }

    public List<V> values() {
        List<V> valuesList = new LinkedList<>();
        for (LinkedList<HashNode<K, V>> bucket : bucketArray) {
            for (HashNode<K, V> entry : bucket) {
                valuesList.add(entry.value);
            }
        }
        return valuesList;
    }

    public Set<K> keySet() {
        Set<K> keySet = new HashSet<>();
        for (LinkedList<HashNode<K, V>> bucket : bucketArray) {
            for (HashNode<K, V> entry : bucket) {
                keySet.add(entry.key);
            }
        }
        return keySet;
    }

    public boolean containsKey(K key) {
        int bucketIndex = getBucketIndex(key);
        LinkedList<HashNode<K, V>> chain = bucketArray[bucketIndex];
        for (HashNode<K, V> entry : chain) {
            if (entry.key.equals(key)) {
                return true;
            }
        }
        return false;
    }
}



