package dam2.practicapmdm.u2.examenOrdinario.ejercicio1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

import dam2.practicapmdm.R;

public class a2Trimestre1 extends AppCompatActivity {

    TextView tvNombre, tvFecha, tvPresupuestoMinimo;
    Button btMas, btMenos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.examen_ord_presupuesto_a2);

        tvNombre = findViewById(R.id.a2tvNombre);
        tvFecha = findViewById(R.id.a2tvFecha);
        tvPresupuestoMinimo = findViewById(R.id.a2tvPresupuestoMinimo);
        btMas = findViewById(R.id.a2btMas);
        btMenos = findViewById(R.id.a2btMenos);

        // Obtener los datos pasados desde la actividad anterior
        String nombre = getIntent().getStringExtra(a1Trimestre1.INFO_NOMBRE);
        String fecha = getIntent().getStringExtra(a1Trimestre1.INFO_FECHA);
        double presupuestoMin = Double.parseDouble(getIntent().getStringExtra(a1Trimestre1.INFO_PRESUPUESTO_MIN));
        double presupuestoMax = Double.parseDouble(getIntent().getStringExtra(a1Trimestre1.INFO_PRESUPUESTO_MAX));

        tvNombre.setText(nombre);
        tvFecha.setText(fecha);
        tvPresupuestoMinimo.setText(String.valueOf(presupuestoMin));

        Random rand = new Random();

        // Generar un número aleatorio dentro del rango definido por presupuestoMin y presupuestoMax
        double presupuestoAleatorio = rand.nextDouble() * (presupuestoMax - presupuestoMin) + presupuestoMin;

    }
}