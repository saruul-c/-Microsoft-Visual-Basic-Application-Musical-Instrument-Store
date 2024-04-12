package com.example.musicalinstruments;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class NewItemDialogController {
    @FXML
    private TextField txtNewItem;

    private MusicalStoreController parentController;
    private Stage stage;

    public void setParentController(MusicalStoreController parentController, Stage stage) {
        this.parentController = parentController;
        this.stage = stage;
    }

    public void addItemType() {
        String newItemType = txtNewItem.getText();
        if (!newItemType.isEmpty()) {
            parentController.addNewItem(newItemType);
            closeDialog();
        }
    }

    public void cancelDialog() {
        closeDialog();
    }

    private void closeDialog() {
        if (stage != null) {
            stage.close();
        }
    }

}
