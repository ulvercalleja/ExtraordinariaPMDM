package dam2.practicapmdm.u2.practicar.peliculas.api;

import java.util.List;

import dam2.practicapmdm.u2.practicar.peliculas.pojo.PojoActores;
import dam2.practicapmdm.u2.practicar.peliculas.pojo.PojoPeliculas;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface PeliculasRepo {
    @GET("/api/peliculas")
    Call<List<PojoPeliculas>> getPeliculas();

    @GET("/api/peliculas_related/{id}")
    Call<PojoPeliculas> getDetallePelicula(@Path("id") String id);
}

