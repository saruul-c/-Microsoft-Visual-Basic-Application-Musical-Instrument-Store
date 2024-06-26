package com.example.musicalinstruments;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import static javafx.application.Application.launch;

public class MusicStore extends Application{
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MusicalStore.class.getResource("musicStore.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1110, 610);
        stage.centerOnScreen();
        stage.setTitle("Item Editor");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
