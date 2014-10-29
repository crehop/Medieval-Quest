package Main;
import java.util.ArrayList;

import loops.GameLoop;

import org.lwjgl.LWJGLException;
import static org.lwjgl.opengl.GL11.*;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;

import org.lwjgl.opengl.*;

import server.Location;

import entities.Player;
import wireframe.Wireframe;


public class Main {
	public static Player cam;
	public static ArrayList<Wireframe> wireframes = new ArrayList<Wireframe>();
	public static String loop = "start";
	private static boolean debug = false;
	public static boolean isInGameMode = false;
	public static boolean isInMenuMode = false;
	public static boolean isInStartMode = false;
	public static Location center = new Location(0.0f,0.0f,0.0f);
	
	public static void main(String[] args) {
		initiate();
		loop();
		scrub();		
	}
	public static void loop() {
		while(!Display.isCloseRequested()){
			glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
			Main.cam.useView();		
			Controls.checkInput();
			confirmLoop();
			GL11.glLoadIdentity();
			Driver.checkForTick();
			Display.sync(60);
			Display.update();
		}
	}
	private static void confirmLoop() {
		switch(loop){
		case"start": 
			isInGameMode = false;
			isInMenuMode = false;
			isInStartMode = true;
			if(debug){
				
			}
			break;
		case"menu": 
			isInGameMode = false;
			isInMenuMode = true;
			isInStartMode = false;
			if(debug){
				
			}
			break;
		case"game":
			GameLoop.loop();
			isInGameMode = true;
			isInMenuMode = false;
			isInStartMode = false;
			if(debug){
				
			}
			break;
		default:
			isInGameMode = false;
			isInMenuMode = false;
			isInStartMode = true;
			if(debug){
				
			}
			break;
		}
	}
	private static void initiate() {
		try {
			Display.setDisplayMode(new DisplayMode(1080,720));
			Display.setTitle("Medieval-Quest");
			Display.create();
			//cam = new Camera(70,(float)Display.getWidth()/(float)Display.getHeight(),0.3f,1000);
			cam = new Player(0,0,0);
			
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
	public static void toggleDebug(){
		debug = true;
	}
}
