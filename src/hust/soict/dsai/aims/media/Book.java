package hust.soict.dsai.aims.media;

import java.util.ArrayList;
import java.util.List;

public class Book extends Media {
	private List<String> authors = new ArrayList<>();

	public Book(int id, String title, String category, float cost, List<String> authors) {
		super(id, title, category, cost);
		this.authors = authors;
	}

	public List<String> getAuthors() {
		return this.authors;
	}

	public void setAuthors(List<String> authors) {
		this.authors = authors;
	}

	void addAuthor(String name) {
		boolean exists = false;
		for(String s: this.authors) {
			if (s.equals(name)) {
				exists = true;
				break;
			}
		}
		if (!exists) {
			this.authors.add(name);
		}
	}

	void removeAuthor(String name) {
		boolean exists = false;
		int removeId = -1;
		for (String s: this.authors) {
			if (s.equals(name)) {
				exists = true;
				break;
			}
			removeId++;
		}
		if (exists) {
			this.authors.remove(removeId);
		}
	}

	@Override
	public String toString() {
		int index = 1;
		String res = this.getId() + ". Book: ";
		if (this.getTitle() != null) {
			res += "Title: " + this.getTitle() + "\n";
		}
		if (this.getCategory() != null) {
			res += "Category: " + this.getCategory() + "\n";
		}
		if (this.getAuthors().size() != 0) {
			res += "Authors name: ";
			for (String name: this.authors) {
				res += name;
				if (index < this.authors.size()) {
					index++;
					res += ", ";
				}
			}
			res += "\n";
		}
		if (this.getCost() != 0.0f) {
			res += "Cost: "  + this.getCost();
		}
		return res;
	}

	@Override
	public boolean isMatch(String title) {
		return this.getTitle().equals(title);
	}
}