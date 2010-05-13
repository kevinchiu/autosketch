package main;

//libraries
import java.io.File;
import java.util.Enumeration;
import java.util.Hashtable;

import processing.core.PApplet;
import processing.core.PImage;
import vision.Database;
import window.BorderlessWindow;

import communication.Tuio;
import communication.TuioCursor;
import communication.TuioObject;

@SuppressWarnings({ "serial"})

public class SuggestivePaint extends PApplet{
 
	public static final int WIDTH = 1024;
	public static final int HEIGHT = 768;
	
	private Tuio tuio;
	private Hashtable<Integer,TuioCursor> prevCursors;
	private Hashtable<Integer,TuioObject> prevObjects;
	private boolean drawing, suggested, mute;
	private Database dbImg;
	private Database dbAlt;
	private PImage suggestion;
	private String folder;
	
	public void setup(){
		
		// flags
		drawing = false;
		suggested = true;
		mute = false;
		
		// processing
		this.size(WIDTH,HEIGHT);
		this.smooth();
		this.background(255);
		
		// tuio
		tuio = new Tuio(3333);
		
		//database management
		folder = "img";
		
		// vision
		dbImg = new Database();
		dbAlt = new Database();
		String[] filenames;
		filenames = this.listFileNames(this.sketchPath+"/img");
		for (int i = 0; i < filenames.length; i++) {
			if(filenames[i].contains("jpg")){
				PImage aux = this.loadImage("img/"+filenames[i]);
				dbImg.addImage(aux, filenames[i]);
			}
		}
		filenames = this.listFileNames(this.sketchPath+"/alt");
		for (int i = 0; i < filenames.length; i++) {
			if(filenames[i].contains("jpg")){
				PImage aux = this.loadImage("alt/"+filenames[i]);
				dbAlt.addImage(aux, filenames[i]);
			}
		}
		System.out.println(dbAlt);
		suggestion = new PImage(this.width,this.height);
		
	}
 
 	public void draw(){
 		
 		drawing = false;
 		
 		Enumeration<TuioCursor> eCur;
 		TuioCursor auxCur;
 		Hashtable<Integer,TuioCursor> cursors = tuio.getCursors();
 		this.fill(0);
 		for( eCur = cursors.elements() ; eCur.hasMoreElements() ; ) {
 			if(drawing == false) removeSuggestion();
 			drawing = true;
 			suggested = false;
 			auxCur = eCur.nextElement();
 			if(prevCursors.containsKey(auxCur.getSessionId())){
 				this.strokeWeight(11);
 				this.line(Math.round(auxCur.getX()*this.width), Math.round(auxCur.getY()*this.height), Math.round(prevCursors.get(auxCur.getSessionId()).getX()*this.width), Math.round(prevCursors.get(auxCur.getSessionId()).getY()*this.height));
 			}
 			this.strokeWeight(0);
 			this.ellipse(Math.round(auxCur.getX()*this.width), Math.round(auxCur.getY()*this.height), 10, 10);
 		}
 		prevCursors = tuio.getCursors();
 		
 		Enumeration<TuioObject> eObj;
 		TuioObject auxObj;
 		Hashtable<Integer,TuioObject> objects = tuio.getObjects();
 		this.fill(255);
 		this.noStroke();
 		for( eObj = objects.elements() ; eObj.hasMoreElements() ; ) {
 			if(drawing == false) removeSuggestion();
 			drawing = true;
 			suggested = false;
 			auxObj = eObj.nextElement();
 			if(prevObjects.containsKey(auxObj.getSessionId())){
 				this.strokeWeight(Math.max(auxObj.getWidth(),auxObj.getHeight())*this.width);
 				this.line(Math.round(auxObj.getX()*this.width), Math.round(auxObj.getY()*this.height), Math.round(prevObjects.get(auxObj.getSessionId()).getX()*this.width), Math.round(prevObjects.get(auxObj.getSessionId()).getY()*this.height));
 			}
 			this.strokeWeight(0);
 			this.ellipse(Math.round(auxObj.getX()*this.width), Math.round(auxObj.getY()*this.height), Math.max(auxObj.getWidth(),auxObj.getHeight())*this.width, Math.max(auxObj.getWidth(),auxObj.getHeight())*this.width);
 		}
 		prevObjects = tuio.getObjects();
 		
 		if(!suggested && !drawing) {
 			System.out.println("suggestion!!");
 			suggested = true;
 			// vision stuff
 			if(folder == "img") suggestion = loadImage(folder+"/"+dbImg.findImage(this.get()));
 			else if(folder == "alt") suggestion = loadImage(folder+"/"+dbAlt.findImage(this.get()));
 			System.out.println(dbAlt.findImage(this.get()));
 			makeSuggestion();
 		}
 	
 	}
 	
 	public void keyPressed() {
 		if(key == 'S' || key == 's'){
 			PImage screen = this.get();
 			String filename = folder+"/img"+Math.round(Math.random()*10000)+".jpg";
 			screen.save(filename);
 			dbImg.addImage(loadImage(filename), filename);
 		}
 		if(key == 'B' || key == 'b'){
 			this.background(255);
 		}
 		if(key == 'M' || key == 'm'){
 			if(!mute){
 				mute = true;
 				removeSuggestion();
 			}
 			else {
 				makeSuggestion();
 				mute = false;
 			}
 			
 		}
 		if(key == '1'){
 			removeSuggestion();
 			folder="img";
 			suggestion = new PImage(this.width,this.height);
 			drawing = false;
 			suggested = true;
 		}
 		if(key == '2'){
 			removeSuggestion();
 			folder="alt";
 			suggestion = new PImage(this.width,this.height);
 			drawing = false;
 			suggested = true;
 		}
 		
 	}
 	
 	public int[] getPixels(){
 		return this.getPixels();
 	}
 	
 	private String[] listFileNames(String dir) {
 		
 		File file = new File(dir);
 		
 		if (file.isDirectory()) {
 			String names[] = file.list();
 			return names;
 		} else return null;
 		
 	}
 	
 	private void makeSuggestion(){
 		suggestion.loadPixels();
 		this.loadPixels();
 		for ( int i = 0 ; i < this.height ; i++ ) for ( int j = 0 ; j < this.width ; j++ ) 
 			if(suggestion.pixels[i*height+j] == this.color(0) && this.pixels[i*height+j] == color(255))
 				this.pixels[i*height+j] = color(100,50,50);
 		this.updatePixels();	
 	}
 	
 	private void removeSuggestion(){
 		this.loadPixels();
 		for ( int i = 0 ; i < this.height ; i++ ) for ( int j = 0 ; j < this.width ; j++ )
 			if(this.pixels[i*height+j] == color(100,50,50))
 				this.pixels[i*height+j] = color(255,255,255);
 		this.updatePixels();	
 	}

    public static void main(String args[]) {
            SuggestivePaint app = new SuggestivePaint();
            new BorderlessWindow(app, "Suggestive Paint");
            app.init();
    }

} 
