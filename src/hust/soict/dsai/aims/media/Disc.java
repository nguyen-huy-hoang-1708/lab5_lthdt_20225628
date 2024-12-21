package hust.soict.dsai.aims.media;

public class Disc extends Media {
	private int length;
	private String director;

	public Disc() {
		super();
	}

	public Disc(int id, String title, String category, float cost) {
		this(id, title, category, null, 0, cost);
	}

	public Disc(int id, String title, String category, String director, float cost) {
		this(id, title, category, director, 0, cost);
	}

	public Disc(int id, String title, String category, String director, int length, float cost) {
		super(id, title, category, cost);
		this.length = length;
		this.director = director;
	}

	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	public String getDirector() {
		return director;
	}
	public void setDirector(String director) {
		this.director = director;
	}

	@Override
	public String toString() {
		String res = this.getId() + ". Disc: \n";
		if (this.getTitle() != null) {
			res += "Title: " + this.getTitle() + "\n";
		}
		if (this.getCategory() != null) {
			res += "Category: " + this.getCategory() + "\n";
		}
		if (this.getDirector() != null) {
			res += "Director: " + this.getDirector() + "\n";
		}
		if (this.getLength() != 0) {
			res += "Total legnth: " + this.getLength() + "\n";
		}
		if (this.getCost() != 0.0f) {
			res += "Cost: " + this.getCost();
		}
		return res;
	}

	@Override
	public boolean isMatch(String title) {
		return this.getTitle().equals(title);
	}
}