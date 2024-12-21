package hust.soict.dsai.aims.screen.controller;

import java.util.ArrayList;
import java.util.List;

import hust.soict.dsai.aims.cart.Cart;
import hust.soict.dsai.aims.media.*;
import hust.soict.dsai.aims.store.Store;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TestViewStoreScreen extends Application{
    private static Store store;

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/hust/soict/dsai/aims/screen/view/store.fxml"));
        StoreController viewStoreController = new StoreController(new Cart(), store);
        fxmlLoader.setController(viewStoreController);
        Parent root = fxmlLoader.load();

        primaryStage.setTitle("Store");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
    
    public static void initStore(Store store) {
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
		
		CompactDisc cd1 = new CompactDisc(7, "Adele - 30", "Music","Adele", 1500.98f);
        Track track1CD1 = new Track("All Night Parking (interlude)", 161);
        Track track2CD1 = new Track("To Be Loved", 403);
        Track track3CD1 = new Track("Woman Like Me", 300);
        cd1.addTrack(track1CD1);
        cd1.addTrack(track2CD1);
        cd1.addTrack(track3CD1);

        CompactDisc cd2 = new CompactDisc(8, "The Gods We Can Touch", "Music","Aurora", 2000.22f);
        Track track1CD2 = new Track("Everything Matters", 180+34);
        Track track2CD2 = new Track("Blood in the Wine", 180+30);
        Track track3CD2 = new Track("Artemis", 60*2+39);
        cd2.addTrack(track1CD2);
        cd2.addTrack(track2CD2);
        cd2.addTrack(track3CD2);

        CompactDisc cd3 = new CompactDisc(9, "Purpose", "Music","Justin Bieber", 1000.98f);
        Track track1CD3 = new Track("The Feeling", 4*60+5);
        Track track2CD3 = new Track("No Sense", 0);
        cd3.addTrack(track1CD3);
        cd3.addTrack(track2CD3);

        store.addMedia(cd1);
        store.addMedia(cd2);
        store.addMedia(cd3);

    }
    public static void main(String[] args) {
        store = new Store();
        initStore(store);
        launch(args);
    }

}