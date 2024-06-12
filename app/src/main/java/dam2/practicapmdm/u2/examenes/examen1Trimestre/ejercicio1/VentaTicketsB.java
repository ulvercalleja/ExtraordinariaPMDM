package dam2.practicapmdm.u2.examenes.examen1Trimestre.ejercicio1;


import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import dam2.practicapmdm.R;

public class VentaTicketsB extends AppCompatActivity {

    private TextView tvCiudades, tvSoloIda, tvFechaIda, tvFechaVuelta;
    private Button btVolver, btReiniciar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.examen_1tr_venta_tickets_b);
        tvCiudades= findViewById(R.id.tr1tvCiudades);
        tvSoloIda= findViewById(R.id.tr1tvSoloIda);
        tvFechaIda= findViewById(R.id.tr1tvFechaIda);
        tvFechaVuelta= findViewById(R.id.tr1tvFechaVuelta);
        btReiniciar= findViewById(R.id.tr1btReiniciar);
        btVolver= findViewById(R.id.tr1btVolver);

        // Get data from intent
        String origin = getIntent().getStringExtra(VentaTickets.EXTRA_ORIGIN);
        String destination = getIntent().getStringExtra(VentaTickets.EXTRA_DESTINATION);
        String departureDate = getIntent().getStringExtra(VentaTickets.EXTRA_DEPARTURE_DATE);
        String returnDate = getIntent().getStringExtra(VentaTickets.EXTRA_RETURN_DATE);
        boolean oneWay = getIntent().getBooleanExtra(VentaTickets.EXTRA_ONE_WAY, false);

        StringBuilder details = new StringBuilder();
        details.append(origin).append("-").append(destination);
        tvCiudades.setText(details);

        tvFechaIda.setText(departureDate);

        if (oneWay){
            tvSoloIda.setText("Solo ida");
            tvFechaVuelta.setText("");
        } else {
            tvSoloIda.setText("");
            tvFechaVuelta.setText(returnDate);
        }

        btVolver.setOnClickListener(v -> {
            finish();
        });

        btReiniciar.setOnClickListener(v -> {
            tvCiudades.setText("");
            tvFechaIda.setText("");
            tvSoloIda.setText("");
            tvFechaVuelta.setText("");
            finish();
        });
    }
}