package com.mycompany.personEntities;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.mycompany.personEntities")
public class PersonEntitiesApplication {

	public static void main(String[] args) {
		SpringApplication.run(PersonEntitiesApplication.class, args);
	}

}
