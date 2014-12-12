package Information;

import org.lwjgl.opengl.Display;

import server.ServerTime;
public class FPS {
	static boolean started = false;
    static float fps = 0;
	static float lastFPS;
	public static void start() {
	    lastFPS = ServerTime.getTime(); //set lastFPS to current Time
	}
	 
	 
	/**
	 * Calculate the FPS and set it in the title bar
	 */
	public static void updateFPS() {
		if(!(started)){
			start();
			started = true;
		}
		if (ServerTime.getTime() - lastFPS > 1000) {
	        Display.setTitle("FPS: " + fps); 
	        fps = 0; //reset the FPS counter
	        lastFPS += 1000; //add one second
	    }
	    fps++;
	}
}
