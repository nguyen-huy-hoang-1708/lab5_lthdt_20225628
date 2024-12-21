package hust.soict.dsai.aims.cart;

import java.util.Collections;

import hust.soict.dsai.aims.exception.LimitExceededException;
import hust.soict.dsai.aims.media.DigitalVideoDisc;
import hust.soict.dsai.aims.media.Media;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class Cart {
	public static final int MAX_NUMBERS_ORDERED = 20;
	private ObservableList<Media> itemsOrdered = FXCollections.observableArrayList();
    public ObservableList<Media> getItemsOrdered() {
		return itemsOrdered;
	}
	public Cart() {
        itemsOrdered = FXCollections.observableArrayList();
    }


	public void addDigitalVideoDisc(DigitalVideoDisc disc) {
        if(itemsOrdered.size() > MAX_NUMBERS_ORDERED) {
            System.out.println("The cart is almost full!");
            return;
        } else {
            itemsOrdered.add(disc);
            System.out.println("The disc has been added to the cart!");
        }
        return;
    }

	public int addMedia(Media m) throws LimitExceededException {
        if (itemsOrdered.size() < MAX_NUMBERS_ORDERED) {
        	itemsOrdered.add(m);
            return 1;
        } else {
            throw new LimitExceededException("The cart is almost full");
        }
    }

	public boolean removeMedia(Media m) {
        if (itemsOrdered.contains(m)) {
            itemsOrdered.remove(m);
            System.out.println("Media removed successfully");
            return true;
        } else {
            System.out.println("This media has not been added to the cart");
            return false;
        }
    }

	public int numberDVDsInCart() {
		int count = 0;
		for (Media m: itemsOrdered) {
			if (m instanceof DigitalVideoDisc) {
				count++;
			}
		}
		return count;
	}

	public void displayItemsOrdered() {
		for (Media item: this.itemsOrdered) {
			System.out.print(item.getTitle() + ", ");
		}
		System.out.println();
	}
	public float totalCost() {
		float cost = 0;
		for (Media item: this.itemsOrdered) {
			cost += item.getCost();
		}
		return cost;
	}

	public void displayCart() {
		System.out.println("***********************CART***********************");
		System.out.println("Ordered Items:");
		for (Media m: this.itemsOrdered) {
			System.out.println(m.toString());
		}
		System.out.println("Total cost: " + this.totalCost());
		System.out.println("**************************************************");
	}

	public Media searchInCart(String title) {
		for (Media m: itemsOrdered) {
			if (m.isMatch(title)) {
				return m;
			}
		}
		return null;
	}

	public Media searchInCart(int id) {
		for (Media m: itemsOrdered) {
			if (m.getId() == id) {
				return m;
			}
		}
		return null;
	}

	public String placeOrder() {
        if (itemsOrdered.size() == 0) {
            return "Your cart is empty!";
        } else {
            itemsOrdered.clear();
            return "Order created!\n" + "Now your cart will be empty!";
        }
    }

	public void sortCartByTitle() {
		Collections.sort(itemsOrdered, Media.COMPARE_BY_TITLE_COST);
	}
	public void sortCartByCost() {
		Collections.sort(itemsOrdered, Media.COMPARE_BY_COST_TITLE);
	}
}