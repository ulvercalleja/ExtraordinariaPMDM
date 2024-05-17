package dam2.practicapmdm.u2.practicar.dragonball.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DragonBallApi {

    private static Retrofit retrofit;
    private static final String BASE_URL = "https://www.dragonball-api.com/";

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
