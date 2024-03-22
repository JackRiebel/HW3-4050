module org.example.group20_cs4050_7050_assignment3 {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.group20_cs4050_7050_assignment3 to javafx.fxml;
    exports org.example.group20_cs4050_7050_assignment3;
}