package vinuesa.achaval.tomas.simulacion.modelos;

public class GeneradorCongruencialMultiplicativo extends Generador {

    public GeneradorCongruencialMultiplicativo(double semillaX, double multiplicadorA, double moduloM) {
        super(semillaX, multiplicadorA, moduloM);
    }//Constructor de la clase (igual a la del padre)
    @Override
    public double obtenerAleatorio() { //método propio para generar números pseudoaleatorios
        semillaX = (multiplicadorA * semillaX) % moduloM;
        return semillaX; //Se eliminó la variable ineficiente "resultadoXn1". Con semillaX se puede trabajar del mismo modo
    }
} //fin clase

