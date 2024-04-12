module com.example.musicalinstruments {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.musicalinstruments to javafx.fxml;
    exports com.example.musicalinstruments;
}