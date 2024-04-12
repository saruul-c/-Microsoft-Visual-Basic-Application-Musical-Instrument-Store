package com.example.musicalinstruments;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
        private TextField txtPrice;
        @FXML
        private  TextField img_url;
    @FXML
    private ImageView image;

    @FXML
    private Button PictureChoice;

    private FullStoreController fullStoreController;

        @FXML
        void initialize() {

        }
    public void setFullStoreController(FullStoreController fullStoreController) {
        this.fullStoreController = fullStoreController;
    }
    public void addNewCategory(String newCategory) {
        cbxCategories.getItems().add(newCategory);
    }
    public void addNewItem(String newCategory) {
        cbxItemTypes.getItems().add(newCategory);
    }



    @FXML
    private void handleCreateButtonAction() {
        String itemNumber = txtItemNumber.getText();
        String category = cbxCategories.getValue();
        String itemType = cbxItemTypes.getValue();
        String itemName = txtItemName.getText();
        String priceText = txtPrice.getText();
        String imgSrc = img_url.getText();
        double price = Double.parseDouble(priceText);
        Task<Void> saveTask = new Task<>() {
            @Override
            protected Void call() throws Exception {
                try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/items", "postgres", "123")) {
                    String sql = "INSERT INTO items (item_number, category, item_type, item_name, price, img_src) VALUES (?, ?, ?, ?, ?, ?)";
                    try (PreparedStatement statement = connection.prepareStatement(sql)) {
                        statement.setString(1, itemNumber);
                        statement.setString(2, category);
                        statement.setString(3, itemType);
                        statement.setString(4, itemName);
                        statement.setDouble(5, price);
                        statement.setString(6, imgSrc);
                        statement.executeUpdate();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                    Platform.runLater(() -> {
                        Alert alert = new Alert(Alert.AlertType.ERROR, "Error occurred while saving item to database.", ButtonType.OK);
                        alert.showAndWait();
                    });
                }
                return null;
            }
        };

        // Set up task completion handler
        saveTask.setOnSucceeded(event -> {
            // Show success message to the user
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Item saved successfully.", ButtonType.OK);
            alert.showAndWait();
        });

        // Start the background task
        Thread saveThread = new Thread(saveTask);
        saveThread.start();
    }

    @FXML
    private void handlePictureChoice(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose Image File");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home") + "/Downloads"));
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif")
        );

        File selectedFile = fileChooser.showOpenDialog(((Button) event.getSource()).getScene().getWindow());
        if (selectedFile != null) {
            String selectedFilePath = selectedFile.toURI().toString();
            // Check if the URL protocol is valid
            if (selectedFilePath.startsWith("file:/")) {
                selectedFilePath = selectedFilePath.substring(6);
            }
            // Create a file object from the path
            File file = new File(selectedFilePath);
            // Get the absolute path
            String absolutePath = file.getAbsolutePath();
            // Create the image using the absolute path
            Image selectedImage = new Image(selectedFile.toURI().toString());
            img_url.setText(absolutePath);
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
            dialogStage.initOwner(btnNewCategory.getScene().getWindow());
            NewCategoryDialogController dialogController = fxmlLoader.getController();
            dialogController.setParentController(this, dialogStage);
            dialogStage.setScene(new Scene(dialogRoot));
            dialogStage.setTitle("Add New Category");
            dialogStage.showAndWait();

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


        } catch (IOException e) {
            e.printStackTrace();
        }
    }




}
