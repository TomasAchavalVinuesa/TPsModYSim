package vinuesa.achaval.tomas.simulacion.modelos;

public abstract class Generador { //clase implementada por GeneradorCongruencialMixto y GeneradorCongruencialMultiplicativo
    public double multiplicadorA;
    public double moduloM;
    public double semillaX;

    public Generador(double semillaX, double multiplicadorA, double moduloM) { //las 2 clases que heredan implementan como mínimo este constructor
        this.semillaX = semillaX;
        this.multiplicadorA = multiplicadorA;
        this.moduloM = moduloM;
    }

    public abstract double obtenerAleatorio(); //las 2 clases que heredan esta clase, obtienen números aleatorios de distinta forma (van a sobreescribir el método en su clase)

    public int obtenerPeriodo(){ //ambas clases que heredan de esta clase, lo implementan a este método exactamente igual
        double inicial = semillaX; //esta variable no se debe eliminar, ya que el método "obtenerAleatorio()" cambia el valor de semillaX en cada una de sus iteraciones
        int periodo = 0;
        do {
            periodo++; //se eliminó la llamada al método "obtenerAleatorio()" ya que es calculada en el condicional del while
        }while (obtenerAleatorio() != inicial); //si acá fuera "obtenerAleatorio() != semillaX" el ciclo terminaría en la primera ejecución y devolvería siempre 1
        return periodo;
    }

    public double[] obtenerMuestraParcial(int tamanoMuestra){ //ambas clases que heredan de esta clase lo implementan de la misma forma
        double[] muestra = new double[tamanoMuestra];
        for (int i = 0; i<tamanoMuestra; i++){
            muestra[i] = obtenerAleatorio();
        }
        return muestra; //devuelve un array con "tamanoMuestra" como cantidad de elementos y no necesariamente todos los elementos distintos entre si (pueden superar el periodo
    }

    public double[] obtenerMuestraCompleta(){ //ambas clases que heredan de esta clase lo implementan de la misma forma
        int periodo = obtenerPeriodo(); //se almacena el valor del periodo para no tener que calcularlo 3 veces en este método
        double[] muestra = new double[periodo];
        for (int i = 0; i<periodo; i++){
            muestra[i] = obtenerAleatorio();
        }
        return muestra; //devuelve un array con "periodo" como cantidad de elementos y contiene a todos los posibles valores generados con la semillaX del generador una única vez
    }
} //fin clase
