package com.pixel.pricer.utils;

public final class Utils {

	//final values used to calculate the standard normal cumulative distribution function
	private static final double P = 0.2316419;
	private static final double B1 = 0.319381350;
	private static final double B2 = -0.356563782;
	private static final double B3 = 1.781477937;
	private static final double B4 = -1.821255978;
	private static final double B5 = 1.330274429;

	public static double normalCumulativeDF(double param) {
		double t = 1 / (1 + P * Math.abs(param));
		double t1 = B1 * Math.pow(t, 1);
		double t2 = B2 * Math.pow(t, 2);
		double t3 = B3 * Math.pow(t, 3);
		double t4 = B4 * Math.pow(t, 4);
		double t5 = B5 * Math.pow(t, 5);
		double b = t1 + t2 + t3 + t4 + t5;
		double res = 1 - (normalStandardDF(param) * b);
		
		return (param < 0) ? 1 - res : res;
	}

	private static double normalStandardDF(double param) {
		return Math.exp(-0.5 * Math.pow(param, 2)) / Math.sqrt(2 * Math.PI);
	}
	
}
