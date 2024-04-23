package dam2.practicapmdm.u2.examenOrdinario.ejercicio1;

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

        return layout;
    }
}