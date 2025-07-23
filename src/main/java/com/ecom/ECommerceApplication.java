package com.ecom;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication   //Starts Spring context and auto-configuration
public class ECommerceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ECommerceApplication.class, args);//Starts the embedded server (like Tomcat).Loads the ApplicationContext and all bean
	}
	//  /logout -->for logout

}
