package communication;

public class TuioTag {

	private int sessionId,id;
	private float x,y,angle,xspeed,yspeed,angularspeed,acceleration,angularacceleration;

	public TuioTag(int _sessionId, int _id, float _x, float _y, float _angle, float _xspeed, float _yspeed, float _angularspeed, float _acceleration, float _angularacceleration){
		
		this.sessionId = _sessionId;
		this.id = _id;
		this.x = _x;
		this.y = _y;
		this.angle = _angle;
		this.xspeed = _xspeed;
		this.yspeed = _yspeed;
		this.angularspeed = _angularspeed;
		this.acceleration = _acceleration;
		this.angularacceleration = _angularacceleration;
		
	}
	
	public int getId() {
	
		return id;
	
	}

	public void setId(int id) {
	
		this.id = id;
	
	}
	
	public float getAngle() {
		
		return angle;
	
	}

	public void setAngle(float angle) {

		this.angle = angle;
	
	}

	public float getAngularspeed() {
	
		return angularspeed;
	
	}

	public void setAngularspeed(float angularspeed) {
	
		this.angularspeed = angularspeed;
	
	}

	public float getAngularacceleration() {
	
		return angularacceleration;
	
	}

	public void setAngularacceleration(float angularacceleration) {
	
		this.angularacceleration = angularacceleration;
	
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