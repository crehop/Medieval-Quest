package Information;

import server.ServerTime;
public class FPS {
	static boolean started = false;
    static float fps = 0;
	static float lastFPS;
	static float fpsCheck;
	public static void start() {
	    lastFPS = ServerTime.getTime(); //set lastFPS to current Time
	}
	public static void updateFPS() {
		if(!(started)){
			start();
			started = true;
		}
		if (ServerTime.getTime() - lastFPS > 1000) {
	        fpsCheck = fps;
	        fps = 0; //reset the FPS counter
	        lastFPS += 1000; //add one second
	    }
	    fps++;
	}
	public static float getFPS(){
		return fpsCheck;
	}
}
