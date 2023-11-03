package vinuesa.achaval.tomas.simulacion.modelos;

import org.apache.commons.math3.distribution.NormalDistribution;

/**
 * Hello world!
 *
 */
public class App 
{
	public static void main(String[] args) {
		boolean resultado = false;
        GeneradorCongruencialMixto generador1 = new GeneradorCongruencialMixto(128, 13, 127, 42);
        int periodo = generador1.obtenerPeriodo();
        System.out.println(periodo);

        double[] a = NormalizadorNumeros.normalizar(generador1, periodo, 5);
        for (int i = 0; i< periodo; i++){
            System.out.println("Número generado: "+"\u001B[32m" + a[i] + "\u001B[0m");
        }
        
        System.out.println("<----------------------------------- Se realizará la prueba de los promedios--------------------------------------->");
        //resultado = PPromedio.realizarPrueba(a);
        
        System.out.println("<----------------------------------- Se realizará la prueba de Frecuencias ---------------------------------------->");
        //resultado = resultado & PFrecuencia.realizarPrueba(a);
        
        System.out.println("<----------------------------------- Se realizará la prueba de Kolmogorov-Smirnov --------------------------------->");
        //resultado = resultado & PKolmogorovSmirnov.realizarPrueba(a);
        
        System.out.println("<----------------------------------- Se realizará la prueba del Poker --------------------------------------------->");
        resultado = resultado & PPoker.realizarPrueba(a);

        
        System.out.println("<----------------------------------- Las pruebas han finalizado --------------------------------------------------->");
        
        if(resultado) {
        	System.out.println("El generador ha logrado pasar todas las pruebas");
        }else {
        	System.out.println("El generador NO ha logrado pasar todas las pruebas");
        }
    }
}
