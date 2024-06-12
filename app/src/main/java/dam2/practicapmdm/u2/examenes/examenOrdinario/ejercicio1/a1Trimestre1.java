package dam2.practicapmdm.u2.examenes.examenOrdinario.ejercicio1;
/* Presupuesto de mantenimiento
Crea una aplicación que pida:
• Nombre
• Fecha
• Presupuesto máximo
• Presupuesto mínimo
Tendrá un botón para Enviar.
Validación:
• Fecha será posterior a hoy
• El mínimo debe ser inferior a máximo
Errores:
• Se mostrará en rojo un mensaje al lado del lugar donde se ha producido el
error.
Cuando se pulse enviar y los datos estén bien se mandará la información a otra
actividad. La segunda actividad recogerá los datos y generará un presupuesto
aleatorio entre máximo y mínimo.
Ese presupuesto se mostrará en un fragment (Nombre y precio). Esta segunda
actividad, además del fragment, tendrá dos botones: “más” y “menos”, para modificar el presupuesto dentro del del fragment. Cada vez que se pulsen variará el
presupuesto en 10 euros.
*/
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
            int presupuestoMin = Integer.parseInt(etPresupuestoMin.getText().toString());
            int presupuestoMax = Integer.parseInt(etPresupuestoMax.getText().toString());

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
                String strpresupuestoMin = etPresupuestoMin.getText().toString();
                String strpresupuestoMax = etPresupuestoMax.getText().toString();

                Intent intent = new Intent(a1Trimestre1.this, a2Trimestre1.class);
                intent.putExtra(INFO_NOMBRE, nombre);
                intent.putExtra(INFO_FECHA, fecha);
                intent.putExtra(INFO_PRESUPUESTO_MIN, strpresupuestoMin);
                intent.putExtra(INFO_PRESUPUESTO_MAX, strpresupuestoMax);
                startActivity(intent);
            }
        });
    }
}