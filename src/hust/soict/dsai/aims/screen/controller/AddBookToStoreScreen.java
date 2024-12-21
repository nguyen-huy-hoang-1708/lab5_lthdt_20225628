package hust.soict.dsai.aims.screen.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTextField;

import hust.soict.dsai.aims.media.Book;
import hust.soict.dsai.aims.store.Store;

public class AddBookToStoreScreen extends AddItemToStoreScreen {

    public AddBookToStoreScreen(Store store) {
        super(store);

        btnAdd.addActionListener(new btnAddListener());
        super.setTitle("Add Book");
    }

    private class btnAddListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String title = null, category = null, author = null;
            int id = 0;
            List<String> authors = new ArrayList<>();
            float cost=0;
            for (JTextField tf : tfs) {
            	if (tf.getName().equals("Id")) {
            		id = Integer.parseInt(tf.getText());
            	}
                if (tf.getName().equals("Title")) {
                    title = tf.getText();
                }
                if (tf.getName().equals("Author")) {
                    author = tf.getText();
                    authors.add(author);
                }
                if (tf.getName().equals("Category")) {
                    category = tf.getText();
                }
                if (tf.getName().equals("Cost")) {
                    cost = Float.parseFloat(tf.getText());
                }
            }

            Book b =  new Book(id, title, category, cost, authors);
            store.addMedia(b);
        }
    }
}