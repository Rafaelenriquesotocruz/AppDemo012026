package com.Matematica;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.Scanner;


@SpringBootApplication
public class Ejercicio03U2Sc100224Application {
	
    // Ejercicio 9: Función para solicitar datos
    public static double solicitarDato(int numero) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Ingrese el número " + numero + ": ");
        return sc.nextDouble();
    }

	public static void main(String[] args) {
		SpringApplication.run(Ejercicio03U2Sc100224Application.class, args);
		
		Scanner scanner = new Scanner(System.in);
		
        // Ejercicio 1: Cast implícito
        System.out.println("\n=== Ejercicio 1: Cast implícito ===");
        byte b = 100;
        short s = b;
        int i = s;
        long l = i;
        float f = l;
        double d = f;
        System.out.println("byte -> short -> int -> long -> float -> double");
        System.out.println("Valor final (double): " + d + "\n");
        
        
        // Ejercicio 2: Cast explícito
        System.out.println("=== Ejercicio 2: Cast explícito ===");
        double d2 = 100.99;
        float f2 = (float) d2;
        long l2 = (long) f2;
        int i2 = (int) l2;
        short s2 = (short) i2;
        byte b2 = (byte) s2;
        System.out.println("double -> float -> long -> int -> short -> byte");
        System.out.println("Valor final (byte): " + b2 + "\n");
        
        
        // Ejercicio 3: Contar caracteres
        System.out.println("=== Ejercicio 3: Contar caracteres ===");
        System.out.print("Ingrese su nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese su apellido: ");
        String apellido = scanner.nextLine();
        System.out.print("Ingrese su dirección: ");
        String direccion = scanner.nextLine();
        System.out.println("Sus nombres tienen: " + nombre.length() + " caracteres");
        System.out.println("Sus apellidos tienen: " + apellido.length() + " caracteres");
        System.out.println("Su dirección tiene: " + direccion.length() + " caracteres\n");
        
        
        // Ejercicio 4: Suma y promedio de arreglo
        System.out.println("=== Ejercicio 4: Suma y promedio ===");
        int[] datos = {100, 200, 150, 45, 50, 5, 40, 4, 25};
        int suma = 0;
        for (int valor : datos) {
            suma += valor;
        }
        double promedio = (double) suma / datos.length;
        System.out.println("Suma de datos: " + suma);
        System.out.println("Promedio de datos: " + promedio + "\n");
        
        
        // Ejercicio 5: Mayúsculas y concatenación
        System.out.println("=== Ejercicio 5: Saludos en mayúsculas ===");
        String[] datosStr = {"hola", "buenos", "dias"};
        StringBuilder saludo = new StringBuilder("Saludos: ");
        for (String palabra : datosStr) {
            saludo.append(palabra.toUpperCase()).append(" ");
        }
        System.out.println(saludo.toString().trim() + "\n");
        
        
        
     // Ejercicio 6: Clase Operaciones
        System.out.println("\n=== Ejercicio 6: Operaciones Matemáticas ===");
        Operaciones op = new Operaciones(10, 5);

        // Mostrar resultados de las 4 operaciones
        System.out.println("La suma es: " + op.suma());
        System.out.println("La resta es: " + op.resta());
        System.out.println("La multiplicación es: " + op.multiplicacion());
        System.out.println("La división es: " + op.division());
        
        
	
     // EJERCICIOS 7, 8 y 9: Implementación conjunta
        System.out.println("\n=== Ejercicios 7, 8, 9: Clases en paquete Matematica ===");

        // EJERCICIO 9: Solicitar números usando UNA función
        double num1 = solicitarDato(1);
        double num2 = solicitarDato(2);
        

        // EJERCICIO 7: OperacionesMatematicas
        OperacionesMatematicas opsMat = new OperacionesMatematicas();
        System.out.println("\n=== Ejercicio 7: Operaciones Matemáticas ===");
        System.out.println("Suma: " + opsMat.suma(num1, num2));
        System.out.println("Resta: " + opsMat.resta(num1, num2));
        System.out.println("Multiplicación: " + opsMat.multiplicacion(num1, num2));
        System.out.println("División: " + opsMat.division(num1, num2));

        
        // EJERCICIO 8: Estadística
        Estadistica est = new Estadistica((int) num1, (int) num2);
        System.out.println("\n=== Ejercicio 8: Estadística ===");
        System.out.println("Promedio: " + est.promedio());
        
        scanner.close();
	}
         
        
}
	
//CLASE OPERACIONES PARA EJERCICIO 6 - DENTRO DEL MISMO ARCHIVO
class Operaciones {
 private int a, b;

 public Operaciones(int a, int b) {
     this.a = a;
     this.b = b;
 }

 public int suma() {
     return a + b;
 }

 public int resta() {
     return a - b;
 }

 public int multiplicacion() {
     return a * b;
 }

 public double division() {
     if (b == 0) return 0;
     return (double) a / b;
 }
}