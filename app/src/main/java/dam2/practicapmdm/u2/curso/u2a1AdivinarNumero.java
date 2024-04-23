package dam2.practicapmdm.u2.curso;

/* Crea una App con el juego adivina el número. Tendrá un botón de reiniciar y un botón de jugar.
El programa eligirá un número entre 1 y N, tendrás log2(N)-1 intentos. Si ganas el programa mostrará
 un mensaje en verde has ganado. Si pierdes en rojo indicando que has perdido. */

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;

import dam2.practicapmdm.R;

public class u2a1AdivinarNumero extends AppCompatActivity {

    Button btJugar, btReinicar;
    EditText etAdivinar;
    TextView tvResultado;
    int maxNum = 0;
    int numIntentos = 0;
    Random random = new Random();
    int numGenerado = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.u2a1_adivinar_numero);

        btJugar = findViewById(R.id.u2a1btJugar);
        btReinicar = findViewById(R.id.u2a1btReiniciar);
        etAdivinar = findViewById(R.id.u2a1ptAdivinar);
        tvResultado = findViewById(R.id.u2a1tvResultado);

        reiniciar();

        btJugar.setOnClickListener(view -> {

            int numElegido = Integer.parseInt(etAdivinar.getText().toString());

            if (numGenerado == numElegido && numIntentos > 0){
                tvResultado.setTextColor(Color.GREEN);
                tvResultado.setText("¡¡¡Acertaste!!!");
                btJugar.setEnabled(false);
            } else {
                numIntentos--;
                tvResultado.setText("¡¡¡NO!!! el numero es " + numGenerado);
                if (numIntentos == 0){
                    tvResultado.setTextColor(Color.RED);
                    tvResultado.setText("¡¡¡Perdiste!!!");
                    btJugar.setEnabled(false);
                }
            }
        });

        btReinicar.setOnClickListener(view -> {
            reiniciar();
        });
    }

    public void reiniciar(){
        maxNum = 10;
        numIntentos = (int) (Math.log(maxNum) / Math.log(2)) - 1;
        numGenerado = random.nextInt(maxNum) + 1;
        tvResultado.setText("");
        etAdivinar.setText("");
        btJugar.setEnabled(true);
    }
}