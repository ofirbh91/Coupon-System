package com.jb.couponsys;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class CouponsysApplication {

	public static void main(String[] args) {
		SpringApplication.run(CouponsysApplication.class, args);
		System.out.println("IOC container was loaded");
	}

}
