package hust.soict.dsai.aims.screen.controller;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import hust.soict.dsai.aims.cart.Cart;
import hust.soict.dsai.aims.exception.LimitExceededException;
import hust.soict.dsai.aims.media.Book;
import hust.soict.dsai.aims.media.DigitalVideoDisc;
import hust.soict.dsai.aims.store.Store;
//import hust.soict.dsai.aims.screen.controller.CartScreenController;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;


public class CartScreen extends JFrame {

    private static Cart cart;
    private static Store store;

    public static void main(String[] args) throws LimitExceededException {
    	store = new Store();
    	cart = new Cart();
		DigitalVideoDisc dvd1 = new DigitalVideoDisc("The Lion King",
				"Animation", "Roger Allers", 87, 19.95f);
		store.addMedia(dvd1);

		DigitalVideoDisc dvd2 = new DigitalVideoDisc("Star Wars",
				"Science Fiction", "George Lucas", 87, 24.95f);
		store.addMedia(dvd2);

		DigitalVideoDisc dvd3 = new DigitalVideoDisc("Aladin",
				"Animation", 18.99f);
		store.addMedia(dvd3);

		List<String> authors1 = new ArrayList<>();
		authors1.add("Than Quang Khoat");
		authors1.add("Dinh Viet Sang");
		Book book1 = new Book(4, "Intro to A.I", "Math & Science", 25.5f, authors1);

		List<String> authors2 = new ArrayList<>();
		authors2.add("Trinh Tuan Dat");
		authors2.add("Nguyen Thi Thu Trang");
		Book book2 = new Book(5, "OOP Techniques", "Computer Science", 29.5f, authors2);

		store.addMedia(book1);
		store.addMedia(book2);

		new CartScreen(cart, store);
	}

    public CartScreen(Cart cart, Store store) {

        super();

        CartScreen.cart = cart;

        JFXPanel fxPanel = new JFXPanel();
        this.add(fxPanel);

        this.setTitle("Cart");
        this.setSize(1024, 768);
        this.setVisible(true);
        
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/hust/soict/dsai/aims/screen/view/cart.fxml"));

                    CartScreenController controller = new CartScreenController(cart, store);
                    loader.setController(controller);
                    Parent root = loader.load();
                    fxPanel.setScene(new Scene(root));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }

}