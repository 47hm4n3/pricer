package com.pixel.pricer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.pixel.pricer.model.EuroOption;

@SpringBootApplication
public class PricerApplication {

	public static void main(String[] args) {
		
		double pVal = 0;
		double cVal = 0;
		int M = 20;
		int N = 1_000_000_000;
		
		SpringApplication.run(PricerApplication.class, args);
		
		
		EuroOption opt = new EuroOption(5, 20, 2, 0.02, 0.05);

		System.out.println(opt.toString());
		
		System.out.println("BS p : " + opt.putPriceBS());
		System.out.println("BS c : " + opt.callPriceBS());
		
		for (int i =0; i < N; i++) {
			pVal += opt.putPriceMC(M);
			cVal += opt.callPriceMC(M);
		}
		
		System.out.println("MC p : " + pVal/N);
		System.out.println("MC c : " + cVal/N);
		
	}

}
