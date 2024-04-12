package com.example.musicalinstruments;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MusicalStoreController {

    public void doClose(ActionEvent actionEvent) {
        Platform.exit();
    }
        @FXML
        private URL location;

        @FXML
        private Button btnClose;

        @FXML
        private Button btnCreate;

        @FXML
        private Button btnNewCategory;

        @FXML
        private Button btnNewItemType;

        @FXML
        private ChoiceBox<String> cbxCategories;

        @FXML
        private ChoiceBox<String> cbxItemTypes;

        @FXML
        private HBox pbxPicturePath;

        @FXML
        private TextField txtItemName;

        @FXML
        private TextField txtItemNumber;
    @FXML
    private ImageView image;

    @FXML
    private Button PictureChoice;



        @FXML
        void initialize() {

        }

    public void addNewCategory(String newCategory) {
        cbxCategories.getItems().add(newCategory);
    }


    @FXML
    private void handlePictureChoice(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose Image File");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif")
        );

        File selectedFile = fileChooser.showOpenDialog(((Button) event.getSource()).getScene().getWindow());
        if (selectedFile != null) {
            Image selectedImage = new Image(selectedFile.toURI().toString());
            image.setImage(selectedImage);
        }
    }
    @FXML
    private void openNewCategoryDialog() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("NewCategoryDialog.fxml"));
            VBox dialogRoot = fxmlLoader.load();
            Stage dialogStage = new Stage();
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(cbxCategories.getScene().getWindow());
            dialogStage.setScene(new Scene(dialogRoot));
            dialogStage.setTitle("Add New Category");
            dialogStage.show();
            NewCategoryDialogController dialogController = fxmlLoader.getController();
            dialogController.setParentController(this, dialogStage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void openNewItemDialog() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("NewItemDialog.fxml"));
            VBox dialogRoot = fxmlLoader.load();
            Stage dialogStage = new Stage();
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(btnNewItemType.getScene().getWindow());
            NewItemDialogController dialogController = fxmlLoader.getController();
            dialogController.setParentController(this, dialogStage);

            dialogStage.setScene(new Scene(dialogRoot));
            dialogStage.setTitle("Add New Item Type");
            dialogStage.showAndWait();

            String newItemType = dialogController.getNewItem();
            if (newItemType != null && !newItemType.isEmpty()) {
                cbxItemTypes.getItems().add(newItemType);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




}
