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
		saveSignature(sig, filename);
	}
	
	public void removeImage(PImage img){
		Signature sig = new Signature(img);
		removeSignature(sig);
	}

	/*
	 * private methods
	 */
	
	private String findBest(Signature s) {
		// iterate through whole collection and find nearest match
		// fails if db is empty
		float error = Float.MAX_VALUE;
		Signature best = null; 
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
			difference += Math.abs(av.get(i)*av.get(i) - bv.get(i)*av.get(i));
		return difference;
	}
	
	public String toString(){
		String toReturn = "";
		for (Signature sig : dictionary.keySet()) {
			toReturn += sig.toString() + "\n";
		}
		return toReturn;
	}
}
