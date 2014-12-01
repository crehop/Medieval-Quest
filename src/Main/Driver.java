package Main;

import org.lwjgl.Sys;

public class Driver {
	private static long lastFrame;
	private static long getTime(){
		return((Sys.getTime() * 1000)/ Sys.getTimerResolution());
	}
	private static long getDelta(){
		long currentTime = getTime();
		int delta = (int)(currentTime - lastFrame);
		lastFrame = getTime();
		return delta;
	}
	public static void checkForTick(){
	}
	private static void tick1TimesASecond(){
	}
	private static void tick20TimesASecond(){
	}
}
