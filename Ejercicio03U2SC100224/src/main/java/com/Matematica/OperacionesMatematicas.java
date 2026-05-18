package com.Matematica;

public class OperacionesMatematicas {
	
	 protected double suma(double a, double b) {
	        return a + b;
	    }
	    
	    protected double resta(double a, double b) {
	        return a - b;
	    }
	    
	    protected double multiplicacion(double a, double b) {
	        return a * b;
	    }
	    
	    protected double division(double a, double b) {
	        // Controlar división por cero
	        if (b == 0) {
	            System.out.println("Error: División por cero no permitida");
	            return 0;
	        }
	        // Controlar decimales - redondear a 2 decimales
	        double resultado = a / b;
	        return Math.round(resultado * 100.0) / 100.0;

}
	    }
