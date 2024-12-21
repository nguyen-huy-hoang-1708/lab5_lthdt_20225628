package hust.soict.dsai.aims.media;

import java.util.Comparator;

import hust.soict.dsai.aims.exception.PlayerException;
import hust.soict.dsai.aims.media.comparator.MediaComparatorByCostTitle;
import hust.soict.dsai.aims.media.comparator.MediaComparatorByTitleCost;


public abstract class Media {
	private int id;
	private String title;
	private String category;
	private float cost;

	public static final Comparator<Media> COMPARE_BY_TITLE_COST =
			new MediaComparatorByTitleCost();
	public static final Comparator<Media> COMPARE_BY_COST_TITLE =
			new MediaComparatorByCostTitle();


	public Media() {

	}

	public Media(int id, String title, float cost) {
		this(id, title, null, cost);
	}

	public Media(int id, String title) {
		this(id, title, null, 0);
	}

	public Media(int id, String title, String category, float cost) {
		this.id = id;
		this.title = title;
		this.category = category;
		this.cost = cost;
	}

	public int getId() {
		return id;
	}
	public String getTitle() {
		return title;
	}
	public String getCategory() {
		return category;
	}
	public float getCost() {
		return cost;
	}
	public void setTitle(String title) {
		this.title = title;
	}

	public String playGUI() throws PlayerException {
        return "Playing media";
    }
	public boolean equals(Media m) {
		try {
			if (m == this) {
	            return true;
	        }
	        if (!(m instanceof Media)) {
	            return false;
	        }
	        Media mTemp = m;
	        return this.title.equals(mTemp.getTitle());
		} catch(NullPointerException e) {
			return false;
		} catch (ClassCastException e1) {
			return false;
		}
	}

	@Override
	public String toString() {
		String res = this.getId() + ". Media: ";
		if (this.getTitle() != null) {
			res += "Title: " + this.getTitle() + "\n";
		}
		if (this.getCategory() != null) {
			res += "Category: " + this.getCategory() + "\n";
		}
		if (this.getCost() != 0.0f) {
			res = "Cost: " + res + this.getCost();
		}
		return res;
	}
	public abstract boolean isMatch(String title);
}