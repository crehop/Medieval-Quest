package entities;

import renderer.CameraFPS;
import server.Location;
						
public class Player extends CameraFPS{
	private Location location;
	public Player(float x, float y, float z) {
		super(x, y, z);
		location = new Location(x,y,z);
	}
	public void setLocation(float x,float y,float z){
		this.location.setX(x);
		this.location.setY(y);
		this.location.setZ(z);
	}
	public Location getLocation(){
		return this.location;
	}

}
