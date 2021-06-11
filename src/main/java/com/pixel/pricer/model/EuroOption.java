package com.pixel.pricer.model;

import java.util.Random;

import com.pixel.pricer.utils.Utils;

public class EuroOption extends UnderlyingAsset {

	double spot; // S_0
	double strike; // K
	double maturity; // T
	double interest; // r
	double volatility; // sigma
	
	
	
	public EuroOption() {
		super();
	}

	public EuroOption(double spot, double strike, double maturity, double interest, double volatility) {
		super();
		this.spot = spot;
		this.strike = strike;
		this.maturity = maturity;
		this.interest = interest;
		this.volatility = volatility;
	}

	public double callPriceBS() {
		return (spot * Utils.normalCumulativeDF(d_1())) - (strike * Math.exp(-interest * maturity) * Utils.normalCumulativeDF(d_2()));
	}
	
	public double putPriceBS() {
		return (strike * Math.exp(-interest * maturity) * Utils.normalCumulativeDF(-d_2())) - (spot * Utils.normalCumulativeDF(-d_1()));
	}
	
	public double callPriceMC(int nbSteps) {
		
		Random rand = new Random();
		double timeStep = maturity / nbSteps;
		
		double val = 0;
		double res = 0;
		double r = 0;
		
		for (int i = 0; i < nbSteps; i++) {
			r = rand.nextGaussian();
			//System.out.println("rand = " + r);
			val = spot * Math.exp(((interest - Math.pow(volatility, 2) / 2) * timeStep) + (volatility * Math.sqrt(timeStep) * r));
			//System.out.println("val = " + val);
			res = Math.max((val - strike), 0);
			//System.out.println("C res = " + res + " -> " + (val - strike));
		}
		
		return res;
	}
	
	public double putPriceMC(int nbSteps) {
		Random rand = new Random();
		double timeStep = maturity / nbSteps;
		
		double val = 0;
		double res = 0;
		double r = 0;
		
		for (int i = 0; i < nbSteps; i++) {
			r = rand.nextGaussian();
			//System.out.println("rand = " + r);
			val = spot * Math.exp(((interest - Math.pow(volatility, 2) / 2) * timeStep) + (volatility * Math.sqrt(timeStep) * r));
			//System.out.println("val = " + val);
			res = Math.max((strike - val), 0);
			//System.out.println("P res = " + res + " -> " + (strike - val));
		}
		
		return res;
	}
	
	// BS
	private double d_1 () {
		
		return (Math.log(spot/strike)+(interest + Math.pow(volatility, 2)/2)*maturity)/(volatility*Math.sqrt(maturity));
	}
	
	private double d_2 () {
		return (Math.log(spot/strike)+(interest - Math.pow(volatility, 2)/2)*maturity)/(volatility*Math.sqrt(maturity));
		//return d_1() - (volatility*Math.sqrt(maturity));
	}
	// ------------ BS
	
	// MC
	
	
	
	
	//------------- MC
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("Option {");
		sb.append("Spot: ");
		sb.append(spot);
		sb.append(", Strike: ");
		sb.append(strike);
		sb.append(", Maturity: ");
		sb.append(maturity);
		sb.append(", Interest: ");
		sb.append(interest);
		sb.append(", Volatility: ");
		sb.append(volatility);
		sb.append("}\n");
		return sb.toString();
	}
	
}
