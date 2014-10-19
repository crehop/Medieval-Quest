package wireframe;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import static org.lwjgl.input.Keyboard.*;


import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.*;
import org.lwjgl.util.Color;

import world.Location;

import Main.Main;
import static org.lwjgl.opengl.GL11.*;

public class WireframePart{
	private final int ID = entities.ID.getPartID();
	private boolean draw = true;
	private Wireframe body;
	private Location start;
	private Location end;
	private Color color;
	public WireframePart(Location start, Location end, Wireframe body){
		if(this.body == null){
			ArrayList<WireframePart> parts = new ArrayList<WireframePart>();
			body = new Wireframe(parts);
			this.end = end;
			this.start = start;
		}
		else{
			this.body = body;
			this.end = end;
			this.start = start;
		}
	}
	public void render() {
		if(draw == true){
			glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
			glLoadIdentity();
			glPushMatrix();
			float x = start.getRotation();
			{
				glTranslatef(0,0,-10);
				glRotatef(x,0.01f,0.01f,0);
				glBegin(GL_QUADS);
				{
					//FrontFace
					glRandomColor();
					glVertex3f(-1,-1,1);
					glVertex3f(-1,1,1);
					glVertex3f(1,1,1);
					glVertex3f(1,-1,1);

					//BackFace;
					glRandomColor();
					glVertex3f(-1,-1,-1);
					glVertex3f(-1,1,-1);
					glVertex3f(1,1,-1);
					glVertex3f(1,-1,-1);

					//BottomFace
					glRandomColor();
					glVertex3f(-1,-1,-1);
					glVertex3f(-1,-1,1);
					glVertex3f(-1,1,1);
					glVertex3f(-1,1,-1);

					//TopFace
					glRandomColor();
					glVertex3f(1,-1,-1);
					glVertex3f(1,-1,1);
					glVertex3f(1,1,1);
					glVertex3f(1,1,-1);

					//LeftFace
					glRandomColor();
					glVertex3f(-1,-1,-1);
					glVertex3f(1,-1,-1);
					glVertex3f(1,-1,1);
					glVertex3f(-1,-1,1);

					//Right Face
					glRandomColor();
					glVertex3f(-1,1,-1);
					glVertex3f(1,1,-1);
					glVertex3f(1,1,1);
					glVertex3f(-1,1,1);
				}
				if(Keyboard.isKeyDown(KEY_K)){
					
				}
				if(Keyboard.isKeyDown(KEY_I)){
					
				}
				if(Keyboard.isKeyDown(KEY_L)){
					start.setRotation(x+0.5f);
				}
				if(Keyboard.isKeyDown(KEY_J)){
					start.setRotation(x-0.5f);
				}
				glEnd();
			}
			glPopMatrix();
			Main.cam.useView();
			Display.update();
			System.out.println("CONFIRM RENDER");
		}

	}
	public Location getStart() {
		return start;
	}
	public void setStart(Location start) {
		this.start = start;
	}
	public Location getEnd() {
		return end;
	}
	public void setEnd(Location end) {
		this.end = end;
	}
	private static void glRandomColor() {
		Random rand = new Random();
		glColor3f(rand.nextFloat(),rand.nextFloat(),rand.nextFloat());
	}

}
