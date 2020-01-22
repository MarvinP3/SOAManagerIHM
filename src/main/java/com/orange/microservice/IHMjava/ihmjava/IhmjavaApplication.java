package com.orange.microservice.IHMjava.ihmjava;





import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
@EnableAutoConfiguration
@Configuration
@ComponentScan
@SpringBootApplication
public class IhmjavaApplication extends WebMvcConfigurerAdapter {
	public static void main(String[] args) {
		SpringApplication.run(IhmjavaApplication.class, args);
		System.out.println("toto");
		
	        
		   }
		  }       



