package hust.soict.dsai.aims.screen.controller;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import hust.soict.dsai.aims.media.Book;
import hust.soict.dsai.aims.media.CompactDisc;
import hust.soict.dsai.aims.media.DigitalVideoDisc;
import hust.soict.dsai.aims.media.Media;
import hust.soict.dsai.aims.media.Track;
import hust.soict.dsai.aims.store.Store;


public class StoreScreen extends JFrame {
	private Store store;
    JPanel centerPanel;

    public StoreScreen(Store store) {
        this.store = store;

        Container cp = getContentPane();
        cp.setLayout(new BorderLayout());

        cp.add(createNorth(), BorderLayout.NORTH);
        cp.add(createCenter(), BorderLayout.CENTER);

        setTitle("Store");
        setSize(1024, 768);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    JPanel createNorth() {
        JPanel north = new JPanel();
        north.setLayout(new BoxLayout(north, BoxLayout.Y_AXIS));
        north.add(createMenuBar());
        north.add(createHeader());
        return north;
    }

    JMenuBar createMenuBar() {
        JMenu menu = new JMenu("Options");

        JMenuItem viewStoreMenu = new JMenuItem("View store");
        menu.add(viewStoreMenu);
        viewStoreMenu.addActionListener(new btnMenuListener());

        JMenu smUpdateStore = new JMenu("Update store");
        JMenuItem addBookMenu = new JMenuItem("Add Book");
        JMenuItem addCDMenu = new JMenuItem("Add CD");
        JMenuItem addDVDMenu = new JMenuItem("Add DVD");

        smUpdateStore.add(addBookMenu);
        smUpdateStore.add(addCDMenu);
        smUpdateStore.add(addDVDMenu);
        menu.add(smUpdateStore);

        addBookMenu.addActionListener(new btnMenuListener());
        addDVDMenu.addActionListener(new btnMenuListener());
        addCDMenu.addActionListener(new btnMenuListener());


        JMenuBar menuBar = new JMenuBar();
        menuBar.setLayout(new FlowLayout(FlowLayout.LEFT));
        menuBar.add(menu);

        return menuBar;
    }

    JPanel createHeader() {
        JPanel header = new JPanel();
        header.setLayout(new BoxLayout(header, BoxLayout.X_AXIS));

        JLabel title = new JLabel("AIMS");
        title.setFont(new Font(title.getFont().getName(), Font.PLAIN, 50));
        title.setForeground(Color.CYAN);

        header.add(Box.createRigidArea(new Dimension(10, 10)));
        header.add(title);
        header.add(Box.createHorizontalGlue());
        header.add(Box.createRigidArea(new Dimension(10, 10)));

        return header;
    }

    JPanel createCenter() {

        JPanel center = new JPanel();
        center.setLayout(new GridLayout(3, 3, 2, 2));

        ArrayList<Media> mediaInStore = store.getItemsInStore();
        for (Media element : mediaInStore) {
            MediaStore cell = new MediaStore(element);
            center.add(cell);
        }

        return center;
    }

    private class btnMenuListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();
            if (command.equals("Add DVD")) {
                new AddDigitalVideoDiscToStoreScreen(store);
            } else if (command.equals("Add Book")) {
                new AddBookToStoreScreen(store);
            } else if (command.equals("Add CD")) {
                new AddCompactDiscToStoreScreen(store);
            } else if (command.equals("View Store")) {
                new StoreScreen(store);
            }
            dispose();
        }
    }

    public static void main(String[] args) {
    	Store store = new Store();
    	DigitalVideoDisc dvd1 = new DigitalVideoDisc(1, "The Lion King",
				"Animation", "Roger Allers", 87, 19.95f);
		store.addMedia(dvd1);

		DigitalVideoDisc dvd2 = new DigitalVideoDisc(2, "Star Wars",
				"Science Fiction", "George Lucas", 87, 24.95f);
		store.addMedia(dvd2);

		DigitalVideoDisc dvd3 = new DigitalVideoDisc(3, "Aladin",
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

		List<String> authors3 = new ArrayList<>();
		authors3.add("Tran Viet Trung");
		authors3.add("Dinh Thi Ha Ly");
		Book book3 = new Book(6, "Intro to Deep Learning", "A.I", 30.5f, authors3);

		store.addMedia(dvd1);
		store.addMedia(dvd2);
		store.addMedia(dvd3);
		store.addMedia(book1);
		store.addMedia(book2);
		store.addMedia(book3);
        new StoreScreen(store);
    }
}