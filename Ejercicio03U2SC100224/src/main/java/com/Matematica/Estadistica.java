package com.Matematica;

public class Estadistica {
	
    private int numero1;
    private int numero2;
    
    public Estadistica(int numero1, int numero2) {
        this.numero1 = numero1;
        this.numero2 = numero2;
    }
    
    public double promedio() {
        return (numero1 + numero2) / 2.0;
    }
}

