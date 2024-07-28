package org.diecastfinder.wanted;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class WantedApplication {
	public static void main(String[] args) {
		SpringApplication.run(WantedApplication.class, args);
	}

}
