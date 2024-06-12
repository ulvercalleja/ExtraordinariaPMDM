package dam2.practicapmdm.u2.examenes.examen2Trimestre.ejercicio2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import dam2.practicapmdm.R;
import dam2.practicapmdm.u2.examenes.examen2Trimestre.ejercicio2.pojo.PojoEstafados;

public class CriptosAdapter extends RecyclerView.Adapter<CriptosAdapter.CriptosViewHolder> {
    private List<PojoEstafados> listaEstafados;
    private Context context;

    public CriptosAdapter(List<PojoEstafados> listaEstafados, Context context) {
        this.listaEstafados = listaEstafados;
        this.context = context;
    }

    @NonNull
    @Override
    public CriptosAdapter.CriptosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.examen_2tr_cripto_holder, parent, false);
        return new CriptosAdapter.CriptosViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CriptosAdapter.CriptosViewHolder holder, int position) {
        PojoEstafados estafado = listaEstafados.get(position);
        holder.tvNombre.setText(estafado.getNombre());
    }

    @Override
    public int getItemCount() {
        return listaEstafados.size();
    }

    public static class CriptosViewHolder extends RecyclerView.ViewHolder {
        private final TextView tvNombre;

        public CriptosViewHolder(@NonNull View itemView){
            super(itemView);
            tvNombre = itemView.findViewById(R.id.tr2tvcriptosholderNombre);
        }
    }
}
