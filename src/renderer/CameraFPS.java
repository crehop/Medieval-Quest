package renderer;
import static org.lwjgl.opengl.GL11.GL_DEPTH_TEST;
import static org.lwjgl.opengl.GL11.GL_MODELVIEW;
import static org.lwjgl.opengl.GL11.GL_PROJECTION;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glMatrixMode;
import static org.lwjgl.util.glu.GLU.gluPerspective;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Vector3f;

import server.Location;

public class CameraFPS {
	protected Vector3f position = null;
	protected float yaw = 0.0f;
	protected float pitch = 0.0f;
	private Location location;
	
	
	public CameraFPS(float x, float y, float z){
		position = new Vector3f(x,y,z);
		this.location = new Location(x,y,z);
		//initProjection();
	}
	public void initProjection() {
		// TODO Auto-generated method stub
		glMatrixMode(GL_PROJECTION);
		gluPerspective(70,(float)Display.getWidth()/(float)Display.getHeight(),0.0003f,10000);
		glMatrixMode(GL_MODELVIEW);
		glEnable(GL_DEPTH_TEST);
	}
	public void yaw(float amount){
		//increment the yaw by amount
		yaw += amount;
	}
	public void pitch(float amount){
		pitch += amount;
	}
	public void walkForward(float distance){
		if(this.pitch > 86 || this.pitch < -86){
			distance = distance/25.5f;
		}
		else if(this.pitch > 75 || this.pitch < -75){
			distance = distance/7.5f;
		}	
		else if(this.pitch > 65 || this.pitch < -65){
			distance = distance/4.0f;
		}
		else if(this.pitch > 45 || this.pitch < -45){
			distance = distance/1.8f;
		}
		//moves camera forward relative to its current rotation;
		position.x -= distance * (float)Math.sin(Math.toRadians(yaw));
		position.y += distance * (float)Math.tan(Math.toRadians(pitch));
		position.z += distance * (float)Math.cos(Math.toRadians(yaw));
		
	}
	public void walkBackward(float distance){
		if(this.pitch > 86 || this.pitch < -86){
			distance = distance/25.5f;
		}
		else if(this.pitch > 75 || this.pitch < -75){
			distance = distance/7.5f;
		}	
		else if(this.pitch > 65 || this.pitch < -65){
			distance = distance/4.0f;
		}
		else if(this.pitch > 45 || this.pitch < -45){
			distance = distance/1.8f;
		}
		position.x += distance * (float)Math.sin(Math.toRadians(yaw));
		position.y -= distance * (float)Math.tan(Math.toRadians(pitch));
		position.z -= distance * (float)Math.cos(Math.toRadians(yaw));
	}
	public void strafeLeft(float distance){
		//moves camera forward relative to its current rotation;
		position.x -= distance * (float)Math.sin(Math.toRadians(yaw - 90));
		position.z += distance * (float)Math.cos(Math.toRadians(yaw - 90));
	}
	public void strafeRight(float distance){
		//moves camera forward relative to its current rotation;
		position.x -= distance * (float)Math.sin(Math.toRadians(yaw + 90));
		position.z += distance * (float)Math.cos(Math.toRadians(yaw + 90));
	}
	public void moveUp(float distance){
		position.y -= distance;
	}
	public void moveDown(float distance){
		position.y += distance;
	}
	public void useView(){
		//Rotate pitch around X axis
		GL11.glRotatef(pitch, 1.0f, 0.0f, 0.0f);
		//Rotate yaw around Y axis
		GL11.glRotatef(yaw, 0.0f, 1.0f, 0.0f);
		//Move camera into position vectors location;
		GL11.glTranslatef(position.x, position.y, position.z);
		//Update location variable
		this.updateLocation();
	}
	
	public void setLocation(Location location){
		position.setX(location.getX());
		position.setY(location.getY());
		position.setZ(location.getZ());
	}
	public void test(){
		if(location == null){
			location = new Location(0,0,0);
		}
		location.setX(position.getX());
		location.setY(position.getY());
		location.setZ(position.getZ());
	}
	public Location getLocation(){
		return location;
	}
	protected void updateLocation(){
		this.location.setY(-position.y);
		this.location.setX(-position.x);
		this.location.setZ(-position.z);
	}
	public void setYaw(float yaw) {
		if(yaw + this.yaw > 360){
			this.yaw = ((this.yaw + yaw) - 360);
		}
		else if(yaw + this.yaw < 0){
			this.yaw = ((this.yaw + yaw) + 360);
		}
		else{
			this.yaw += yaw;
		}
	}
	public float getPitch(){
		return pitch;
	}
	public void setPitch(float pitch) {
		if ((this.pitch + pitch) > 88.0){
			this.pitch = 88.0f;
		}
		else if ((this.pitch + pitch) < -88.0){
			this.pitch = -88.0f;
		}
		else{
			this.pitch += pitch;
		}
	}
	public float getYaw() {
		return yaw;
	}

	
}
