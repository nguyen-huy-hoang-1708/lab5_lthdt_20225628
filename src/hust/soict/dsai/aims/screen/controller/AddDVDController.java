package hust.soict.dsai.aims.screen.controller;

import hust.soict.dsai.aims.media.DigitalVideoDisc;
import hust.soict.dsai.aims.store.Store;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddDVDController {
    @FXML
    private TextField titleField;

    @FXML
    private TextField categoryField;

    @FXML
    private TextField directorField;

    @FXML
    private TextField lengthField;

    @FXML
    private TextField costField;

    private Store store;

    public AddDVDController(Store store) {
        this.store = store;
    }

    @FXML
    private void handleAddDVD() {
        String title = titleField.getText();
        String category = categoryField.getText();
        String director = directorField.getText();
        String lengthText = lengthField.getText();
        String costText = costField.getText();

        if (title.isEmpty() || category.isEmpty() || director.isEmpty() || lengthText.isEmpty() || costText.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please fill in all fields.");
            alert.showAndWait();
            return;
        }

        try {
            int length = Integer.parseInt(lengthText);
            float cost = Float.parseFloat(costText);
            int id = store.getItemsInStore().size();
            DigitalVideoDisc dvd = new DigitalVideoDisc(id, title, category, director, length, cost);
            store.addMedia(dvd);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("DVD added successfully.");
            alert.showAndWait();

            // Close the window after adding the DVD
            Stage stage = (Stage) titleField.getScene().getWindow();
            stage.close();
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Invalid length or cost value.");
            alert.showAndWait();
        }
    }
}