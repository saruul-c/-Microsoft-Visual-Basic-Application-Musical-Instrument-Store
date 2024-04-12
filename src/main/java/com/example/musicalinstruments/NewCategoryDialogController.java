package com.example.musicalinstruments;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class NewCategoryDialogController {
    @FXML
    private TextField txtNewCategory;

    private MusicalStoreController parentController;

    private Stage stage;

    public void setParentController(MusicalStoreController parentController, Stage stage) {
        this.parentController = parentController;
        this.stage = stage;
    }

    public void addCategory() {
        String newCategory = txtNewCategory.getText();
        if (!newCategory.isEmpty()) {
            parentController.addNewCategory(newCategory);
            closeDialog();
        }
    }

    public void cancelDialog() {
        closeDialog();
    }

    private void closeDialog() {
        stage.close();
    }

}
