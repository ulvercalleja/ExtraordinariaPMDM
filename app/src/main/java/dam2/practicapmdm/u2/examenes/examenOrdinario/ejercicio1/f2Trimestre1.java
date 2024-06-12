package dam2.practicapmdm.u2.examenes.examenOrdinario.ejercicio1;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import dam2.practicapmdm.R;

public class f2Trimestre1 extends Fragment {

    TextView tvPresupuesto;
    TextView tvNombre;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout = inflater.inflate(R.layout.examen_ord_presupuesto_fragment, container, false);
        tvPresupuesto = layout.findViewById(R.id.f2tvPresupuesto);
        tvNombre = layout.findViewById(R.id.f2tvNombre);

        // Obtener el presupuesto aleatorio del Bundle
        Bundle bundle = getArguments();
        if (bundle != null) {
            int presupuestoAleatorio = bundle.getInt(a2Trimestre1.INFO_PRESUPUESTO);
            String nombre = bundle.getString(a2Trimestre1.INFO_NOMBRE);
            // Mostrar el presupuesto aleatorio en el TextView
            tvPresupuesto.setText(String.valueOf(presupuestoAleatorio));
            tvNombre.setText(nombre);
        }

        return layout;
    }

    public void actualizarPresupuesto(int nuevoPresupuesto) {
        tvPresupuesto.setText(String.valueOf(nuevoPresupuesto));
    }
}