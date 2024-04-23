package dam2.practicapmdm.u2.examenOrdinario.ejercicio1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;
import java.util.GregorianCalendar;

import dam2.practicapmdm.R;

public class a1Trimestre1 extends AppCompatActivity {
    public static final String INFO_NOMBRE = "nombre";
    public static final String INFO_FECHA = "fecha";
    public static final String INFO_PRESUPUESTO_MIN = "presupuestoMin";
    public static final String INFO_PRESUPUESTO_MAX = "presupuestoMax";
    Calendar selectedDate;
    EditText etNombre, etFecha, etPresupuestoMin, etPresupuestoMax;
    TextView tvError;
    Button btFecha, btValidar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.examen_ord_presupuesto_a1);

        etNombre = findViewById(R.id.a1etNombre);
        etFecha = findViewById(R.id.a1etFecha);
        etPresupuestoMin = findViewById(R.id.a1etPresupuestoMin);
        etPresupuestoMax = findViewById(R.id.a1etPresupuestoMax);
        tvError = findViewById(R.id.a1tvError);
        btFecha = findViewById(R.id.a1btFecha);
        btValidar = findViewById(R.id.a1btValidar);

        btFecha.setOnClickListener(view -> {
            // Obtenemos la instancia del calendario actual.
            final Calendar c = Calendar.getInstance();

            // on below line we are getting
            // our day, month and year.
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            // Creamos un DatePickerDialog con la fecha actual como fecha mínima.
            DatePickerDialog datePickerDialog = new DatePickerDialog(
                    // on below line we are passing context.
                    a1Trimestre1.this,
                    (datePicker, year1, monthOfYear, dayOfMonth) -> {
                        // Creamos una instancia de Calendar con la fecha seleccionada.
                        selectedDate = new GregorianCalendar(year, monthOfYear, dayOfMonth);
                        etFecha.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year1);
                    },
                    // on below line we are passing year,
                    // month and day for selected date in our date picker.
                    year, month, day);
            // at last we are calling show to
            // display our date picker dialog.
            datePickerDialog.show();
        });

        btValidar.setOnClickListener(view -> {
            String nombre = etNombre.getText().toString();
            String fecha = etFecha.getText().toString();
            double presupuestoMin = Double.parseDouble(etPresupuestoMin.getText().toString());
            double presupuestoMax = Double.parseDouble(etPresupuestoMax.getText().toString());

            final Calendar fechaActual = Calendar.getInstance();

            if (selectedDate.before(fechaActual) || presupuestoMax < presupuestoMin){
                if (presupuestoMax < presupuestoMin){
                    tvError.setText("El presupuesto minimo no puede ser mayor al maximo.");
                }

                if (selectedDate.before(fechaActual)){
                    tvError.setText("La fecha no puede ser anterior a la actual.");
                }
            } else {
                tvError.setText("");
                // Crear un intent para pasar los datos a la siguiente actividad
                Intent intent = new Intent(a1Trimestre1.this, a2Trimestre1.class);
                intent.putExtra(INFO_NOMBRE, nombre);
                intent.putExtra(INFO_FECHA, fecha);
                intent.putExtra(INFO_PRESUPUESTO_MIN, presupuestoMin);
                intent.putExtra(INFO_PRESUPUESTO_MAX, presupuestoMax);
                startActivity(intent);
            }
        });
    }
}