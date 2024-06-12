package dam2.practicapmdm.u2.practicar.peliculas.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PeliculasApi {

    private static Retrofit retrofit;
    private static final String BASE_URL = "http://192.168.56.102:8000/";

    public static Retrofit getInstancia() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
