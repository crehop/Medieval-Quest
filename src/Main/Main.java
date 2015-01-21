package Main;
import java.util.ArrayList;

import loops.GameLoop;
import loops.StartLoop;

import org.lwjgl.LWJGLException;
import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;

import org.lwjgl.opengl.*;

import Information.EnhancedDelta;

import server.Location;

import entities.Player;
import voxel.Wireframe;


public class Main {
	public static Player cam;
	public static ArrayList<Wireframe> wireframes = new ArrayList<Wireframe>();
	public static String loop = "game";
	private static boolean debug = false;
	public static boolean isInGameMode = false;
	public static boolean isInMenuMode = false;
	public static boolean isInStartMode = false;
	public static boolean loopSwitch = false;
	public static boolean open = true;
	public static boolean paused = false;
	private static boolean lockCamera = false;
	private static int height = 720;
	private static int width = 1080;
	private static double time = 0.0f;
	public static Location center = new Location(0.0f,0.0f,0.0f);
	
	public static void main(String[] args) {
		initiate();
		loop();
		scrub();		
	}
	public static void loop() {
	      Thread loop = new Thread()
	      {
	         public void run()
	         {
	        	 while(this.isOpen()){
	        		 EnhancedDelta.checkDelta();
	        	 }
        		 EnhancedDelta.checkDelta();
	         }

			public boolean isOpen() {
				return open;
			}
	      };
	      loop.start();
   		while(!Display.isCloseRequested()){
  			glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
  			Mouse.updateCursor();
  			confirmLoop();
  			Display.update();
  			Display.sync(60);
  		}
	}
	private static void confirmLoop() {
		switch(loop){
		case "start": 
			if(loopSwitch == true){
				StartLoop.toggleInitiate();
				loopSwitch = false;
			}
			StartLoop.loop();
			isInGameMode = false;
			isInMenuMode = false;
			isInStartMode = true;
			if(debug){
				
			}
			break;
		case"menu": 
			if(loopSwitch == true){
				loopSwitch = false;
			}
			isInGameMode = false;
			isInMenuMode = true;
			isInStartMode = false;
			if(debug){
				
			}
			break;
		case "game":
			if(loopSwitch == true){
				GameLoop.toggleInitiate();
				loopSwitch = false;
			}
			GameLoop.loop();
			isInGameMode = true;
			isInMenuMode = false;
			isInStartMode = false;
			if(debug){
				
			}
			break;
		default:
			if(loopSwitch == true){
				loopSwitch = false;
			}
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
			Display.setVSyncEnabled(true);
			Display.setResizable(true);
			Display.setFullscreen(false);
			Display.setDisplayMode(new DisplayMode(width,height));
			Display.setTitle("Zombie RTS");
			Display.create();
			//cam = new Camera(70,(float)Display.getWidth()/(float)Display.getHeight(),0.3f,1000);
			cam = new Player(0,0,0);
			
		} catch (LWJGLException e) {
			e.printStackTrace();
		}
	}
	public static void scrub(){
   		open = false;
		Display.destroy();
	}
	public static void setLoop(String loop2){
		
		if(loop.equalsIgnoreCase("game")||loop.equalsIgnoreCase("menu")||loop.equalsIgnoreCase("start")){
			Main.loop = loop2;
			loopSwitch = true;
		}
		else{
		}
	}
	public static void toggleDebug(){
		debug = true;
	}
	public static String getLoop(){
		return loop;
	}
	public static boolean isOpen(){
		return open;
	}
	public static boolean isPaused() {
		return paused;
	}
	public static void updateTime(double now){
		time = now;
	}
	public static double getTime(){
		return (time/100000000.0f);
	}
	public static void resizeDisplay(int width, int height){
		try {
			Display.setDisplayMode(new DisplayMode(width,height));
		} catch (LWJGLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void lockCamera(boolean lock){
		lockCamera = lock;
	}
	public static boolean isCameraLocked(){
		return lockCamera;
	}
}
