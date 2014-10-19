package Main;
import java.util.ArrayList;

import loops.GameLoop;
import loops.MenuLoop;
import loops.StartLoop;

import org.lwjgl.LWJGLException;
import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.opengl.Display;

import org.lwjgl.opengl.*;

import renderer.Camera;
import server.Location;
import voxel.Voxel;
import wireframe.Wireframe;


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
			Controls.checkInput();
			confirmLoop();
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
			break;
		case"menu": 
			menuLoop();
			break;
		case"game":
			gameLoop();
			break;
		default:
			startLoop();
			break;
		}
	}
	private static void menuLoop() {
		MenuLoop.render();
	}
	private static void startLoop() {
		StartLoop.render();
	}
	private static void gameLoop() {
		GameLoop.loop();
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
	public static void setLoop(String loop2){
		if(loop.equalsIgnoreCase("game")||loop.equalsIgnoreCase("menu")||loop.equalsIgnoreCase("start")){
			Main.loop = loop2;
		}
		else{
			System.out.println("IMPROPER LOOP SELECTED MAIN.JAVA");
		}
	}

}
