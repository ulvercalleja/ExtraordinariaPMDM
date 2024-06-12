package dam2.practicapmdm.u2.practicar.peliculas;

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
import dam2.practicapmdm.u2.examenes.examenOrdinario.ejercicio2.CuadrosViewModel;
import dam2.practicapmdm.u2.practicar.peliculas.api.PeliculasApi;
import dam2.practicapmdm.u2.practicar.peliculas.api.PeliculasRepo;
import dam2.practicapmdm.u2.practicar.peliculas.pojo.PojoPeliculas;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Peliculas extends AppCompatActivity {

    private TextView tvError;
    private RecyclerView rvPeliculas;
    private PeliculasAdapter peliculasAdapter;
    private List<PojoPeliculas> listaPeliculas = new ArrayList<>();
    ProgressBar pbCargando;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.practicar_peliculas);

        rvPeliculas = findViewById(R.id.practicarpeliculasRV);
        rvPeliculas.setLayoutManager(new LinearLayoutManager(this));
        pbCargando = findViewById(R.id.practicarpeliculaspbCargando);
        tvError = findViewById(R.id.practicarpeliculasvError);

        getPeliculas();
    }

    private void getPeliculas(){
        CuadrosViewModel mvvm = new ViewModelProvider(this).get(CuadrosViewModel.class);

        mvvm.getCuadro().observe(this, integer -> {
            pbCargando.setVisibility(View.INVISIBLE);
            rvPeliculas.setVisibility(View.VISIBLE);
        });

        pbCargando.setVisibility(View.VISIBLE);
        rvPeliculas.setVisibility(View.INVISIBLE);

        PeliculasRepo repoPeli = PeliculasApi.getInstancia().create(PeliculasRepo.class);

        Call<List<PojoPeliculas>> call = repoPeli.getPeliculas();

        call.enqueue(new Callback<List<PojoPeliculas>>(){

            @Override
            public void onResponse(Call<List<PojoPeliculas>> call, Response<List<PojoPeliculas>> response) {
                if (!response.isSuccessful()){
                    tvError.setText("Codigo: " + response.code());
                    peliculasAdapter = new PeliculasAdapter(listaPeliculas, Peliculas.this);
                    rvPeliculas.setAdapter(peliculasAdapter);
                    mvvm.cargaCuadro();
                    return;
                }

                List<PojoPeliculas> listaPeliculas = response.body();
                peliculasAdapter = new PeliculasAdapter(listaPeliculas, Peliculas.this);
                rvPeliculas.setAdapter(peliculasAdapter);
            }

            @Override
            public void onFailure(Call<List<PojoPeliculas>> call, Throwable t) {
                tvError.setText(t.getMessage());
            }
        });
    }
}