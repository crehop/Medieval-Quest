package server;

public class Location {
	private float x;
	private float y;
	private float z;
	
	public Location(){
		this.setX(0);
		this.setY(0);
		this.setZ(0);
	}
	public Location(float x, float y, float z){
		this.setX(x);
		this.setY(y);
		this.setZ(z);
	}
	public Location(Location location) {
		this.setX(location.getX());
		this.setY(location.getX());
		this.setZ(location.getZ());
	}
	public float getX() {
		return this.x;
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
	public float getZ() {
		return z;
	}
	public void setZ(float z) {
		this.z = z;
	}
	
}
