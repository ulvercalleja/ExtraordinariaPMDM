package dam2.practicapmdm.u2.examenOrdinario.ejercicio2.pojo;

import java.io.Serializable;

public class PojoCuadro implements Serializable {

    public PojoCuadro(String id, String url, String nombre, Double precio, String fecha, String tecnica, String color) {
        this.id = id;
        this.url = url;
        this.nombre = nombre;
        this.precio = precio;
        this.fecha = fecha;
        this.tecnica = tecnica;
        this.color = color;
    }

    private String id;
    private String url;
    private String nombre;
    private Double precio;
    private String fecha;
    private String tecnica;
    private String color;

    public String getId() {
        return id;
    }
    public String getUrl() {
        return url;
    }
    public String getNombre() {
        return nombre;
    }
    public Double getPrecio() {
        return precio;
    }
    public String getFecha() {
        return fecha;
    }

    public String getTecnica() {
        return tecnica;
    }

    public String getColor() {
        return color;
    }
}
