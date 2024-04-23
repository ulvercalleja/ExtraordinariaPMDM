package dam2.practicapmdm.u2.claseRecuperacion.ejercicio2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import dam2.practicapmdm.R;
import dam2.practicapmdm.u2.examenOrdinario.ejercicio2.CuadroAdapter;
import dam2.practicapmdm.u2.examenOrdinario.ejercicio2.a2Cuadro;

public class ElegirEjercito extends AppCompatActivity {

    private RecyclerView rvPiezas;
    private EjercitoAdapter ejercitoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.clase_recuperacion_elegir_ejercito);

        rvPiezas = findViewById(R.id.cre2piezasRV);
        rvPiezas.setLayoutManager(new LinearLayoutManager(ElegirEjercito.this));

        Pieza[] piezasArray = {
                new Pieza("Rey", 10),
                new Pieza("Dama", 9),
                new Pieza("Alfil", 3),
                new Pieza("Caballo", 3),
                new Pieza("Torre", 5),
                new Pieza("Peon", 1),
        };
        ejercitoAdapter = new EjercitoAdapter(piezasArray);

        rvPiezas.setAdapter(ejercitoAdapter);
    }
}