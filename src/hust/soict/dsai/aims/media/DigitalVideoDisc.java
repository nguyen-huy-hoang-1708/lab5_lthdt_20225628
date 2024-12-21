package hust.soict.dsai.aims.media;

import hust.soict.dsai.aims.exception.PlayerException;

public class DigitalVideoDisc extends Media implements Playable {
	public static final float EPS = 1e-6f;
	private static int nbDigitalVideoDiscs = 0;

	private String director;
	private int length;

	public DigitalVideoDisc(String title) {
		this(title, null, null, 0, 0);
	}

	public DigitalVideoDisc(String title, String category, float cost) {
		this(title, category, null, 0, cost);
	}

	public DigitalVideoDisc(String title, String category, String director, float cost) {
		this(title, category, director, 0, cost);
	}

	public DigitalVideoDisc(int id, String title, String category, float cost) {
		super(id, title, category, cost);
	}

	public DigitalVideoDisc(int id, String title, String category, String director, float cost) {
		super(id, title, category, cost);
		this.director = director;
	}
	public DigitalVideoDisc(String title, String category, String director, int length, float cost) {
		super(DigitalVideoDisc.nbDigitalVideoDiscs++, title, category, cost);
		this.director = director;
		this.length = length;
	}

	public DigitalVideoDisc(int id, String title, String category, String director, int length, float cost) {
		super(id, title, category, cost);
		this.director = director;
		this.length = length;
	}

	@Override
	public boolean isMatch(String title) {
		return this.getTitle().equals(title);
	}

	@Override
    public String play() throws PlayerException {
		String log = "";
		if (this.getLength() > 0) {
			System.out.println("Playing DVD: " + this.getTitle());
	        System.out.println("DVD length: " + this.getLength());
	        log += "Playing DVD " + this.getTitle() + "\n" + "DVD length: " + this.getLength() + "\n";
		}
		else {
			throw new PlayerException("Error! The DVD length is not positive!");
		}
        return log;
    }

	@Override
	public String toString() {
		String res = this.getId() + ". Digital Video Disc (DVD): \n";
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

	public int getNbDigitalVideoDiscs() {
		return DigitalVideoDisc.nbDigitalVideoDiscs;
	}

	public String getDirector() {
		return director;
	}
	public int getLength() {
		return length;
	}

}