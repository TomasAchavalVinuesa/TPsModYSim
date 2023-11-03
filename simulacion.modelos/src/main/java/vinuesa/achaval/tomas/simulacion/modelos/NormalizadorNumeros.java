package vinuesa.achaval.tomas.simulacion.modelos;

public class NormalizadorNumeros {

    public static double[] normalizar(Generador generador, int tamanoMuestra, int cantidadDeCeros ){
        double potencia = Math.pow(10.0, cantidadDeCeros); //se deberá añadir control para que cantidadDeCeros sea mayor o igual que 0
        double[] numerosTransformar = generador.obtenerMuestraParcial(tamanoMuestra);
        for (int i = 0; i<tamanoMuestra; i++){
            double auxiliar = numerosTransformar[i] / (generador.moduloM-1);
            numerosTransformar[i] = (Math.floor(auxiliar * potencia)/potencia);
        }
        return  numerosTransformar;
    }
}
