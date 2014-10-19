package Main;
import java.util.ArrayList;

import org.lwjgl.LWJGLException;
import static org.lwjgl.input.Keyboard.*;
import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;

import org.lwjgl.opengl.*;

import Renderer.TextureHandler;
import Wireframe.Wireframe;
import Wireframe.WireframePart;

public class Main {
	public static Camera cam;
	private static boolean pressed = false;
	private static int partID = 0;
	public static ArrayList<Wireframe> wireframes = new ArrayList<Wireframe>();
	
	public static void main(String[] args) {
		initiate();
		gameLoop();
		scrub();		
	}
	private static void gameLoop() {
		TextureHandler.initialize();
		cam = new Camera(35,(float)Display.getWidth()/(float)Display.getHeight(),0.3f,1000);
		while(!Display.isCloseRequested()){
			glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
			{
				{

					glTranslatef(0,0,-20);
					if(Keyboard.isKeyDown(KEY_F) && pressed == false){
						Location home = new Location(0.3f,0.1f,2.2f);
						Location start = new Location (1.3f,0.07f,0.03f);
						Location end = new Location(0f,-0.2f,1.1f);
						Wireframe frame = new Wireframe();
						WireframePart part = new WireframePart(start,end,frame);
						frame.addPart(part);
						frame.setLocation(home);
						System.out.println("CONFIRM F PRESS");
						pressed = true;
					}
				}
			}
			Controls.checkInput();
			Renderer.Renderer.renderFrame();
			cam.useView();
			glEnd();
			Display.sync(60);
			Display.update();
		}
	}
	private static void initiate() {
		try {
			Display.setDisplayMode(new DisplayMode(1080,720));
			Display.setTitle("Medieval-Quest");
			Display.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
		}
	}
	public static int getPartID() {
		partID++;
		return partID;
	}
	public static void scrub(){
		Display.destroy();
	}

}
