package vinuesa.achaval.tomas.simulacion.modelos;

public class GeneradorCongruencialMixto extends Generador {

    double constanteAditivaC; //esta es la diferencia entre ambos generadores (también modificará como se deben elegir estos parámetros)

    public GeneradorCongruencialMixto(double multiplicadorA, double constanteAditivaC, double moduloM, double semillaX) {
        super(semillaX, multiplicadorA, moduloM);
        this.constanteAditivaC = constanteAditivaC; //agrega un elemento propio de este generador
    } //Constructor de la clase


    @Override
    public double obtenerAleatorio() { //método para generar 1 número pseudoaleatorio
        semillaX = (multiplicadorA * semillaX + constanteAditivaC) % moduloM; //relación de recurrencia
        // Actualiza la semillaX para que el próximo resultado pueda variar
        return semillaX; //se eliminó la variable "resultadoXn1", ya que su uso era ineficiente, con semillaX se puede trabajar del mismo modo
    }
} //fin clase
