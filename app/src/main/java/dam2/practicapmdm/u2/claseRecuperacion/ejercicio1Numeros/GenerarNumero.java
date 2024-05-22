package dam2.practicapmdm.u2.claseRecuperacion.ejercicio1Numeros;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Random;

import dam2.practicapmdm.R;

public class GenerarNumero extends AppCompatActivity {
    public static final String INFO_ARRAY="miArray";
    EditText etNumero;
    Button btGenerar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.clase_recuperacion_generar_numero);

        etNumero = findViewById(R.id.crgnetNum);
        btGenerar= findViewById(R.id.crgnbtGenerar);

        Random random = new Random();

        btGenerar.setOnClickListener(view -> {
            int numero = Integer.parseInt(etNumero.getText().toString());
            int array[] = new int[numero];

            for (int i = 0; i < numero; i++) {
                int randomNumber = random.nextInt(100) + 1;
                array[i] = randomNumber;
            }

            Intent intent = new Intent(GenerarNumero.this, NumerosGenerados.class);
            intent.putExtra(INFO_ARRAY, array);
            startActivity(intent);
        });

    }
}