package assignment.dictionary;

import java.util.Iterator;


/**
 * A class that will be used to display the lines of text that are corrected.
 *
 */

import java.util.ArrayList;

public class LinesToDisplay {

    public static final int LINES = 10; // Display 10 lines
    private ArrayList<Wordlet>[] lines;
    private int currentLine;

    /**
     * Constructor for objects of class LinesToDisplay
     */
    public LinesToDisplay() {
        // Initialize the array of lists and the current line
        lines = new ArrayList[LINES];
        for (int i = 0; i < LINES; i++) {
            lines[i] = new ArrayList<>();
        }
        currentLine = 0;
    }

    /**
     * Add a new wordlet to the current line.
     */
    public void addWordlet(Wordlet w) {
        // Check if the current line is within bounds and add the wordlet
        if (currentLine < LINES) {
            lines[currentLine].add(w);
        }
    }

    /**
     * Go to the next line, if the number of lines has exceeded LINES, shift
     * them all up by one
     */
    public void nextLine() {
        // Increment the current line
        currentLine++;

        // Check if current line exceeds LINES
        if (currentLine >= LINES) {
            // Shift all lines up by one
            for (int i = 1; i < LINES; i++) {
                lines[i - 1] = lines[i];
            }
            // Initialize a new line at the end
            lines[LINES - 1] = new ArrayList<>();
            currentLine = LINES - 1; // Reset the current line to the last line
        }
    }
}

    public int getCurrentLine(){
        return currentLine;
    }
    
    public AList<Wordlet>[] getLines(){
        return lines;
    }
}
