package Main;
import java.util.ArrayList;

import loops.MenuLoop;

import org.lwjgl.LWJGLException;
import static org.lwjgl.input.Keyboard.*;
import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;

import org.lwjgl.opengl.*;
import org.newdawn.slick.opengl.Texture;

import renderer.Camera;
import renderer.TextureHandler;
import wireframe.Wireframe;
import wireframe.WireframePart;


public class Main {
	public static Camera cam;
	private static int partID = 0;
	public static ArrayList<Wireframe> wireframes = new ArrayList<Wireframe>();
	public static String loop = "start";
	
	public static void main(String[] args) {
		initiate();
		loop();
		scrub();		
	}
	public static void loop() {
		cam = new Camera(35,(float)Display.getWidth()/(float)Display.getHeight(),0.3f,1000);
		while(!Display.isCloseRequested()){
			glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
			confirmLoop();
			Controls.checkInput();
			renderer.Renderer.renderFrame();
			glEnd();
			Display.sync(60);
			Display.update();
		}
	}
	private static void confirmLoop() {
		switch(loop){
		case"start": 
			startLoop();
			System.out.println("StartLoop");
			break;
		case"menu": 
			System.out.println("MenuLoop");
			menuLoop();
			break;
		case"game":
			System.out.println("GameLoop");
			gameLoop();
			break;
		default:
			System.out.println("DEFAULT LOOP WARNING!!!");
			startLoop();
			break;
		}
	}
	private static void menuLoop() {
		
	}
	private static void startLoop() {
		MenuLoop.render();
	}
	private static void gameLoop() {
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
	public static void scrub(){
		Display.destroy();
	}

}
