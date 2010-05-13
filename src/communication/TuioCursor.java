package communication;

public class TuioCursor {

	private int sessionId;
	private float x,y,xspeed,yspeed,acceleration;
	
	public TuioCursor(int _sessionId, float _x, float _y, float _xspeed, float _yspeed, float _acceleration){
		
		this.sessionId = _sessionId;
		this.x = _x;
		this.y = _y;
		this.xspeed = _xspeed;
		this.yspeed = _yspeed;
		this.acceleration = _acceleration;
		
	}
	
	public int getSessionId() {
		
		return sessionId;
	
	}
	
	public void setSessionId(int sessionId) {
	
		this.sessionId = sessionId;
	
	}
	
	public float getX() {
	
		return x;
	
	}
	
	public void setX(float x) {
	
		this.x = x;
	
	}
	
	public float getY() {
	
		return y;
	
	}
	
	public void setY(float y) {
	
		this.y = y;
	
	}
	
	public float getXspeed() {
	
		return xspeed;
	
	}
	
	public void setXspeed(float xspeed) {
	
		this.xspeed = xspeed;
	
	}
	
	public float getYspeed() {
	
		return yspeed;
	
	}
	
	public void setYspeed(float yspeed) {
	
		this.yspeed = yspeed;
	
	}
	
	public float getAcceleration() {
	
		return acceleration;
	
	}
	
	public void setAcceleration(float acceleration) {
	
		this.acceleration = acceleration;
	
	}
	
}