package Main;
import java.util.ArrayList;

import loops.GameLoop;
import loops.MenuLoop;
import loops.StartLoop;

import org.lwjgl.LWJGLException;
import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;

import org.lwjgl.opengl.*;

import renderer.CameraFPS;
import wireframe.Wireframe;


public class Main {
	public static CameraFPS cam;
	public static ArrayList<Wireframe> wireframes = new ArrayList<Wireframe>();
	public static String loop = "start";
	private static boolean debug = false;
	public static boolean isInGameMode = false;
	public static boolean isInMenuMode = false;
	public static boolean isInStartMode = false;
	
	
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
	        Thread.yield();
	        //This stops the app from consuming all your CPU. It makes this slightly less accurate, but is worth it.
	        //You can remove this line and it will still work (better), your CPU just climbs on certain OSes.
	        //FYI on some OS's this can cause pretty bad stuttering.
	        try {Thread.sleep(1);} catch(Exception e) {}
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
			cam = new CameraFPS(0,0,0);
			
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
