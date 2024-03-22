module com.example.dictionary {
    requires javafx.controls;
    requires javafx.fxml;


    opens main.java.assignment.dictionary to javafx.fxml;
    exports main.java.assignment.dictionary;
}