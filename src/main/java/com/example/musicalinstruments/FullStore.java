package com.example.musicalinstruments;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class FullStore extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MusicalStore.class.getResource("FullStore.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 750, 510);
        stage.setTitle("Musical Instrument Store");

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
