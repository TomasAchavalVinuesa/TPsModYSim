package vinuesa.achaval.tomas.simulacion.modelos;

public class PPoker {
	public static boolean realizarPrueba(double[] numeros){
        boolean resultado =false;
        
        int[] numActual = new int[5];
        int[] repeticionesNumero = new int[10];
        int[] clasificaciones =new int[7];
        int[] Fo =new int[7];
        double[] Fe = {0.3024, 0.5040, 0.1080, 0.0720, 0.0090, 0.0045, 0.0001};
        /*
         0 Todos diferentes
         1 un par
         2 dos pares
         3 Tercia
         4 full 
         5 Poker
         6 Quintilla
         */
        
        for(int i = 0; i<numeros.length; i++) {
        	numActual = separarDigitos(numeros[i]);
        	for(int k = 0; k < 10; k++) {
        		repeticionesNumero[k] = 0;
        	}
        	for(int j = 0; j<5; j++) {
        		repeticionesNumero[numActual[j]]++;
        	}
        	clasificaciones[0] = clasificaciones[0] + todosDiferentes(numActual);
        	clasificaciones[1] = clasificaciones[1] + unPar(repeticionesNumero);
        	clasificaciones[2] = clasificaciones[2] + dosPares(repeticionesNumero);
        	clasificaciones[3] = clasificaciones[3] + tercia(repeticionesNumero);
        	clasificaciones[4] = clasificaciones[4] + full(repeticionesNumero);
        	clasificaciones[5] = clasificaciones[5] + poker(repeticionesNumero);
        	clasificaciones[6] = clasificaciones[6] + quintilla(repeticionesNumero);
        }
        for(int i = 0; i<7; i++) {
        	System.out.println("clasificación numero: "+ i + " tiene la cantidad: "+ clasificaciones[i]);
        }
        for(int i = 0; i <7; i++) {
        	Fo[i] = clasificaciones[i]/numeros.length;
        }
        double X = 0;
        for(int i = 0; i<7; i++) {
        	X = X +  ((Fo[i] - Fe[i]) * (Fo[i] - Fe[i]) * (1.0 / Fe[i]));
        }
        System.out.println("X vale: " + X);
        return resultado;
	}
      
//separa Numeros 
    public static int[] separarDigitos(double numero) {
    	
        int numeroEntero = (int) (numero * 100000);
        int[] digitos = new int[5];
        if(numero == 1.0) {
    		digitos[0] = 0;
            digitos[1] = 0;
            digitos[2] = 0;
            digitos[3] = 0;
            digitos[4] = 0;
    		return digitos;
    	}
        digitos[0] = numeroEntero / 10000;         // Primer dígito después de la coma
        digitos[1] = (numeroEntero / 1000) % 10;   // Segundo dígito
        digitos[2] = (numeroEntero / 100) % 10;    // Tercer dígito
        digitos[3] = (numeroEntero / 10) % 10;     // Cuarto dígito
        digitos[4] = numeroEntero % 10;            // Quinto dígito

        return digitos;
    }
    
    public static int todosDiferentes(int[] digitos) {
        for (int i = 0; i < digitos.length; i++) {
            for (int j = i + 1; j < digitos.length; j++) {
                if (digitos[i] == digitos[j]) {
                    return 0; // Si se encuentra un dígito repetido, devuelve 0
                }
            }
        }
        
        return 1; // Si no se encontraron dígitos repetidos, devuelve 1
    }
    
    public static int quintilla(int[] repeticionesNumero) {
    	for(int i = 0; i<10; i++) {
    		if(repeticionesNumero[i] == 5) {
    			return 1;
    		}
    	}
    	return 0;
    }
    
    public static int unPar(int[] repeticionesNumero) {
    	for(int i = 0; i<10; i++) {
    		if(repeticionesNumero[i] == 2 ) {
    			boolean temp = false;
    			for (int w = 0; w< 10; w++) {
           			if(repeticionesNumero[w] == 2 && w!=i || repeticionesNumero[w] == 3) {
           				temp = true;
           			}
    			}
    			if (temp) {
    				return 0;
    			}else {
    				return 1;
    			}
    		}
    	}	
    	return 0;
    }
    
    public static int dosPares(int[] repeticionesNumero) {
    	for(int i = 0; i<10; i++) {
    		if(repeticionesNumero[i] == 2) {
    			boolean temp = false;
    			for (int w = 0; w< 10; w++) {
           			if(repeticionesNumero[w] == 2 && w!=i) {
           				temp = true;
           			}
    			}
    			if (temp) {
    				return 1;
    			}else {
    				return 0;
    			}
    		}
    	}
    	return 0;
    }
    
    public static int poker(int[] repeticionesNumero) {
    	for(int i = 0; i<10; i++) {
    		if(repeticionesNumero[i] == 4) {
    			return 1;
    		}
    	}
    	return 0;
    }
    
    public static int tercia(int[] repeticionesNumero) {
    	for(int i = 0; i<10; i++) {
    		if(repeticionesNumero[i] == 3) {
    			boolean temp = false;
    			for (int w = 0; w< 10; w++) {
           			if(repeticionesNumero[w] == 2 ) {
           				temp = true;
           			}
    			}
    			if (temp) {
    				return 0;
    			}else {
    				return 1;
    			}
    		}
    	}
    	return 0;
    }
    
    public static int full(int[] repeticionesNumero) {
    	for(int i = 0; i<10; i++) {
    		if(repeticionesNumero[i] == 3) {
    			for (int w = 0; w< 10; w++) {
           			if(repeticionesNumero[w] == 2) {
           				return 1;
           			}
    			}
    		}
    	}
    	return 0;
    }

}
