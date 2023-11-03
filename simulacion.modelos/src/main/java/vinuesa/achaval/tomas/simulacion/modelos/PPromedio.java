package vinuesa.achaval.tomas.simulacion.modelos;

import org.apache.commons.math3.distribution.NormalDistribution;
import java.util.Scanner;

public class PPromedio {

    public static boolean realizarPrueba(double[] numerosGenerados){
    	//Declaraciones variables
    	Scanner scanner = new Scanner(System.in); 
    	boolean resultado = false; 
        double valorEsperado= 0.5;
        double varianza = (1.0 /12.0);
        double promedio = 0.0;
        double raizCuadradaUnoDoce = Math.sqrt(varianza);
        double z0 = 0.0;
        NormalDistribution standardNormal = new NormalDistribution(0, 1);
        //Fin declaraciones variables
        
        for (double numero: numerosGenerados){
            promedio += numero;
        }
        promedio /= numerosGenerados.length;
        System.out.println("El promedio es: " + promedio);
        
        System.out.println("Ingrese un valor comprendido entre 0.00 y 1.00 Este será el nivel de tolerancia ( se recomienda 0,05)");
        double alpha = scanner.nextDouble();
        double valorBuscar = 1.00 - (alpha /2);
        double zAlphaOver2 = standardNormal.inverseCumulativeProbability(valorBuscar);
        
        z0 = (promedio - valorEsperado)* Math.sqrt(numerosGenerados.length) / raizCuadradaUnoDoce;        
        z0 = Math.abs(z0);
        
        if (z0 < zAlphaOver2) {
        	System.out.println("\u001B[32m" + "PRUEBA SUPERADA" + "\u001B[0m");
        	resultado = true;
        }else {
        	System.out.println("\u001B[31m" + "PRUEBA NO SUPERADA" + "\u001B[0m");
        }
        
        System.out.println("Valor de z0: " + z0 + " Valor de zAlphaOver2: "+ zAlphaOver2);
        
        return resultado;
    }
}