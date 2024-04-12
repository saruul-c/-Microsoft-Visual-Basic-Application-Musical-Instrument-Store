package com.example.musicalinstruments;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;



public class MusicalStoreController {
    @FXML
    private TableView<StoreItem> tvStoreItems;
    @FXML
    private TableColumn<StoreItem, String> colCategory;
    @FXML
    private TableColumn<StoreItem, String> colType;
    @FXML
    private TableColumn<StoreItem, String> colItemName;
    @FXML
    private TableColumn<StoreItem, Double> colUnitPrice;
    @FXML
    private ChoiceBox<String> cbxCategories;
    @FXML
    private ComboBox<String> cbxTypes;
    @FXML
    private Button btnSave;

    private ObservableList<StoreItem> items = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        setupTableColumns();
        populateItems();
        populateComboBoxes();
    }

    private void setupTableColumns() {
//        colCategory.setCellValueFactory(new PropertyValueFactory<>("category"));
//        colType.setCellValueFactory(new PropertyValueFactory<>("type"));
//        colItemName.setCellValueFactory(new PropertyValueFactory<>("name"));
//        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
//        tvStoreItems.setItems(items);
    }

    private void populateItems() {
        items.add(new StoreItem("Guitars", "Electric", "Gibson Les Paul Vintage Mahogany Electric Guitar", 745.95, "path/to/image"));
        items.add(new StoreItem("Bass", "Electric 4-String", "Epiphone Thunderbird IV Bass", 325.85, "path/to/image"));
        // Add other items similarly...
    }

    private void populateComboBoxes() {
        // Populate categories and types, ensuring no duplicates
        ObservableList<String> categories = FXCollections.observableArrayList();
        ObservableList<String> types = FXCollections.observableArrayList();
        for (StoreItem item : items) {
            if (!categories.contains(item.getCategory()))
                categories.add(item.getCategory());
            if (!types.contains(item.getType()))
                types.add(item.getType());
        }
//        cbxCategories.setItems(categories);
//        cbxTypes.setItems(types);
    }
    public void doClose(ActionEvent actionEvent) {
        Platform.exit();
    }
        @FXML
        private URL location;

        @FXML
        private Button btnClose;
    private Scene newScene;

    @FXML
    private TableView<StoreItem> itemsTable; // Assuming your data model is StoreItem

    @FXML
        private Button btnCreate;

        @FXML
        private Button btnNewCategory;

        @FXML
        private Button btnNewItemType;

        @FXML
        private HBox pbxPicturePath;

        @FXML
        private TextField txtItemName;

        @FXML
        private TextField txtItemNumber;
    private String selectedCategory;
    private String selectedItemType;

    @FXML
    private ImageView image;

    @FXML
    private Button PictureChoice;



    public void addNewCategory(String newCategory) {
        cbxCategories.getItems().add(newCategory);
    }

    public static void centerStageOnScreen(Stage stage) {
        stage.centerOnScreen();
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
//                cbxTypes.getItems().add(newItemType);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void handleCreateButton(ActionEvent event) throws IOException {
        // ... your code for saving existing choices ...
        Label messageLabel = new Label("Item created successfully!");
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("musicStore.fxml"));
        newScene = new Scene(fxmlLoader.load());
        Stage stage = (Stage) btnCreate.getScene().getWindow();
        stage.centerOnScreen();  // Call the instance method
        stage.setScene(newScene);
        stage.show();
    }
}
