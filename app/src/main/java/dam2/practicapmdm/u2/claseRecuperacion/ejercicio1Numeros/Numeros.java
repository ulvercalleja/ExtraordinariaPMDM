package dam2.practicapmdm.u2.claseRecuperacion.ejercicio1Numeros;

import java.util.ArrayList;


public class Numeros {

    private int numero;

    public Numeros(int numero) {
        this.numero = numero;
    }

    public int getNumero() {
        return numero;
    }

    public static ArrayList<Numeros> mostrarMinimo(ArrayList<Numeros> numero){
        int numeroMinimo = 150;
        for (Numeros num : numero) {
            if (num.getNumero() < numeroMinimo){
                numeroMinimo = num.getNumero();
            }
        }
        ArrayList<Numeros> resultado = new ArrayList<>();
        resultado.add(new Numeros(numeroMinimo));
        return numero;
    }
}
