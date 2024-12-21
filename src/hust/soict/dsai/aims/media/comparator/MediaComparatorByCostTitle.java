package hust.soict.dsai.aims.media.comparator;

import java.util.Comparator;

import hust.soict.dsai.aims.media.Media;

public class MediaComparatorByCostTitle implements Comparator<Media> {

	@Override
	public int compare(Media m1, Media m2) {
		String cost1 = String.valueOf(m1.getCost());
		String cost2 = String.valueOf(m2.getCost());

		int comp = cost1.compareTo(cost2);
		if (comp != 0) {
			return comp;
		}
		String title1 = m1.getTitle();
		String title2 = m2.getTitle();

		return title1.compareTo(title2);
	}
}