package Information;

import loops.GameLoop;

public class Delta {
	static double t = 0.0;
	static final double dt = 0.01;
	static double currentTime = ClientTime.getTime();
	static double accumulator;

	public static void addDelta()
	{
	    double newTime = ClientTime.getTime();
	    double frameTime = newTime - currentTime;
	    currentTime = newTime;
	    accumulator += frameTime;

	    while ( accumulator >= dt )
	    {
	        accumulator -= dt;
	        t += dt;
	    }
	}
	
}
