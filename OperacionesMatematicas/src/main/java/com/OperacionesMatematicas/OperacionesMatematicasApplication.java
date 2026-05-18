package com.OperacionesMatematicas;


import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OperacionesMatematicasApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(OperacionesMatematicasApplication.class, args);
	}
	@Override
	public void run(String... args) throws Exception {
		String nombre = "Rafael Soto";
		System.out.println("Hola Mundo, hola: " + nombre );
		
		double a = 10, b = 5;
        System.out.println("Suma: " + (a + b));
        System.out.println("Resta: " + (a - b));
        System.out.println("Multiplicación: " + (a * b));
        System.out.println("División: " + (a / b));
    }
	}

