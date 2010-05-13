package communication;

public class TuioObject {

	private int sessionId;
	private float x,y,angle,width,height,area,xspeed,yspeed,angularspeed,acceleration,angularacceleration;
	
	public TuioObject(int _sessionId, float _x, float _y, float _angle, float _width, float _height, float _area, float _xspeed, float _yspeed, float _angularspeed, float _acceleration, float _angularacceleration){
		
		this.sessionId = _sessionId;
		this.x = _x;
		this.y = _y;
		this.angle = _angle;
		this.width = _width;
		this.height = _height;
		this.area = _area;
		this.xspeed = _xspeed;
		this.yspeed = _yspeed;
		this.angularspeed = _angularspeed;
		this.acceleration = _acceleration;
		this.angularacceleration = _angularacceleration;
		
	}
	
	public float getAngle() {
		
		return angle;
	
	}

	public void setAngle(float angle) {

		this.angle = angle;
	
	}

	public float getWidth() {
	
		return width;
	
	}

	public void setWidth(float width) {
	
		this.width = width;
	
	}

	public float getHeight() {
	
		return height;
	
	}

	public void setHeight(float height) {
	
		this.height = height;
	
	}

	public float getArea() {
	
		return area;
	
	}

	public void setArea(float area) {
	
		this.area = area;
	
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