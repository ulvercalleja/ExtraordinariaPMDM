package dam2.practicapmdm.u2.examenes.examen2Trimestre.ejercicio2.api;

import java.util.List;

import dam2.practicapmdm.u2.examenes.examen2Trimestre.ejercicio2.pojo.PojoCripto;
import dam2.practicapmdm.u2.examenes.examen2Trimestre.ejercicio2.pojo.PojoEstafados;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface CriptoRepo {
    @GET("/api/cripto/{id}")
    Call<PojoCripto> getCripto(@Path("id") String id);

    @GET("/api/cripto/{id}")
    Call<PojoCripto> getEstafados(@Path("id") String id);
}
