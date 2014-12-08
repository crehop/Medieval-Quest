package renderer;

import org.lwjgl.opengl.GL11;

public class CameraRTS extends CameraFPS {
	
	float zoom = -30.0f;
	public CameraRTS(float x, float y, float z) {
		super(x, y, z);
		super.setPitch(50.0f);
	}
	
	public void walkForward(float distance){
		//moves camera forward relative to its current rotation;
		super.position.x -= distance * (float)Math.sin(Math.toRadians(super.yaw));
		super.position.z += distance * (float)Math.cos(Math.toRadians(super.yaw));
	}
	public void walkBackward(float distance){
		//moves camera backward relative to its current rotation;
		super.position.x += distance * (float)Math.sin(Math.toRadians(super.yaw));
		super.position.z -= distance * (float)Math.cos(Math.toRadians(super.yaw));
	}
	public void strafeLeft(float distance){
		//moves camera forward relative to its current rotation;
		super.position.x -= distance * (float)Math.sin(Math.toRadians(super.yaw - 90));
		super.position.z += distance * (float)Math.cos(Math.toRadians(super.yaw - 90));
	}
	public void strafeRight(float distance){
		//moves camera forward relative to its current rotation;
		super.position.x -= distance * (float)Math.sin(Math.toRadians(super.yaw + 90));
		super.position.z += distance * (float)Math.cos(Math.toRadians(super.yaw + 90));
	}
	public void moveUp(float distance){
		super.position.y -= distance;
	}
	public void moveDown(float distance){
		super.position.y += distance;
	}
	
	public void useView(){
		position.y = zoom;
		GL11.glRotatef(super.pitch, 1.0f, 0.0f, 0.0f);
		GL11.glTranslatef(position.x, position.y, position.z);
		super.updateLocation();
	}
	public void setYaw(float yaw) {
	}

	public void setPitch(float pitch) {
	}
	public void minusZoom(){
		if(zoom < -10.0f){
			this.zoom = zoom += 10.0f;
		}
	}
	public void plusZoom(){
		if(zoom > -1000){
			this.zoom = zoom -= 10.0f;
		}
	}
	public void Rotate90Right(){
		//super.setYaw(super.getYaw() + 90.0f);
	}
	public void Rotate90Left(){
		//super.setYaw(super.getYaw() - 90.0f);
	}
	
}
