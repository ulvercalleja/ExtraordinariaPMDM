package dam2.practicapmdm.u2.examenes.examenOrdinario.ejercicio2.api;

import java.util.List;

import dam2.practicapmdm.u2.examenes.examenOrdinario.ejercicio2.pojo.PojoCuadro;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface CuadroRepo {
    @GET("/api/cuadro/")
    Call<List<PojoCuadro>> getCuadro();

    @GET("/api/cuadro/{id}")
    Call<PojoCuadro> getDetalleCuadro(@Path("id") String id);
}
