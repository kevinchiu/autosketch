package vision;

/**
 * Calculates the signature array we'll use to look up the image later.
 */
import java.util.ArrayList;

import processing.core.PImage;

public class Signature {
	ArrayList<Double> values;

	public Signature(PImage img) {
		values = Sobel.processImage(img, 10); // cells
	}

	public boolean equals(Object o) {
		if (values.equals(o))
			return true;
		else
			return false;
	}

	public int hashCode() {
		return values.hashCode();
	}

	public ArrayList<Double> getValues() {
		return values;
	}

}
