module com.example.musicalinstruments {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.musicalinstruments to javafx.fxml;
    exports com.example.musicalinstruments;
}