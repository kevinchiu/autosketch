package communication;

import com.illposed.osc.*;
import java.util.*;

public class Tuio {
	
	private OSCPortIn receiver;
	private OSCListener curListener,objListener,tagListener;
	private Hashtable<Integer,TuioCursor> cursors;
	private Hashtable<Integer,TuioObject> objects;
	private Hashtable<Integer,TuioTag> tags;
	
	public Tuio(int port){
	
		cursors = new Hashtable<Integer,TuioCursor>();
		objects = new Hashtable<Integer,TuioObject>();
		tags = new Hashtable<Integer,TuioTag>();
		
		try{
			receiver = new OSCPortIn(port);
		}catch(Exception e){
			System.out.println("Unable to read TUIO");
		}
		
		curListener = new OSCListener() {
			public void acceptMessage(java.util.Date time, OSCMessage message) {
				processCursor(message);
			}
		};
		
		objListener = new OSCListener() {
			public void acceptMessage(java.util.Date time, OSCMessage message) {
				processObject(message);
			}
		};
		
		tagListener = new OSCListener() {
			public void acceptMessage(java.util.Date time, OSCMessage message) {
				processTag(message);
			}
		};
		
		receiver.addListener("/tuio/2Dcur", curListener);
		receiver.addListener("/tuio/2Dblb", objListener);
		receiver.addListener("/tuio/2Dobj", tagListener);
		receiver.startListening();
	
	}
	
	private void processCursor(OSCMessage message){
		
		Object[] args = message.getArguments();
		String command = (String)args[0];
		
		if(command.equals("set")) cursors.put((Integer)args[1], new TuioCursor((Integer)args[1],(Float)args[2],(Float)args[3],(Float)args[4],(Float)args[5],(Float)args[6]));
		else if(command.equals("alive")){
			Hashtable<Integer,TuioCursor> aux = new Hashtable<Integer,TuioCursor>();
			for(int i=1;i<args.length;i++) if(cursors.containsKey(args[i])) aux.put((Integer)args[i], cursors.get(args[i]));
			cursors = aux;
		}
		
	}

	private void processObject(OSCMessage message){
		
		Object[] args = message.getArguments();
		String command = (String)args[0];
		
		if(command.equals("set")) objects.put((Integer)args[1], new TuioObject((Integer)args[1],(Float)args[2],(Float)args[3],(Float)args[4],(Float)args[5],(Float)args[6],(Float)args[7],(Float)args[8],(Float)args[9],(Float)args[10],(Float)args[11],(Float)args[12]));
		else if(command.equals("alive")){
			Hashtable<Integer,TuioObject> aux = new Hashtable<Integer,TuioObject>();
			for(int i=1;i<args.length;i++) if(cursors.containsKey(args[i])) aux.put((Integer)args[i], objects.get(args[i]));
			objects = aux;
		}
		
	}
	
	private void processTag(OSCMessage message){
		
		Object[] args = message.getArguments();
		String command = (String)args[0];
		
		if(command.equals("set")) tags.put((Integer)args[1], new TuioTag((Integer)args[1],(Integer)args[2],(Float)args[3],(Float)args[4],(Float)args[5],(Float)args[6],(Float)args[7],(Float)args[8],(Float)args[9],(Float)args[10]));
		else if(command.equals("alive")){
			Hashtable<Integer,TuioTag> aux = new Hashtable<Integer,TuioTag>();
			for(int i=1;i<args.length;i++) if(cursors.containsKey(args[i])) aux.put((Integer)args[i], tags.get(args[i]));
			tags = aux;
		}
		
	}

	public Hashtable<Integer, TuioCursor> getCursors() {
	
		return cursors;
	
	}

	public void setCursors(Hashtable<Integer, TuioCursor> cursors) {
	
		this.cursors = cursors;
	
	}

	public Hashtable<Integer, TuioObject> getObjects() {
	
		return objects;
	
	}

	public void setObjects(Hashtable<Integer, TuioObject> objects) {
	
		this.objects = objects;
	
	}

	public Hashtable<Integer, TuioTag> getTags() {
	
		return tags;
	
	}

	public void setTags(Hashtable<Integer, TuioTag> tags) {
	
		this.tags = tags;
	
	}
		
}