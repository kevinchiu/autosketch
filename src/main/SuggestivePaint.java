package main;

//libraries
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
	private boolean drawing, suggested;
	private Database db;
	private String suggestion;
	
	public void setup(){
		
		drawing = false;
		suggested = true;
		
		this.size(WIDTH,HEIGHT);
		this.smooth();
		this.background(255);
		
		tuio = new Tuio(3333);
		
		// vision stuff
		
		db = new Database();
		
	}
 
 	public void draw(){
 		
 		drawing = false;
 		
 		Enumeration<TuioCursor> eCur;
 		TuioCursor auxCur;
 		Hashtable<Integer,TuioCursor> cursors = tuio.getCursors();
 		this.fill(0);
 		for( eCur = cursors.elements() ; eCur.hasMoreElements() ; ) {
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
 		for( eObj = objects.elements() ; eObj.hasMoreElements() ; ) {
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
 			suggested = true;
 			// vision stuff
 			suggestion = db.findImage(this.get());
 		}
 	
 	}
 	
 	public void keyPressed() {
 		if(key == 'S' || key == 's'){
 			PImage screen = this.get();
 			screen.save("img"+Math.round(Math.random()*10000)+".jpg");
 		}
 	}
 	
 	public int[] getPixels(){
 		return this.getPixels();
 	}
 	

    public static void main(String args[]) {
            SuggestivePaint app = new SuggestivePaint();
            new BorderlessWindow(app, "Suggestive Paint");
            app.init();
    }

} 
