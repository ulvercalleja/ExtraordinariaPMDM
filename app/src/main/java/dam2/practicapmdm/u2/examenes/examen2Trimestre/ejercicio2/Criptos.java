package dam2.practicapmdm.u2.examenes.examen2Trimestre.ejercicio2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import dam2.practicapmdm.R;
import dam2.practicapmdm.u2.examenes.examen2Trimestre.ejercicio2.api.CriptoApi;
import dam2.practicapmdm.u2.examenes.examen2Trimestre.ejercicio2.api.CriptoRepo;
import dam2.practicapmdm.u2.examenes.examen2Trimestre.ejercicio2.pojo.PojoCripto;
import dam2.practicapmdm.u2.examenes.examen2Trimestre.ejercicio2.pojo.PojoEstafados;
import dam2.practicapmdm.u2.examenes.examenOrdinario.ejercicio2.CuadrosViewModel;
import dam2.practicapmdm.u2.practicar.peliculas.PeliculasViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Criptos extends AppCompatActivity {
    private Button btBuscar;
    private TextView tvError, tvEuros;
    private RecyclerView rvCriptos;
    Spinner spMonedas;
    private List<PojoEstafados> listaEstafados = new ArrayList<>();
    private CriptosAdapter criptosAdapter;
    ProgressBar pbCargando;
    String opcionSeleccionada;
    int idCripto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.examen_2tr_criptos);

        tvEuros = findViewById(R.id.tr2tvcriptosEuros);
        rvCriptos = findViewById(R.id.tr2CriptosRV);
        rvCriptos.setLayoutManager(new LinearLayoutManager(this));
        pbCargando = findViewById(R.id.tr2pbcriptosCargando);
        tvError = findViewById(R.id.tr2tvcriptosError);
        spMonedas = findViewById(R.id.tr2spSpinner);
        btBuscar = findViewById(R.id.tr2btBuscar);

        List<String> opciones = new ArrayList<>();
        opciones.add("Bitcoin");
        opciones.add("Etherium");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, opciones);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spMonedas.setAdapter(adapter);

        spMonedas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                opcionSeleccionada = opciones.get(position);
                if (opcionSeleccionada == "Bitcoin"){
                    idCripto = 1;
                } else {
                    idCripto = 2;
                }

            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Si no se selecciona nada
            }
        });
        pbCargando.setVisibility(View.INVISIBLE);
        btBuscar.setOnClickListener(view -> {
            getEuros(idCripto);
        });
    }
    /*
    private void getEstafados(int idCripto){
        CuadrosViewModel mvvm = new ViewModelProvider(this).get(CuadrosViewModel.class);

        mvvm.getCuadro().observe(this, integer -> {
            pbCargando.setVisibility(View.INVISIBLE);
            rvCriptos.setVisibility(View.VISIBLE);
        });

        pbCargando.setVisibility(View.VISIBLE);
        rvCriptos.setVisibility(View.INVISIBLE);

        CriptoRepo repoCripto = CriptoApi.getInstancia().create(CriptoRepo.class);
        Call<PojoCripto> call = repoCripto.getEstafados("1");

        call.enqueue(new Callback<PojoCripto>() {
            @Override
            public void onResponse(Call<PojoCripto> call, Response<PojoCripto> response) {
                pbCargando.setVisibility(View.INVISIBLE);
                if (response.isSuccessful()) {
                    PojoCripto cripto = response.body();
                    if (cripto != null) {
                        listaEstafados = cripto.getEstafados();
                        criptosAdapter = new CriptosAdapter(listaEstafados, Criptos.this);
                        rvCriptos.setAdapter(criptosAdapter);
                    } else {
                        tvError.setText("No se encontraron detalles para esta película.");
                    }
                } else {
                    tvError.setText("Error: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<PojoCripto> call, Throwable t) {
                pbCargando.setVisibility(View.INVISIBLE);
                tvError.setText("Error al obtener detalles: " + t.getMessage());
            }
        });
    } */

    private void getEuros(int idCripto){

        CuadrosViewModel mvvm = new ViewModelProvider(this).get(CuadrosViewModel.class);

        mvvm.getCuadro().observe(this, integer -> {
            pbCargando.setVisibility(View.INVISIBLE);
            rvCriptos.setVisibility(View.VISIBLE);
        });

        pbCargando.setVisibility(View.VISIBLE);
        rvCriptos.setVisibility(View.INVISIBLE);

        CriptoRepo repoCripto = CriptoApi.getInstancia().create(CriptoRepo.class);

        Call<PojoCripto> callEuros = repoCripto.getCripto(String.valueOf(idCripto));

        callEuros.enqueue(new Callback<PojoCripto>(){

            @Override
            public void onResponse(Call<PojoCripto> call, Response<PojoCripto> response) {
                if (!response.isSuccessful()){
                    tvError.setText("Codigo: " + response.code());
                    mvvm.cargaCuadro();
                    return;
                }

                PojoCripto cripto = response.body();
                tvEuros.setText(String.valueOf(cripto.getEuros()));

                if (cripto != null) {
                    listaEstafados = cripto.getEstafados();
                    criptosAdapter = new CriptosAdapter(listaEstafados, Criptos.this);
                    rvCriptos.setAdapter(criptosAdapter);
                } else {
                    tvError.setText("No se encontraron detalles para esta película.");
                }
            }

            @Override
            public void onFailure(Call<PojoCripto> call, Throwable t) {
                tvError.setText(t.getMessage());
            }
        });
        /*
        callEstafados.enqueue(new Callback<PojoCripto>() {
            @Override
            public void onResponse(Call<PojoCripto> call, Response<PojoCripto> response) {
                if (!response.isSuccessful()){
                    mvvm.cargaCuadro();
                    tvError.setText("Error: " + response.code());
                    return;
                }

                PojoCripto cripto = response.body();
                if (cripto != null) {
                    listaEstafados = cripto.getEstafados();
                    criptosAdapter = new CriptosAdapter(listaEstafados, Criptos.this);
                    rvCriptos.setAdapter(criptosAdapter);
                } else {
                    tvError.setText("No se encontraron detalles para esta película.");
                }

            }

            @Override
            public void onFailure(Call<PojoCripto> call, Throwable t) {
                tvError.setText(t.getMessage());
            }
        });*/
    }
}