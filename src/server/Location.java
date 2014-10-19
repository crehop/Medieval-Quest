package server;

public class Location {
	private float x;
	private float y;
	private float z;
	private float rotation;
	private float yaw;
	public Location(float x, float y, float z, float rotation, float yaw){
		this.setX(x);
		this.setY(y);
		this.setZ(z);
		this.setRotation(rotation);
		this.setYaw(yaw);
	}
	public Location(){
		this.setRotation(0);
		this.setX(0);
		this.setY(0);
		this.setZ(0);
		this.setYaw(0);
	}
	public Location(float x, float y, float z){
		this.setX(x);
		this.setY(y);
		this.setZ(z);
		this.setRotation(0);
		this.setYaw(0);
		System.out.println("NEW LOCATION CREATED " + x + " " + y + " " + z);
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
	public float getYaw() {
		return yaw;
	}
	public void setYaw(float yaw) {
		this.yaw = yaw;
	}
	public float getRotation() {
		return rotation;
	}
	public void setRotation(float rotation) {
		this.rotation = rotation;
	}
	
}
