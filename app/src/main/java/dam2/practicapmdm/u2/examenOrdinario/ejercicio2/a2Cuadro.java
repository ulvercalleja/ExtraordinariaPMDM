package dam2.practicapmdm.u2.examenOrdinario.ejercicio2;

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
import dam2.practicapmdm.u2.examenOrdinario.ejercicio2.api.CuadroApi;
import dam2.practicapmdm.u2.examenOrdinario.ejercicio2.api.CuadroRepo;
import dam2.practicapmdm.u2.examenOrdinario.ejercicio2.pojo.PojoCuadro;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class a2Cuadro extends AppCompatActivity {
    public static final String ID_DETALLE="ID";
    private TextView tvError;
    private RecyclerView rvAtracciones;
    private CuadroAdapter cuadroAdapter;
    private List<PojoCuadro> listaCuadro = new ArrayList<>();
    ProgressBar pbCargando;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.examen_ord_cuadro_a2);

        rvAtracciones = findViewById(R.id.a2excuadrosRV);
        rvAtracciones.setLayoutManager(new LinearLayoutManager(a2Cuadro.this));
        pbCargando = findViewById(R.id.a2expbCargando);
        tvError = findViewById(R.id.a2extvError);

        getCuadros();
    }

    private void getCuadros(){
        CuadrosViewModel mvvm = new ViewModelProvider(this).get(CuadrosViewModel.class);

        mvvm.getCuadro().observe(this, integer -> {
            pbCargando.setVisibility(View.INVISIBLE);
            rvAtracciones.setVisibility(View.VISIBLE);
        });

        pbCargando.setVisibility(View.VISIBLE);
        rvAtracciones.setVisibility(View.INVISIBLE);

        CuadroRepo repoCuadro = CuadroApi.getInstancia().create(CuadroRepo.class);

        Call<List<PojoCuadro>> call = repoCuadro.getCuadro();

        call.enqueue(new Callback<List<PojoCuadro>>() {
            @Override
            public void onResponse(Call<List<PojoCuadro>> call, Response<List<PojoCuadro>> response) {
                if (!response.isSuccessful()){
                    tvError.setText("Codigo: " + response.code());
                    cuadroAdapter = new CuadroAdapter(listaCuadro, a2Cuadro.this);
                    rvAtracciones.setAdapter(cuadroAdapter);
                    mvvm.cargaCuadro();
                    return;
                }

                List<PojoCuadro> listaAtracciones = response.body();
                cuadroAdapter = new CuadroAdapter(listaAtracciones, a2Cuadro.this);
                rvAtracciones.setAdapter(cuadroAdapter);
            }

            @Override
            public void onFailure(Call<List<PojoCuadro>> call, Throwable t) {
                tvError.setText(t.getMessage());
            }
        });
    }
}