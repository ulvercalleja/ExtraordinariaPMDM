package dam2.practicapmdm.u2.claseRecuperacion.ejercicio3Animales;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;

import dam2.practicapmdm.R;

public class Zoo extends AppCompatActivity {

    RecyclerView rv;
    ZooAdapter adapter;
    Button btdesordenar, btordenartipo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.clase_recuperacion_zoo);

        rv = findViewById(R.id.crzrvZoo);
        btdesordenar = findViewById(R.id.crzbtDesordenar);
        btordenartipo = findViewById(R.id.crzbtOrdenarTIpo);

        rv.setLayoutManager(new LinearLayoutManager(Zoo.this));
        adapter = new ZooAdapter();
        rv.setAdapter(adapter);
        adapter.add(Animales.generarDatos());

        btdesordenar.setOnClickListener(view -> {
            adapter.setListaAnimales(Animales.desordenarDatos(adapter.getListaAnimales()));
        });

        btordenartipo.setOnClickListener(view -> {
            adapter.setListaAnimales(Animales.ordenarTipo(adapter.getListaAnimales()));
        });
    }
}