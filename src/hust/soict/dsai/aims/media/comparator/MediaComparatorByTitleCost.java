package hust.soict.dsai.aims.media.comparator;

import java.util.Comparator;

import hust.soict.dsai.aims.media.Media;

public class MediaComparatorByTitleCost implements Comparator<Media> {

	@Override
	public int compare(Media m1, Media m2) {
		String title1 = m1.getTitle();
		String title2 = m2.getTitle();

		int comp = title1.compareTo(title2);
		if (comp != 0) {
			return comp;
		}

		String cost1 = String.valueOf(m1.getCost());
		String cost2 = String.valueOf(m2.getCost());
		return cost1.compareTo(cost2);
	}

}