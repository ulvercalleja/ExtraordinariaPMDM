package dam2.practicapmdm.u2.claseRecuperacion.ejercicio3Animales;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Animales {

    private String nombre;
    private String tipo;
    private String color;

    public Animales(String nombre, String tipo, String color) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.color = color;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public static ArrayList<Animales> generarDatos(){
        ArrayList<Animales> animales = new ArrayList<Animales>();
        animales.add(new Animales("Tobby", "Perro", "Marrón"));
        animales.add(new Animales("Sara", "Girafa", "Amarilla"));
        animales.add(new Animales("Paco", "Elefante", "Gris"));
        animales.add(new Animales("Pedro", "Mapache", "Blanco y negro"));
        animales.add(new Animales("Rocky", "Perro", "Marron"));
        animales.add(new Animales("Kiko", "Pajaro", "Verde"));
        animales.add(new Animales("Alfonso", "Lobo", "Gris"));
        animales.add(new Animales("Sergio", "Elefante", "Gris"));

        return animales;
    }

    public static ArrayList<Animales> desordenarDatos(ArrayList<Animales> animales){
        Collections.shuffle(animales);
        return animales;
    }

    public static ArrayList<Animales> ordenarTipo(ArrayList<Animales> animales){
        Comparator<Animales> comparadorPorTipo = new Comparator<Animales>() {
            @Override
            public int compare(Animales a1, Animales a2) {
                return a1.getTipo().compareTo(a2.getTipo());
            }
        };
        Collections.sort(animales, comparadorPorTipo);

        return animales;
    }
}
