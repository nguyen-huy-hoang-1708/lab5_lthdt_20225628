package hust.soict.dsai.aims.media;

import java.util.ArrayList;
import java.util.List;

import hust.soict.dsai.aims.exception.PlayerException;

public class CompactDisc extends Disc implements Playable {
	private String artist;
	private List<Track> tracks = new ArrayList<>();

	public CompactDisc(int id, String title, String category, String artist, float cost) {
		super(id, title, category, null, cost);
		this.artist = artist;
		//this.tracks = tracks;
	}

	public CompactDisc(int id, String title, String category, String artist, String director, float cost) {
		super(id, title, category, director, cost);
		this.artist = artist;
		//this.tracks = tracks;
	}

	public boolean addTrack(Track newTrack) {
        if(this.tracks.contains(newTrack)){
            return false;
        } else {
            this.tracks.add(newTrack);
            return true;
        }
    }

	public boolean removeTrack(Track track) {
		if(this.tracks.contains(track)){
			this.tracks.remove(track);
            return true;
        } else {

            return false;
        }
	}

	@Override
	public int getLength(){
        int totalLength = 0;
        for (Track track : this.tracks){
            totalLength += track.getLength();
        }
        return totalLength;
    }

	@Override
	public String toString() {
		String res = this.getId() + ". Compact Disc (CD): \n";
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
    public String play() throws PlayerException {
		String log = "";
		if (this.getLength() > 0) {
			System.out.println("Playing CD: " + this.getTitle());
            System.out.println("CD length: " + this.getLength());

            log += "Playing CD " + this.getTitle() + "\n" + "CD length: " + this.getLength() + "\n";
            //info.append("Playing CD: " + this.getTitle() + "\n" + "CD length: " + this.getLength() + "\n");

            for (Track t : tracks) {
                try {
                    String trackLog = t.play();
                    log += trackLog + "\n";

                } catch (PlayerException e) {
                    throw e;
                }
            }
		}
		else {
			throw new PlayerException("ERROR: The CD length is not positive");
		}
        return log;
    }

	@Override
	public boolean isMatch(String title) {
		return this.getTitle().equals(title);
	}
}