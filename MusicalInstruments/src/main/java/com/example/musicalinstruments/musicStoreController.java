package com.example.musicalinstruments;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.control.cell.PropertyValueFactory;

public class musicStoreController {
    @FXML private TableView<StoreItem> tvStoreItems;
    @FXML private TableColumn<StoreItem, String> colCategory;
    @FXML private TableColumn<StoreItem, String> colType;
    @FXML private TableColumn<StoreItem, String> colItemName;
    @FXML private TableColumn<StoreItem, Double> colUnitPrice;
    @FXML private ComboBox<String> cbxCategories;
    @FXML private ComboBox<String> cbxTypes;
    private Button btnNewStoreItem;

    @FXML
    private HBox btnOpen;

    @FXML
    private Button btnRemove1;

    @FXML
    private Button btnRemove2;

    @FXML
    private Button btnRemove3;

    @FXML
    private Button btnRemove4;

    @FXML
    private Button btnRemove5;

    @FXML
    private Button btnRemove6;

    @FXML
    private Button btnSave;

    @FXML
    private ImageView pbxStoreItem;

    @FXML
    private TextArea txtDescription1;

    @FXML
    private TextArea txtDescription2;

    @FXML
    private TextArea txtDescription3;

    @FXML
    private TextArea txtDescription4;

    @FXML
    private TextArea txtDescription5;

    @FXML
    private TextArea txtDescription6;

    @FXML
    private TextArea txtItemNumber1;

    @FXML
    private TextArea txtItemNumber2;

    @FXML
    private TextArea txtItemNumber3;

    @FXML
    private TextArea txtItemNumber4;

    @FXML
    private TextArea txtItemNumber5;

    @FXML
    private TextArea txtItemNumber6;

    @FXML
    private TextField txtItemsTotal;

    @FXML
    private TextField txtOrderTotal;

    @FXML
    private TextArea txtQuantity1;

    @FXML
    private TextArea txtQuantity2;

    @FXML
    private TextArea txtQuantity3;

    @FXML
    private TextArea txtQuantity4;

    @FXML
    private TextArea txtQuantity5;

    @FXML
    private TextArea txtQuantity6;

    @FXML
    private TextField txtReceiptNumber;

    @FXML
    private TextArea txtSubTotal1;

    @FXML
    private TextArea txtSubTotal2;

    @FXML
    private TextArea txtSubTotal3;

    @FXML
    private TextArea txtSubTotal4;

    @FXML
    private TextArea txtSubTotal5;

    @FXML
    private TextArea txtSubTotal6;

    @FXML
    private TextField txtTaxAmount;

    @FXML
    private TextArea txtUnitPrice1;

    @FXML
    private TextArea txtUnitPrice2;

    @FXML
    private TextArea txtUnitPrice3;

    @FXML
    private TextArea txtUnitPrice4;

    @FXML
    private TextArea txtUnitPrice5;

    @FXML
    private TextArea txtUnitPrice6;
    @FXML
    private ListView<StoreItem> lvwStoreItems; // Assuming ListView for simplicity

    private ObservableList<StoreItem> items = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        setupTableColumns();
        populateItems();
        populateComboBoxes();
    }

    private void setupTableColumns() {
        colCategory.setCellValueFactory(new PropertyValueFactory<>("category"));
        colType.setCellValueFactory(new PropertyValueFactory<>("type"));
        colItemName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        tvStoreItems.setItems(items);
    }

    private void populateItems() {
        items.add(new StoreItem("Guitars", "Electric", "Gibson Les Paul Vintage Mahogany Electric Guitar", 745.95, "path/to/image"));
        items.add(new StoreItem("Bass", "Electric 4-String", "Epiphone Thunderbird IV Bass", 325.85, "path/to/image"));
        items.add(new StoreItem("Keyboards", "Synthesizers", "Alesis QS8.2 88 Key Synthesizer", 825.50, "path/to/image"));
        // Add other items similarly
    }

    private void populateComboBoxes() {
        ObservableList<String> categories = FXCollections.observableArrayList();
        ObservableList<String> types = FXCollections.observableArrayList();
        for (StoreItem item : items) {
            if (!categories.contains(item.getCategory()))
                categories.add(item.getCategory());
            if (!types.contains(item.getType()))
                types.add(item.getType());
        }
        cbxCategories.setItems(categories);
        cbxTypes.setItems(types);
    }
}
