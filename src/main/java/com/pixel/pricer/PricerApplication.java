package com.pixel.pricer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.pixel.pricer.model.EuroOption;

@SpringBootApplication
public class PricerApplication {

	public static void main(String[] args) {
		SpringApplication.run(PricerApplication.class, args);
		
		EuroOption opt = new EuroOption(10, 10, 2, 0.02, 0.05);
		System.out.println(opt.putPriceBS());
		System.out.println(opt.callPriceBS());
		
		double val = 0;
		
		//for (int i =0; i < 20000; i++) {
			//val += opt.callPriceMC(50000);
		//}
		
		
		System.out.println("MC : " + val);
		
	}

}
