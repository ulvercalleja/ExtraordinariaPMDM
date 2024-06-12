package dam2.practicapmdm.u2.examenes.examen2Trimestre.ejercicio2.pojo;

import java.io.Serializable;
import java.util.List;


public class PojoCripto implements Serializable {

    private String nombre;
    private String euros;

    private List<PojoEstafados> estafados;

    public String getNombre() {
        return nombre;
    }

    public String getEuros() {
        return euros;
    }

    public List<PojoEstafados> getEstafados() {
        return estafados;
    }
}
