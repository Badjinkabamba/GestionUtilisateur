package com.sid.gestionUtilisateurs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@ComponentScan(basePackages = {"com.sid.gestionUtilisateurs"})
@SpringBootApplication
public class GestionUtilisateursApplication {

	public static void main(String[] args) {
		SpringApplication.run(GestionUtilisateursApplication.class, args);
	}

}
