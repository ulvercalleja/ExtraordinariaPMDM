package dam2.practicapmdm.u2.examenes.examenOrdinario.ejercicio2;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import dam2.practicapmdm.R;

import dam2.practicapmdm.u2.examenes.examenOrdinario.ejercicio2.pojo.PojoCuadro;

public class CuadroAdapter extends RecyclerView.Adapter<CuadroAdapter.CuadroViewHolder>{
    private List<PojoCuadro> listaCuadros;
    public static final String ID_DETALLE="ID";
    private Context context;

    public CuadroAdapter(List<PojoCuadro> listaAtracciones, Context context){
        this.listaCuadros = listaAtracciones;
        this.context = context;
    }

    public static class CuadroViewHolder extends RecyclerView.ViewHolder {

        private final TextView tvNombre;
        private final TextView tvId;
        public CuadroViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNombre = itemView.findViewById(R.id.a2extvNombre);
            tvId = itemView.findViewById(R.id.textView);
        }
    }

    @NonNull
    @Override
    public CuadroViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.examen_ord_cuadro_holder, parent, false);
        return new CuadroViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CuadroViewHolder holder, int position) {
        PojoCuadro cuadro = listaCuadros.get(position);
        holder.tvNombre.setText(cuadro.getNombre());
        holder.tvId.setText(cuadro.getId());

        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(context, a2CuadroDetalles.class);
            intent.putExtra(ID_DETALLE, cuadro.getId());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return listaCuadros.size();
    }

}
