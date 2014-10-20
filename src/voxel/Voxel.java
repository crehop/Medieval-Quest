package voxel;
import static org.lwjgl.opengl.GL11.*;
import org.lwjgl.opengl.Display;

import server.Location;

import java.util.Random;

public class Voxel {
	
	public Voxel(Location location){

	}
	public void render(){
		initiateRender();
		renderFinal();
	}
	
	private void initiateRender() {
		glPushMatrix();
		glTranslatef(0,0,-10);
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
		Display.update();
	}

	private void renderTop(boolean render) {
		if(render){
			//Top Face
			glColor3f(0.9f,0.9f,0.9f);
			glVertex3f(0.5f,-0.5f,-0.5f);
			glVertex3f(0.5f,-0.5f,0.5f);
			glVertex3f(0.5f,0.5f,0.5f);
			glVertex3f(0.5f,0.5f,-0.5f);
		}
	}

	private void renderBottom(boolean render) {
		if(render){
			//Bottom Face
			glColor3f(0.9f,0.9f,0.0f);
			glVertex3f(-0.5f,-0.5f,-0.5f);
			glVertex3f(-0.5f,-0.5f,0.5f);
			glVertex3f(-0.5f,0.5f,0.5f);
			glVertex3f(-0.5f,0.5f,-0.5f);	
		}
	}
	
	private void renderFront(boolean render) {
		if(render){
			//Front Face
			glColor3f(0.9f,0.0f,0.9f);
			glVertex3f(-0.5f,-0.5f,0.5f);
			glVertex3f(-0.5f,0.5f,0.5f);
			glVertex3f(0.5f,0.5f,0.5f);
			glVertex3f(0.5f,-0.5f,0.5f);
		}
	}

	private void renderBack(boolean render) {
		if(render){
		//Back Face;
			glColor3f(0.0f,0.9f,0.9f);
			glVertex3f(-0.5f,-0.5f,-0.5f);
			glVertex3f(-0.5f,0.5f,-0.5f);
			glVertex3f(0.5f,0.5f,-0.5f);
			glVertex3f(0.5f,-0.5f,-0.5f);
		}
	}

	private void renderRight(boolean render) {
		if(render){
			//Right Face
			glColor3f(0.9f,0.0f,0.0f);
			glVertex3f(-0.5f,0.5f,-0.5f);
			glVertex3f(0.5f,0.5f,-0.5f);
			glVertex3f(0.5f,0.5f,0.5f);
			glVertex3f(-0.5f,0.5f,0.5f);		
		}
	}

	private void renderLeft(boolean render) {
		if(render){
			//Left Face
			glColor3f(0.0f,0.0f,0.9f);
			glVertex3f(-0.5f,-0.5f,-0.5f);
			glVertex3f(0.5f,-0.5f,-0.5f);
			glVertex3f(0.5f,-0.5f,0.5f);
			glVertex3f(-0.5f,-0.5f,0.5f);	
		}
	}
	private static void glRandomColor() {
		Random rand = new Random();
		glColor3f(rand.nextFloat(),rand.nextFloat(),rand.nextFloat());
	}
}
