package dam2.practicapmdm.u2.practicar.dragonball;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import dam2.practicapmdm.R;
import dam2.practicapmdm.u2.practicar.dragonball.api.DragonBallApi;
import dam2.practicapmdm.u2.practicar.dragonball.api.DragonBallRepo;
import dam2.practicapmdm.u2.practicar.dragonball.pojo.PojoDragonBall;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DragonBall extends AppCompatActivity {

    public static final String ID_DETALLE="ID";
    private TextView tvError;
    private RecyclerView rvPersonajes;
    private DragonBallAdapter dragonballAdapter;
    private List<PojoDragonBall> listaPersonajes = new ArrayList<>();
    ProgressBar pbCargando;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.practicar_dragon_ball);

        rvPersonajes = findViewById(R.id.practicardragonballRV);
        rvPersonajes.setLayoutManager(new LinearLayoutManager(DragonBall.this));
        pbCargando = findViewById(R.id.practicardragonballpbCargando);
        tvError = findViewById(R.id.practicardragonballtvError);

        getPersonajes();

    }

    public void getPersonajes() {
        DragonBallViewModel mvvm = new ViewModelProvider(this).get(DragonBallViewModel.class);

        mvvm.getPersonaje().observe(this, integer -> {
            pbCargando.setVisibility(View.INVISIBLE);
            rvPersonajes.setVisibility(View.VISIBLE);
        });

        pbCargando.setVisibility(View.VISIBLE);
        rvPersonajes.setVisibility(View.INVISIBLE);

        DragonBallRepo repoDragonBall = DragonBallApi.getInstancia().create(DragonBallRepo.class);

        Call<List<PojoDragonBall>> call = repoDragonBall.getPersonaje();

        call.enqueue(new Callback<List<PojoDragonBall>>() {
            @Override
            public void onResponse(Call<List<PojoDragonBall>> call, Response<List<PojoDragonBall>> response) {
                if (!response.isSuccessful()){
                    tvError.setText("Codigo: " + response.code());
                    dragonballAdapter = new DragonBallAdapter(listaPersonajes, DragonBall.this);
                    rvPersonajes.setAdapter(dragonballAdapter);
                    mvvm.cargaPersonaje();
                    return;
                }

                List<PojoDragonBall> listaPersonajes = response.body();
                dragonballAdapter = new DragonBallAdapter(listaPersonajes, DragonBall.this);
                rvPersonajes.setAdapter(dragonballAdapter);
            }

            @Override
            public void onFailure(Call<List<PojoDragonBall>> call, Throwable t) {
                tvError.setText(t.getMessage());
            }
        });
    }
}