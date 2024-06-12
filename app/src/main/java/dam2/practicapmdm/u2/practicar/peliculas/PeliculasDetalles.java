package dam2.practicapmdm.u2.practicar.peliculas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import dam2.practicapmdm.R;
import dam2.practicapmdm.u2.practicar.peliculas.api.PeliculasApi;
import dam2.practicapmdm.u2.practicar.peliculas.api.PeliculasRepo;
import dam2.practicapmdm.u2.practicar.peliculas.pojo.PojoActores;
import dam2.practicapmdm.u2.practicar.peliculas.pojo.PojoPeliculas;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PeliculasDetalles extends AppCompatActivity {

    private TextView tvError;
    private RecyclerView rvActores;
    private PeliculasDetallesAdapter peliculaDetallesAdapter;
    private List<PojoActores> listaActores = new ArrayList<>();
    private ProgressBar pbCargando;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.practicar_peliculas_detalles);

        rvActores = findViewById(R.id.practicarpeliculasdetallesRV);
        rvActores.setLayoutManager(new LinearLayoutManager(this));
        tvError = findViewById(R.id.practicarpeliculasdetallestvError);
        pbCargando = findViewById(R.id.practicarpeliculasdetallespbCargando);

        getDetalles();
    }

    private void getDetalles() {
        PeliculasViewModel mvvm = new ViewModelProvider(this).get(PeliculasViewModel.class);

        mvvm.getPelicula().observe(this, integer -> {
            pbCargando.setVisibility(View.INVISIBLE);
            rvActores.setVisibility(View.VISIBLE);
        });

        pbCargando.setVisibility(View.VISIBLE);
        rvActores.setVisibility(View.INVISIBLE);

        PeliculasRepo repoPeli = PeliculasApi.getInstancia().create(PeliculasRepo.class);

        String urlString = getIntent().getStringExtra(PeliculasAdapter.ID_URL);
        // Extraer el ID de la URL completa
        String id = extractIdFromUrl(urlString);

        Call<PojoPeliculas> call = repoPeli.getDetallePelicula(id);

        call.enqueue(new Callback<PojoPeliculas>() {

            @Override
            public void onResponse(Call<PojoPeliculas> call, Response<PojoPeliculas> response) {
                pbCargando.setVisibility(View.INVISIBLE);
                if (response.isSuccessful()) {
                    PojoPeliculas pelicula = response.body();
                    if (pelicula != null) {
                        listaActores = pelicula.getActores();
                        peliculaDetallesAdapter = new PeliculasDetallesAdapter(listaActores, PeliculasDetalles.this);
                        rvActores.setAdapter(peliculaDetallesAdapter);
                    } else {
                        tvError.setText("No se encontraron detalles para esta película.");
                    }
                } else {
                    tvError.setText("Error: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<PojoPeliculas> call, Throwable t) {
                pbCargando.setVisibility(View.INVISIBLE);
                tvError.setText("Error al obtener detalles: " + t.getMessage());
            }
        });
    }

    // Método para extraer el ID de la URL
    private String extractIdFromUrl(String urlString) {
        if (urlString != null && urlString.startsWith("http://")) {
            String[] parts = urlString.split("/");
            return parts[parts.length - 1]; // Asumiendo que el ID es la última parte de la URL
        }
        return "";
    }
}
