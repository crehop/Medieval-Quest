package Information;

import java.math.RoundingMode;
import java.text.NumberFormat;

import loops.GameLoop;

public class EnhancedDelta {
    //This value would probably be stored elsewhere.
    final static double GAME_HERTZ = 60.0;
    //Calculate how many ns each frame should take for our target game hertz.
    final static double TIME_BETWEEN_UPDATES = 1000000000 / GAME_HERTZ;
    //At the very most we will update the game this many times before a new render.
    //If you're worried about visual hitches more than perfect timing, set this to 1.
    final static int MAX_UPDATES_BEFORE_RENDER = 5;
    static int fps = 0;
    static double startSeconds = System.nanoTime()/1000000000;
    static double now = System.nanoTime();
    static int updateCount = 0;
    final static double TARGET_FPS = 60;
    final static double TARGET_TIME_BETWEEN_RENDERS = 1000000000 / TARGET_FPS;
    static double lastUpdateTime = 0;
    static double lastRenderTime = 0;
    static double lastSecondTime = 0;
    static double thisSecond = 0;
    //We will need the last update time.
	  public static void checkDelta()
	   {
	      NumberFormat format = NumberFormat.getInstance();

	      lastUpdateTime = System.nanoTime();
	      //Store the last time we rendered.
	      lastRenderTime = System.nanoTime();
	      //If we are able to get as high as this FPS, don't render again.
	      
	      //Simple way of finding FPS.
	      lastSecondTime = (double) (lastUpdateTime / 1000000000);
	      
	      while (Main.Main.isOpen())
	      {
	         now = System.nanoTime();
	         updateCount = 0;
	         format.setRoundingMode(RoundingMode.DOWN);
	         format.setMaximumFractionDigits(2);
             Main.Console.setLine8("" + format.format(((lastUpdateTime / 1000000000) - startSeconds)));
	         
	         if (!(Main.Main.isPaused()))
	         {
	             //Do as many game updates as we need to, potentially playing catchup.
	            while( now - lastUpdateTime > TIME_BETWEEN_UPDATES && updateCount < MAX_UPDATES_BEFORE_RENDER )
	            {
	               GameLoop.physicsUpdates();
	               lastUpdateTime += TIME_BETWEEN_UPDATES;
	               updateCount++;
	            }
	   
	            //If for some reason an update takes forever, we don't want to do an insane number of catchups.
	            //If you were doing some sort of game that needed to keep EXACT time, you would get rid of this.
	            if ( now - lastUpdateTime > TIME_BETWEEN_UPDATES)
	            {
	               lastUpdateTime = now - TIME_BETWEEN_UPDATES;
	            }
	         
	            //Update the frames we got.
	            thisSecond = (float) (lastUpdateTime / 1000000000);
	            if (thisSecond > lastSecondTime)
	            {
	               lastSecondTime = thisSecond;
	            }
	         
	            //Yield until it has been at least the target time between renders. This saves the CPU from hogging.
	            while ( now - lastRenderTime < TARGET_TIME_BETWEEN_RENDERS && now - lastUpdateTime < TIME_BETWEEN_UPDATES)
	            {
	               Thread.yield();
	            
	               //This stops the app from consuming all your CPU. It makes this slightly less accurate, but is worth it.
	               //You can remove this line and it will still work (better), your CPU just climbs on certain OSes.
	               //FYI on some OS's this can cause pretty bad stuttering. Scroll down and have a look at different peoples' solutions to this.
	               try {Thread.sleep(1);} catch(Exception e) {} 
	            
	               now = System.nanoTime();
	            }
	         }
	      }
	   }
}
