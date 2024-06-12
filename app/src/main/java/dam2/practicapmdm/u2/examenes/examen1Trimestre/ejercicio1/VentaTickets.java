package dam2.practicapmdm.u2.examenes.examen1Trimestre.ejercicio1;
/* Para traducir la aplicacion:
     Vamos a res/values/strings.xml
     Añadimos el string, por ejemplo del boton reservar: <string name="button_reserve">Reservar</string>
     Creamos un string en ingles, y añadimos <string name="button_reserve">Reserve</string>
     Luego nos vamos al xml y cambiamos el texto a fuego por: @string/
     Para crear archivo de idioma: click en package String, new resources... etc
*/
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import dam2.practicapmdm.R;

public class VentaTickets extends AppCompatActivity {

    private Spinner spinnerOrigin, spinnerDestination;
    private TextView datePickerDeparture, datePickerReturn, tvError;
    private CheckBox checkBoxOneWay;
    private Button buttonReserve;
    public static final String EXTRA_ORIGIN = "ORIGIN";
    public static final String EXTRA_DESTINATION = "DESTINATION";
    public static final String EXTRA_DEPARTURE_DATE = "DEPARTURE_DATE";
    public static final String EXTRA_RETURN_DATE = "RETURN_DATE";
    public static final String EXTRA_ONE_WAY = "ONE_WAY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.examen_1tr_venta_tickets);

        spinnerOrigin = findViewById(R.id.tr1spOrigen);
        spinnerDestination = findViewById(R.id.tr1spDestino);
        datePickerDeparture = findViewById(R.id.tr1dpIda);
        datePickerReturn = findViewById(R.id.tr1dpVuelta);
        checkBoxOneWay = findViewById(R.id.tr1cbSoloIda);
        tvError = findViewById(R.id.tr1tvError);
        buttonReserve = findViewById(R.id.tr1btReserva);

        // Set up spinners
        String[] cities = {"Madrid", "Londeres", "Lisboa", "Malaga"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, cities);
        spinnerOrigin.setAdapter(adapter);
        spinnerDestination.setAdapter(adapter);

        // Set up date pickers
        datePickerDeparture.setOnClickListener(v -> showDatePickerDialog((TextView) v));
        datePickerReturn.setOnClickListener(v -> showDatePickerDialog((TextView) v));

        // Set up checkbox
        checkBoxOneWay.setOnCheckedChangeListener((buttonView, isChecked) -> {
            datePickerReturn.setEnabled(!isChecked);
            if (isChecked) {
                datePickerReturn.setVisibility(View.INVISIBLE);
                datePickerReturn.setText(""); // Clear return date if One Way is checked
            } else {
                datePickerReturn.setVisibility(View.VISIBLE);
            }
        });

        // Set up reserve button
        buttonReserve.setOnClickListener(v -> {
            if (validateInputs()) {
                Intent intent = new Intent(VentaTickets.this, VentaTicketsB.class);
                intent.putExtra(EXTRA_ORIGIN, spinnerOrigin.getSelectedItem().toString());
                intent.putExtra(EXTRA_DESTINATION, spinnerDestination.getSelectedItem().toString());
                intent.putExtra(EXTRA_DEPARTURE_DATE, datePickerDeparture.getText().toString());
                if (!checkBoxOneWay.isChecked()) {
                    intent.putExtra(EXTRA_RETURN_DATE, datePickerReturn.getText().toString());
                } else {
                    intent.putExtra(EXTRA_RETURN_DATE, "");
                }
                intent.putExtra(EXTRA_ONE_WAY, checkBoxOneWay.isChecked());
                startActivity(intent);
            }
        });
    }

    private void showDatePickerDialog(final TextView textView) {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(VentaTickets.this,
                (view, year1, month1, dayOfMonth) -> textView.setText(dayOfMonth + "/" + (month1 + 1) + "/" + year1),
                year, month, day);
        datePickerDialog.show();
    }

    private boolean validateInputs() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        String origin = spinnerOrigin.getSelectedItem().toString();
        String destination = spinnerDestination.getSelectedItem().toString();
        String departureDate = datePickerDeparture.getText().toString();
        String returnDate = datePickerReturn.getText().toString();
        Date departure = null;
        Date returnD = null;
        try {
            departure = sdf.parse(departureDate);
            returnD = sdf.parse(returnDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if (origin.equals(destination)) {
            tvError.setText("La ciudad de origen y destino no pueden ser la misma");
            return false;
        }

        if (departureDate.isEmpty()) {
            tvError.setText("Seleccione una fecha de ida");
            return false;
        }

        if (!checkBoxOneWay.isChecked() && returnDate.isEmpty()) {
            tvError.setText("Seleccione una fecha de vuelta");
            return false;
        }

        if (!checkBoxOneWay.isChecked() && returnD.before(departure)) {
            tvError.setText("La fecha de vuelta no puede ser antes de la fecha de ida");
            return false;
        }
        tvError.setText("");
        return true;
    }
}