package hust.soict.dsai.aims.screen.controller;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;

import hust.soict.dsai.aims.store.Store;

public abstract class AddItemToStoreScreen extends JFrame {
    Store store;
    JPanel centerPanel;
    JButton btnAdd;

    ArrayList<JTextField> tfs = new ArrayList<>();

    public AddItemToStoreScreen(Store store) {
        this.store = store;

        setSize(1024, 768);
        setLayout(new BorderLayout());

        add(createMenuBar(), BorderLayout.NORTH);

        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        btnAdd = new JButton("Add");
        btnPanel.add(btnAdd);
        btnPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        add(btnPanel, BorderLayout.SOUTH);

        centerPanel = createCenter();
        add(centerPanel, BorderLayout.CENTER);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    JPanel createSubPanel(String text) {
        JPanel subPanel = new JPanel(new FlowLayout());
        JTextField tf = new JTextField(50);
        tf.setName(text);
        tfs.add(tf);
        JLabel lbTextField = new JLabel(text);

        subPanel.add(lbTextField);
        subPanel.add(tf);
        subPanel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);

        return subPanel;
    }

    JPanel createCenter() {
        JPanel centerPanel = new JPanel(new GridLayout(7, 1, 4, 4));

        JPanel titlePanel = createSubPanel("Title");
        JPanel categoryPanel = createSubPanel("Category");
        JPanel costPanel = createSubPanel("Cost");

        centerPanel.add(titlePanel);
        centerPanel.add(categoryPanel);
        centerPanel.add(costPanel);

        return centerPanel;
    }

    JMenuBar createMenuBar() {
        JMenu menu = new JMenu("Options");

        JMenuItem viewStoreMenu = new JMenuItem("View store");
        menu.add(viewStoreMenu);
        viewStoreMenu.addActionListener(new btnMenuListener());

        JMenuBar menuBar = new JMenuBar();
        menuBar.setLayout(new FlowLayout(FlowLayout.LEFT));
        menuBar.add(menu);

        return menuBar;
    }

    private class btnMenuListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            new StoreScreen(store);
            dispose();
        }
    }

}