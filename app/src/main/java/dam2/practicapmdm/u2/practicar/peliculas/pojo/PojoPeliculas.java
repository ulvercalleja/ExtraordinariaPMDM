package dam2.practicapmdm.u2.practicar.peliculas.pojo;

import java.io.Serializable;
import java.util.List;

public class PojoPeliculas implements Serializable {
    private String url;
    private String nombre;
    private String descripcion;
    private int estrellas;

    private List<PojoActores> actores;

    public String getUrl() {
        return url;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public int getEstrellas() {
        return estrellas;
    }

    public List<PojoActores> getActores() {
        return actores;
    }
}
