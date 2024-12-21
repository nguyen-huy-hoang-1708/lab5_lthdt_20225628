package hust.soict.dsai.aims.screen.controller;
import java.io.IOException;

import hust.soict.dsai.aims.cart.Cart;
import hust.soict.dsai.aims.media.Media;
import hust.soict.dsai.aims.store.Store;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


public class StoreController {


    @FXML
    private GridPane gridPane;

    @FXML
    void btnViewCartPressed(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/hust/soict/dsai/aims/screen/view/cart.fxml"));
            fxmlLoader.setController(new CartScreenController(cart, store));
            Parent root = fxmlLoader.load();

            Stage nextStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            nextStage.setScene(new Scene(root));
            nextStage.setTitle("Cart");
            nextStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Store store;
    private Cart cart;
    
    public StoreController(Cart cart, Store store) {
        this.store = store;
        this.cart = cart;
    }

    @FXML
    public void initialize() {

        int column = 0;
        int row = 1;
        for (Media element : store.getItemsInStore()) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/hust/soict/dsai/aims/screen/view/item.fxml"));
                ItemController itemController = new ItemController(cart);
                loader.setController(itemController);
                AnchorPane anchorPane = new AnchorPane();
                anchorPane = loader.load();
                itemController.setData(element);

                if (column == 3) {
                    column = 0; row++;
                }
                gridPane.add(anchorPane, column++, row);
                GridPane.setMargin(anchorPane, new Insets(20, 10, 10, 10));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}