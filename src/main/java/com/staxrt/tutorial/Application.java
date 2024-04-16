package com.staxrt.tutorial;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication; 

import com.staxrt.tutorial.corsConfigurationSource; 


/**
 * The type Application.
 */
@SpringBootApplication
public class Application {

	/**
	 * The entry point of application.
	 *
	 * @param args the input arguments
	 */
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		//SpringApplication.run(corsConfigurationSource.class, args);   
	}

}
   