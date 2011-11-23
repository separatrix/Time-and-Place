package is.hi.lucky7.timeandplace;

import java.lang.Math;

public class router {
	
	/*
	 * Simplified method to find the time it takes to travel between two coordinates, given an average speed.
	 */
	
	public static long SimpleFindTime(double srcLat, double srcLon, double desLat, double desLon, double avgSpeed)
	{
		// Let's begin by converting the numbers we need to radians.
		double dLat = Math.toRadians(desLat - srcLat);
		double dLon = Math.toRadians(desLon - srcLon);
		double lat1 = Math.toRadians(srcLat);
		double lat2 = Math.toRadians(desLat);
		
		// Let's find the distance between our coordinates, depending on where on Earth we are.
		double a = Math.sin(dLat/2) * Math.sin(dLat/2) +
				Math.sin(dLon/2) * Math.sin(dLon/2) * Math.cos(lat1) * Math.cos(lat2);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
		double d = 6371 * c;
		
		// Factor in the average speed, and return the time in milliseconds.
		double t = d / avgSpeed;
		long msecs = (long)(t * 60 * 60 * 1000);
		return msecs;
	}
	
}