package dam2.practicapmdm.u2.practicar.dragonball.api;

import java.util.List;

import dam2.practicapmdm.u2.practicar.dragonball.pojo.PojoDragonBall;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface DragonBallRepo {

    @GET("/api/characters")
    Call<List<PojoDragonBall>> getPersonaje();

    @GET("/api/characters/{id}")
    Call<PojoDragonBall> getPersonajeDetalle(@Path("id") String id);
}
