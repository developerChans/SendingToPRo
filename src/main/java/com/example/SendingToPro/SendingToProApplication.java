package com.example.SendingToPro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SendingToProApplication {
public static void main(String[] args) {
		SpringApplication.run(SendingToProApplication.class, args);

		long heapSize = Runtime.getRuntime().totalMemory();
        System.out.println("HEAP Size(M) : "+ heapSize / (1024*1024) + " MB");
	}

}
