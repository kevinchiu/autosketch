package vision;

import java.util.ArrayList;
import java.util.HashMap;

import processing.core.PImage;

public class Database {
	private HashMap<Signature, String> dictionary;

	public Database() {
		dictionary = new HashMap<Signature, String>();
	}

	public String findImage(PImage img){
		Signature sig = new Signature(img);
		return findBest(sig);
	}

	public void addImage(PImage img, String filename){
		Signature sig = new Signature(img);
		dictionary.put(sig, filename);
	}

	/*
	 * private methods
	 */
	
	private String findBest(Signature s) {
		// iterate through whole collection and find nearest match
		float error = Float.MAX_VALUE;
		Signature best = null; // will be a problem if there are no sigs
		String toReturn = "FAIL";
		for (Signature sig : dictionary.keySet()) {
			float localError = diff(sig, s);
			if (localError < error) {
				error = localError;
				best = sig;
			}
		}
		return dictionary.get(best);
	}
	private void saveSignature(Signature s, String filename) {
		dictionary.put(s, filename);
	}

	private void removeSignature(Signature s) {
		dictionary.remove(s);
	}
	
	private float diff(Signature a, Signature b) {
		float difference = 0;
		ArrayList<Double> av = a.getValues();
		ArrayList<Double> bv = b.getValues();
		for (int i = 0; i < av.size(); i++)
			difference += Math.abs(av.get(i) - bv.get(i));
		return difference;
	}
}
