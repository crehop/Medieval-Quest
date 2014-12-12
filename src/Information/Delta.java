package Information;

import loops.GameLoop;

public class Delta {
	static double t = 0.0;
	static final double dt = 0.01;
	static double currentTime = ClientTime.getTime();
	static double accumulator;
	static double accumulatorLast;

	public static void addDelta()
	{
	    double newTime = ClientTime.getTime();
	    double frameTime = newTime - currentTime;
	    currentTime = newTime;
	    accumulator += frameTime;
	    accumulatorLast = 0;
	    while ( accumulator >= dt )
	    {
	    	accumulatorLast++;
	        accumulator -= dt;
	        t += dt;
	    }
	}
	public static double getDifference(){
		return accumulatorLast;
	}
	
}
