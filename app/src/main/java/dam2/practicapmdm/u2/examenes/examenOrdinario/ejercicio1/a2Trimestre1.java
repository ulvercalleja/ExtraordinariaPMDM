package dam2.practicapmdm.u2.examenes.examenOrdinario.ejercicio1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

import dam2.practicapmdm.R;

public class a2Trimestre1 extends AppCompatActivity {
    public static final String INFO_PRESUPUESTO = "presupuestoAleatorio";
    public static final String INFO_NOMBRE = "nombre";
    TextView tvFecha;
    Button btMas, btMenos;
    private int presupuestoActual;

    private f2Trimestre1 fragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.examen_ord_presupuesto_a2);

        tvFecha = findViewById(R.id.a2tvFecha);
        btMas = findViewById(R.id.a2btMas);
        btMenos = findViewById(R.id.a2btMenos);

        // Obtener los datos pasados desde la actividad anterior
        String nombre = getIntent().getStringExtra(a1Trimestre1.INFO_NOMBRE);
        String fecha = getIntent().getStringExtra(a1Trimestre1.INFO_FECHA);
        int presupuestoMin = Integer.parseInt(getIntent().getStringExtra(a1Trimestre1.INFO_PRESUPUESTO_MIN));
        int presupuestoMax = Integer.parseInt(getIntent().getStringExtra(a1Trimestre1.INFO_PRESUPUESTO_MAX));

        tvFecha.setText(fecha);

        Random rand = new Random();

        // Generar un número aleatorio dentro del rango definido por presupuestoMin y presupuestoMax
        int presupuestoAleatorio = rand.nextInt(presupuestoMax - presupuestoMin + 1) + presupuestoMin;

        presupuestoActual = presupuestoAleatorio;

        Bundle bundle = new Bundle();

        bundle.putInt(INFO_PRESUPUESTO, presupuestoAleatorio);
        bundle.putString(INFO_NOMBRE, nombre);

        fragment = new f2Trimestre1();

        fragment.setArguments(bundle);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView, fragment).commit();

        btMas.setOnClickListener(view -> {
            presupuestoActual += 10; // Aumenta el presupuesto en 10 euros
            fragment.actualizarPresupuesto(presupuestoActual);
        });

        btMenos.setOnClickListener(view -> {
            presupuestoActual -= 10; // Disminuye el presupuesto en 10 euros
            fragment.actualizarPresupuesto(presupuestoActual);
        });
    }
}