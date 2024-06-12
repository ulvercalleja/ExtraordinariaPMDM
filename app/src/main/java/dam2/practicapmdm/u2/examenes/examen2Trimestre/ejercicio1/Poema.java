package dam2.practicapmdm.u2.examenes.examen2Trimestre.ejercicio1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import dam2.practicapmdm.R;

public class Poema extends AppCompatActivity {



    Button btRecitar;
    TextView tvPoema;
    ProgressBar pbCargando;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.examen_2tr_poema);

        btRecitar = findViewById(R.id.e2trbtRecitar);
        pbCargando = findViewById(R.id.e2trpbCargando);
        tvPoema = findViewById(R.id.e2trtvPoema);

        PoemaViewModel mvvm = new ViewModelProvider(this).get(PoemaViewModel.class);

        mvvm.getPoema().observe(this, palabra -> {
            if ("FIN".equals(palabra)) {
                pbCargando.setVisibility(View.INVISIBLE);
                btRecitar.setVisibility(View.VISIBLE);
            } else {
                tvPoema.append(palabra + " "); //Añadimos un espacio para que no esté todo junto
            }
        });

        btRecitar.setOnClickListener(view -> {
            btRecitar.setVisibility(View.INVISIBLE);
            tvPoema.setText("");
            pbCargando.setVisibility(View.VISIBLE);
            mvvm.cargaPoema();
        });

    }
}