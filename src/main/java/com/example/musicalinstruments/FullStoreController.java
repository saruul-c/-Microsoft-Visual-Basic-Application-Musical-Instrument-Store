package com.example.musicalinstruments;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FullStoreController {

    @FXML
    private Button createButton;

    @FXML
    private TableView<Item> tableView;

    @FXML
    private ChoiceBox<String> categoryChoiceBox;

    @FXML
    private ChoiceBox<String> itemTypeChoiceBox;

    @FXML
    private ImageView set_image;

    private Connection connection;
    @FXML
    private TableColumn<Item, String> itemNumberColumn;
    @FXML
    private TableColumn<Item, String> itemNameColumn;
    @FXML
    private TableColumn<Item, Double> priceColumn;


    @FXML
    public void initialize() {
        tableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                updateImage(newValue.getItemNumber());
            }
        });
        categoryChoiceBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            handleChoiceBoxSelection();
        });

        itemTypeChoiceBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            handleChoiceBoxSelection();
        });
        try {
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/items", "postgres", "123");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        populateChoiceBoxes();
        itemNumberColumn.setCellValueFactory(new PropertyValueFactory<>("itemNumber"));
        itemNameColumn.setCellValueFactory(new PropertyValueFactory<>("itemName"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
    }
    private void updateImage(String itemNumber) {
        try {
            String geturl = "SELECT img_src FROM items WHERE item_number = ?";
            PreparedStatement imgStatement = connection.prepareStatement(geturl);
            imgStatement.setString(1, itemNumber);
            ResultSet imgResultSet = imgStatement.executeQuery();
            if (imgResultSet.next()) {
                String imagePath = imgResultSet.getString("img_src");
                String imageUrl = "file:///" + imagePath.replace("\\", "/");
                System.out.println("Image URL: " + imageUrl);
                set_image.setImage(new Image(imageUrl));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }






    private void populateChoiceBoxes() {
        try {
            String categoryQuery = "SELECT DISTINCT category FROM items";
            PreparedStatement categoryStatement = connection.prepareStatement(categoryQuery);
            ResultSet categoryResultSet = categoryStatement.executeQuery();
            ObservableList<String> categories = FXCollections.observableArrayList();
            while (categoryResultSet.next()) {
                String category = categoryResultSet.getString("category");
                categories.add(category);
            }
            categoryChoiceBox.setItems(categories);

            String itemTypeQuery = "SELECT DISTINCT item_type FROM items";
            PreparedStatement itemTypeStatement = connection.prepareStatement(itemTypeQuery);
            ResultSet itemTypeResultSet = itemTypeStatement.executeQuery();
            ObservableList<String> itemTypes = FXCollections.observableArrayList();
            while (itemTypeResultSet.next()) {
                String itemType = itemTypeResultSet.getString("item_type");
                itemTypes.add(itemType);
            }
            itemTypeChoiceBox.setItems(itemTypes);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    @FXML
    private void handleChoiceBoxSelection() {
        String selectedCategory = categoryChoiceBox.getValue();
        String selectedItemType = itemTypeChoiceBox.getValue();

        if (selectedCategory != null && selectedItemType != null) {
            try {
                String query = "SELECT * FROM items WHERE category = ? AND item_type = ?";
                PreparedStatement statement = connection.prepareStatement(query);
                statement.setString(1, selectedCategory);
                statement.setString(2, selectedItemType);
                ResultSet resultSet = statement.executeQuery();

                ObservableList<Item> items = FXCollections.observableArrayList();
                while (resultSet.next()) {
                    String itemNumber = resultSet.getString("item_number");
                    String itemName = resultSet.getString("item_name");
                    double price = resultSet.getDouble("price");
                    items.add(new Item(itemNumber, itemName, price,""));
                }
                tableView.getItems().clear();

                tableView.setItems(items);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }



    @FXML
    public void Show_Dialog() {
        try {
            FXMLLoader musicalStoreLoader = new FXMLLoader(getClass().getResource("musicalstore.fxml"));
            Scene scene = new Scene(musicalStoreLoader.load(), 500, 450);
            MusicalStoreController musicalStoreController = musicalStoreLoader.getController();
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Musical Store");
            dialogStage.setScene(scene);
            dialogStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
