package voxel;
import static org.lwjgl.opengl.GL11.*;

import server.Location;

//import java.util.Random;

public class Voxel {
	private float size;
	private Location location;
	public Voxel(Location location){
		this.location = location;
		size = 0.5f;
	}
	public Voxel(Location location, float i) {
		this.location = location;
		this.size = i;
	}
	public void render(){
		initiateRender();
		renderFinal();
	}
	
	private void initiateRender() {
		glPushMatrix();
		this.move(this.getLocation());
		glRotatef(0.0f,0.0f,0.0f,0.0f);
		glBegin(GL_QUADS);
		{
			renderFront(true);
			renderLeft(true);
			renderRight(true);
			renderBack(true);
			renderBottom(true);
			renderTop(true);
		}
	}
	
	private void renderFinal() {
		glEnd();
	    glPopMatrix();
	}

	private void renderTop(boolean render) {
		if(render){
			//Top Face
			glColor3f(0.9f,0.9f,0.9f);
			glVertex3f(size,-1 * (size),-1 * (size));
			glVertex3f(size,-1 * (size),size);
			glVertex3f(size,size,size);
			glVertex3f(size,size,-1 * (size));
		}
	}

	private void renderBottom(boolean render) {
		if(render){
			//Bottom Face
			glColor3f(0.9f,0.9f,0.0f);
			glVertex3f(-1 * (size),-1 * (size),-1 * (size));
			glVertex3f(-1 * (size),-1 * (size),size);
			glVertex3f(-1 * (size),size,size);
			glVertex3f(-1 * (size),size,-1 * (size));	
		}
	}
	
	private void renderFront(boolean render) {
		if(render){
			//Front Face
			glColor3f(0.9f,0.0f,0.9f);
			glVertex3f(-1 * (size),-1 * (size),size);
			glVertex3f(-1 * (size),size,size);
			glVertex3f(size,size,size);
			glVertex3f(size,-1 * (size),size);
		}
	}

	private void renderBack(boolean render) {
		if(render){
		//Back Face;
			glColor3f(0.0f,0.9f,0.9f);
			glVertex3f(-1 * (size),-1 * (size),-1 * (size));
			glVertex3f(-1 * (size),size,-1 * (size));
			glVertex3f(size,size,-1 * (size));
			glVertex3f(size,-1 * (size),-1 * (size));
		}
	}

	private void renderRight(boolean render) {
		if(render){
			//Right Face
			glColor3f(0.9f,0.0f,0.0f);
			glVertex3f(-1 * (size),size,-1 * (size));
			glVertex3f(size,size,-1 * (size));
			glVertex3f(size,size,size);
			glVertex3f(-1 * (size),size,size);		
		}
	}

	private void renderLeft(boolean render) {
		if(render){
			//Left Face
			glColor3f(0.0f,0.0f,0.9f);
			glVertex3f(-1 * (size),-1 * (size),-1 * (size));
			glVertex3f(size,-1 * (size),-1 * (size));
			glVertex3f(size,-1 * (size),size);
			glVertex3f(-1 * (size),-1 * (size),size);	
		}
	}
	public Location getLocation(){
		return location;
	}
	
	public void move(Location location){
		glTranslatef(location.getX(),location.getY(),location.getZ());
	}
	//private static void glRandomColor() {
	//	Random rand = new Random();
	//	glColor3f(rand.nextFloat(),rand.nextFloat(),rand.nextFloat());
	//}
}
