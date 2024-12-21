package hust.soict.dsai.aims.screen.controller;

import hust.soict.dsai.aims.media.CompactDisc;
import hust.soict.dsai.aims.store.Store;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddCompactDiscController {
    @FXML
    private TextField titleField;

    @FXML
    private TextField categoryField;

    @FXML
    private TextField artistField;

    @FXML
    private TextField directorField;

    @FXML
    private TextField costField;

    private Store store;

    public AddCompactDiscController(Store store) {
        this.store = store;
    }

    @FXML
    private void handleAddCD() {
        String title = titleField.getText();
        String category = categoryField.getText();
        String artist = artistField.getText();
        String director = directorField.getText();
        String costText = costField.getText();

        if (title.isEmpty() || category.isEmpty() || artist.isEmpty() || costText.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please fill in all fields.");
            alert.showAndWait();
            return;
        }

        try {
            float cost = Float.parseFloat(costText);
            CompactDisc cd;
            int id = store.getItemsInStore().size();
            if (director.isEmpty()) {
                cd = new CompactDisc(id, title, category, artist, cost);
            } else {
                cd = new CompactDisc(id, title, category, artist, director, cost);
            }
            store.addMedia(cd);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("CD added successfully.");
            alert.showAndWait();

            // Close the window after adding the CD
            Stage stage = (Stage) titleField.getScene().getWindow();
            stage.close();
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Invalid cost value.");
            alert.showAndWait();
        }
    }
}