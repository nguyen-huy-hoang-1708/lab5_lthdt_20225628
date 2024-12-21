package hust.soict.dsai.aims.screen.controller;

import java.io.IOException;

import hust.soict.dsai.aims.cart.Cart;
import hust.soict.dsai.aims.exception.PlayerException;
import hust.soict.dsai.aims.media.Media;
import hust.soict.dsai.aims.media.Playable;
import hust.soict.dsai.aims.store.Store;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class CartScreenController {
    private Cart cart;
    private Store store;

    @FXML
    private TableColumn<Media, Float> colMediaCost;

    @FXML
    private TableColumn<Media, String> colMediaTitle;

    @FXML
    private TableColumn<Media, String> colMediaCategory;

    @FXML
    private TableView<Media> tblMedia;

    @FXML
    private Button btnPlay;

    @FXML
    private Button btnRemove;

    @FXML
    private Label costLabel;

    @FXML
    private ToggleGroup filterCategory;

    @FXML
    private RadioButton radioBtnFilterId;

    @FXML
    private RadioButton radioBtnFilterTitle;

    @FXML
    private TextField tfFilter;

    @FXML
    private Button placeOrder;

    @FXML
    MenuBar menuBar;
    @FXML
    void btnPlayPressed(ActionEvent event) {
    	try {
    		Media media = tblMedia.getSelectionModel().getSelectedItem();
        	String outputMessage = ((Playable) media).play();
            Alert alert = new Alert(Alert.AlertType.INFORMATION, outputMessage);
            alert.showAndWait();
    	}
    	catch (PlayerException e) {
    		e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
            alert.showAndWait();
    	}
    }

    @FXML
    void placeOrderPressed(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, cart.placeOrder());
        alert.setTitle("Order created");
        alert.setHeaderText(null);
        alert.showAndWait();
    }

    @FXML
    void btnRemovePressed(ActionEvent event) {
        Media media = tblMedia.getSelectionModel().getSelectedItem();
        cart.removeMedia(media);
        costLabel.setText(cart.totalCost() + " $");
    }

    @FXML
    void btnViewStorePressed(ActionEvent event) {
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/hust/soict/dsai/aims/screen/view/store.fxml"));
            loader.setController(new StoreController(cart, store));
            Parent root = loader.load();
            Stage stage = (Stage)costLabel.getScene().getWindow();
            //Stage stage = (Stage)menuBar.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Store");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void addBookPressed(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/hust/soict/dsai/aims/screen/view/addbook.fxml"));
            loader.setController(new AddBookController(store));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Add Book");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    void addCDPressed(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/hust/soict/dsai/aims/screen/view/addcd.fxml"));
            loader.setController(new AddCompactDiscController(store));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Add CD");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    void addDVDPressed(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/hust/soict/dsai/aims/screen/view/adddvd.fxml"));
            loader.setController(new AddDVDController(store));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Add DVD");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public CartScreenController(Cart cart, Store store) {
        super();
        this.cart = cart;
        this.store = store;
    }

    @FXML
    void initialize() {

        colMediaTitle.setCellValueFactory(
            new PropertyValueFactory<>("title")
        );
        colMediaCategory.setCellValueFactory(
            new PropertyValueFactory<>("category")
        );
        colMediaCost.setCellValueFactory(
            new PropertyValueFactory<>("cost")
        );
        tblMedia.setItems(cart.getItemsOrdered());
        costLabel.setText(cart.totalCost() + "$");

        btnPlay.setVisible(false);
        btnRemove.setVisible(false);

        tblMedia.getSelectionModel().selectedItemProperty().addListener(
            new ChangeListener<Media>() {

                @Override
                public void changed(ObservableValue<? extends Media> observable, Media oldValue, Media newValue) {
                    if (newValue != null) {
                        updateButtonBar(newValue);
                    }
                }

                private void updateButtonBar(Media media) {
                    btnRemove.setVisible(true);
                    if (media instanceof Playable) {
                        btnPlay.setVisible(true);
                    } else {
                        btnPlay.setVisible(false);
                    }
                }
            }
        );

        tfFilter.textProperty().addListener(
                new ChangeListener<String>() {

                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                    showFilteredMedia(newValue);
                }

                private void showFilteredMedia(String keyword) {
                    FilteredList<Media> filteredList = new FilteredList<>(cart.getItemsOrdered());

                    if (!keyword.isEmpty() && radioBtnFilterId.isSelected()) {
                        filteredList.setPredicate(media -> {
                            String idString = String.valueOf(media.getId());
                            return idString.equals(keyword);
                        });
                    } else if (!keyword.isEmpty() &&  radioBtnFilterTitle.isSelected()) {
                        filteredList.setPredicate(media -> {
                            String title = media.getTitle().toLowerCase();
                            return title.contains(keyword.toLowerCase());
                        });
                    } else {
                        filteredList.setPredicate(null);
                    }
                    tblMedia.setItems(filteredList);
                }
            });
    }
}