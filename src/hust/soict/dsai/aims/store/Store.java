package hust.soict.dsai.aims.store;

import java.util.ArrayList;

import hust.soict.dsai.aims.media.Media;

public class Store {
	private ArrayList<Media> itemsInStore = new ArrayList<>();

	public Store() {
		this.itemsInStore = new ArrayList<>();
	}

	public ArrayList<Media> getItemsInStore() {
        return itemsInStore;
    }

    public boolean addMedia(Media m){
        return itemsInStore.add(m);
    }

    public boolean removeMedia(Media m) {
        if (itemsInStore.contains(m)) {
            itemsInStore.remove(m);
            return true;
        } else {
            return false;
        }
    }

	public float totalCost() {
		float cost = 0;
		for (Media m: this.itemsInStore) {
			cost += m.getCost();
		}
		return cost;
	}

	public void print() {
		System.out.println("***********************STORE***********************");
		for (Media m: this.itemsInStore) {
			System.out.println(m.toString());
		}
	}


	public Media searchInStore(String title) {
		for (Media m: this.itemsInStore) {
			if (m.isMatch(title)) {
				return m;
			}
		}
		return null;
	}

	public Media getMedia(String title) {
        for (Media m : itemsInStore) {
            if (m.getTitle().equals(title)) {
                return m;
            }
        }
        return null;
    }
}