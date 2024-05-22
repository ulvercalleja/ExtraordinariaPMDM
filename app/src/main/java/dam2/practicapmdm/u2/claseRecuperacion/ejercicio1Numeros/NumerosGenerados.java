package dam2.practicapmdm.u2.claseRecuperacion.ejercicio1Numeros;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;

import java.util.ArrayList;

import dam2.practicapmdm.R;

public class NumerosGenerados extends AppCompatActivity {
    RecyclerView rv;
    NumeroAdapter adapter;
    Button btMinimo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.clase_recuperacion_numeros_generados);
        rv = findViewById(R.id.crngrvNumeros);
        btMinimo = findViewById(R.id.crngbtMin);

        int[] intArray = getIntent().getIntArrayExtra(GenerarNumero.INFO_ARRAY);
        adapter = new NumeroAdapter();

        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(adapter);

        ArrayList<Numeros> numerosList = new ArrayList<>(); //Almacenamos el array en un arraylist ya que el metodo add solo acepta arraylist
        for (int numero : intArray) {
            numerosList.add(new Numeros(numero));
        }

        adapter.add(numerosList);

        btMinimo.setOnClickListener(view -> {
            adapter.setListaNumeros(Numeros.mostrarMinimo(adapter.getListaNumeros()));
        });
    }
}