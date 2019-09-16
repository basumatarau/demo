package com.vironit.basumatarau;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SslDemoApplication {

	public static void main(String[] args) {
		SpringApplication springApplication = new SpringApplication(SslDemoApplication.class);
		springApplication.run(args);

		//SpringApplication.run(SslDemoApplication.class, args);
	}

}
