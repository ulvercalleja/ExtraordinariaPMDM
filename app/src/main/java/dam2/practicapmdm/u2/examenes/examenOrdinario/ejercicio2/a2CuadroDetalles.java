package dam2.practicapmdm.u2.examenes.examenOrdinario.ejercicio2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.ViewModelProvider;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import dam2.practicapmdm.R;
import dam2.practicapmdm.u2.examenes.examenOrdinario.ejercicio2.api.CuadroApi;
import dam2.practicapmdm.u2.examenes.examenOrdinario.ejercicio2.api.CuadroRepo;
import dam2.practicapmdm.u2.examenes.examenOrdinario.ejercicio2.pojo.PojoCuadro;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class a2CuadroDetalles extends AppCompatActivity {
    TextView tvUrl, tvPrecio, tvFecha, tvError;
    ProgressBar pbCargando;
    private List<PojoCuadro> listaCuadro = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.examen_ord_cuadro_detalles_a2);

        tvUrl = findViewById(R.id.eocdtvUrl);
        tvPrecio = findViewById(R.id.eocdtvPrecio);
        tvFecha = findViewById(R.id.eocdtvFecha);
        pbCargando = findViewById(R.id.eocdpbCargando);
        tvError = findViewById(R.id.eocdtvError);

        getDetalles();
    }

    private void getDetalles(){
        ConstraintLayout layout = findViewById(R.id.eocdclConstraint);
        CuadrosViewModel mvvm = new ViewModelProvider(this).get(CuadrosViewModel.class);

        mvvm.getCuadro().observe(this, integer -> {
            pbCargando.setVisibility(View.INVISIBLE);
        });

        pbCargando.setVisibility(View.VISIBLE);
        CuadroRepo repoCuadro = CuadroApi.getInstancia().create(CuadroRepo.class);

        String idString = getIntent().getStringExtra(a2Cuadro.ID_DETALLE);
        Call<PojoCuadro> call = repoCuadro.getDetalleCuadro(idString);

        call.enqueue(new Callback<PojoCuadro>() {
            @Override
            public void onResponse(Call<PojoCuadro> call, Response<PojoCuadro> response) {
                pbCargando.setVisibility(View.INVISIBLE);
                if (response.isSuccessful()) {
                    PojoCuadro cuadro = response.body();
                    if (cuadro != null) {
                        // Update UI with fetched details

                        tvUrl.setText(cuadro.getUrl());
                        tvPrecio.setText(String.valueOf(cuadro.getPrecio()));
                        tvFecha.setText(cuadro.getFecha());
                        int color = Color.parseColor(cuadro.getColor());
                        layout.setBackgroundColor(color);
                    } else {
                        tvError.setText("No se encontraron detalles para este cuadro.");
                    }
                } else {
                    tvError.setText("Error: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<PojoCuadro> call, Throwable t) {
                pbCargando.setVisibility(View.INVISIBLE);
                tvError.setText("Error al obtener detalles: " + t.getMessage());
            }
        });
    }
}