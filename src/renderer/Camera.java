package renderer;

import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;

public class Camera {
	private float x;
	private float y;
	private float z;
	private float rx;
	private float ry;
	private float rz;
		
	public Camera(float fov, float aspect, float near, float far){
		x = 0;
		y = 0;
		z = 0;
		rx = 0;
		ry = 0;
		rz = 0;
		initProjection();
	}

	public void initProjection(){
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glOrtho(0, Display.getWidth(), 0, Display.getHeight(), 1, -1);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		GL11.glLoadIdentity();
	}

	public void useView(){
		glRotatef(rx,1,0,0);
		glRotatef(ry,0,1,0);
		glRotatef(rz,0,0,1);
		glTranslatef(x,y,z);
	}

	public float getX(){
		return x;
	}

	public float getY(){
		return y;
	}

	public float getZ(){
		return z;
	}

	public void setX(float x){
		this.x = x;
	}

	public void setY(float y){
		this.y = y;
	}

	public void setZ(float z){
		this.z = z;
	}

	public float getRX(){
		return rx;
	}

	public float getRY(){
		return ry;
	}

	public float getRZ(){
		return rz;
	}

	public void setRX(float rx){
		this.rx = rx;
	}

	public void setRY(float ry){
		this.ry = ry;
	}

	public void setRZ(float rz){
		this.rz = rz;
	}
	
	public void moveForward(){
		
	}

	public void stabalize() {
		this.setRX(0.0f);
		this.setZ(0.0f);
		this.setX(0.0f);
		this.setY(0.0f);
		this.setRZ(0.0f);
		this.setRY(0.0f);
	}

	public void decelerate() {
		if(this.getRX() != 0){
			if(this.getRX() > 0){
				this.setRX(this.getRX() - 0.01f);
				if(this.getRX() > 0.0f && this.getRX() < 0.01f){
					this.setRX(0.0f);
				}
			}
			else if(this.getRX() < 0){
				this.setRX(this.getRX() + 0.01f);
				if(this.getRX() < 0.0f && this.getRX() > -0.01f){
					this.setRX(0.0f);
				}
			}
		}
		if(this.getRY() != 0){
			if(this.getRY() > 0){
				this.setRY(this.getRY() - 0.01f);
				if(this.getRY() > 0.0f && this.getRY() < 0.01f){
					this.setRY(0.0f);
				}
			}
			else if(this.getRY() < 0){
				this.setRY(this.getRY() + 0.01f);
				if(this.getRY() < 0.0f && this.getRY() > -0.01f){
					this.setRY(0.0f);
				}
			}
		}
		if(this.getRZ() != 0){
			if(this.getRZ() > 0){
				this.setRZ(this.getRZ() - 0.01f);
				if(this.getRZ() > 0.0f && this.getRZ() < 0.01f){
					this.setRZ(0.0f);
				}
			}
			else if(this.getRX() < 0){
				this.setRZ(this.getRZ() + 0.01f);
				if(this.getRX() < 0.0f && this.getRX() > -0.01f){
					this.setRX(0.0f);
				}
			}
		}
		if(this.getX() != 0){
			if(this.getX() > 0){
				this.setX(this.getX() - 0.01f);
				if(this.getX() > 0.0f && this.getX() < 0.01f){
					this.setX(0.0f);
				}
			}
			else if(this.getX() < 0){
				this.setX(this.getX() + 0.01f);
				if(this.getX() < 0.0f && this.getX() > -0.01f){
					this.setX(0.0f);
				}
			}
		}
		if(this.getZ() != 0){
			if(this.getZ() > 0){
				this.setZ(this.getZ() - 0.01f);
				if(this.getZ() > 0.0f && this.getZ() < 0.01f){
					this.setZ(0.0f);
				}
				
			}
			else if(this.getZ() < 0){
				this.setZ(this.getZ() + 0.01f);
				if(this.getZ() < 0.0f && this.getZ() > -0.01f){
					this.setZ(0.0f);
				}
			}
		}
		if(this.getY() != 0){
			if(this.getY() > 0){
				this.setY(this.getY() - 0.01f);
				if(this.getY() > 0.0f && this.getY() < 0.01f){
					this.setY(0.0f);
				}
			}
			else if(this.getY() < 0){
				this.setY(this.getY() + 0.01f);
				if(this.getY() > 0.01f){
					this.setY(0.0f);
					if(this.getY() < 0.0f && this.getY() > -0.01f){
						this.setY(0.0f);
					}
				}
			}
		}
	}
}
