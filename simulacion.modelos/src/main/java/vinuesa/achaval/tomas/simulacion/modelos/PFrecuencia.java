package vinuesa.achaval.tomas.simulacion.modelos;

import org.apache.commons.math3.distribution.ChiSquaredDistribution;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PFrecuencia {

    public static boolean realizarPrueba(double[] numeros) {
        boolean resultado = false;
        
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese la cantidad de subintervalos n: ");
        int n = scanner.nextInt();

        // Crear N listas para almacenar números en intervalos
        List<List<Double>> intervalos = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            intervalos.add(new ArrayList<>());
        }

        double anchoIntervalo = 1.0 / n;

        // Recorrer el arreglo de números y asignarlos a los intervalos
        for (double numero : numeros) {
            int indiceIntervalo = (int) (numero / anchoIntervalo);
            if (indiceIntervalo < n) {
                intervalos.get(indiceIntervalo).add(numero);
            }
        }

        for (int i = 0; i < n; i++) {
            System.out.println("Intervalo " + (i + 1) + ": " + intervalos.get(i));
        }
        int[] Fo = new int[n];
        for(int i= 0; i<n; i ++) {
        	Fo[i]= intervalos.get(i).size();
        }

        int Fe = (int)numeros.length/n;
        
        double X = 0.0;
        for(int i= 0; i<n; i ++) {
        	System.out.println("FO: " + Fo[i] + " FE: " + Fe);
        	X = X +  ((Fo[i] - Fe) * (Fo[i] - Fe)) * (1.0 / Fe);
        }
        		
        System.out.println("Xo: " + X);
        
        int Gl = n - 1; // grado de libertad
        System.out.println("Ingrese el Nivel de significado");
        double Ns = scanner.nextDouble();

        ChiSquaredDistribution chiSquaredDistribution = new ChiSquaredDistribution(Gl);
        double chiSquaredAlpha = chiSquaredDistribution.inverseCumulativeProbability(1 - Ns);
        System.out.println("Valor crítico: " + chiSquaredAlpha);

        if (X < chiSquaredAlpha) {
        	System.out.println("\u001B[32m" + "PRUEBA SUPERADA" + "\u001B[0m");
        	resultado = true;
        }else {
        	System.out.println("\u001B[31m" + "PRUEBA NO SUPERADA" + "\u001B[0m");
        }
        return resultado;
    }
}
