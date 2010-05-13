package vision;

/**
 * Calculates the signature array we'll use to look up the image later.
 */
import java.util.ArrayList;

import processing.core.PImage;


public class Signature {
	ArrayList<Double> values;
	private static int CELLS = 10;

	public Signature(PImage img) {
		values = Sobel.processImage(img, CELLS); // cells
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

	public String toString(){
		String toReturn = "\n";
		
		for (int i = 0; i < CELLS; i++) {
			for (int j = 0; j < CELLS; j++) {
				toReturn += values.get(i * CELLS + j);
			}
			toReturn += '\n';
		}
		
		return toReturn;
	}
}
