package com.proyecto.elena;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ElenaApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ElenaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// Aquí puedes poner el código que quieras que se ejecute al arrancar la aplicación
		System.out.println("Bienvenidos al proyecto realizado en el curso 'Desarrollo Web en Entorno Servidor con Spring Boot - Elena 👋");
	}
}
