package com.example.musicalinstruments;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MusicalStore extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader musicalStoreLoader = new FXMLLoader(getClass().getResource("musicalstore.fxml"));
        Scene scene = new Scene(musicalStoreLoader.load(), 500, 450);
        MusicalStoreController musicalStoreController = musicalStoreLoader.getController();
        FXMLLoader fullStoreLoader = new FXMLLoader(getClass().getResource("fullstore.fxml"));
        fullStoreLoader.load();
        FullStoreController fullStoreController = fullStoreLoader.getController();
        musicalStoreController.setFullStoreController(fullStoreController);

        stage.setTitle("Item Editor");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
