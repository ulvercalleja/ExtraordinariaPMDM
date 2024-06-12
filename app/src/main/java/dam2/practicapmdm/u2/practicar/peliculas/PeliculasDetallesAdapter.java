package dam2.practicapmdm.u2.practicar.peliculas;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import dam2.practicapmdm.R;
import dam2.practicapmdm.u2.practicar.peliculas.pojo.PojoActores;

public class PeliculasDetallesAdapter extends RecyclerView.Adapter<PeliculasDetallesAdapter.PeliculasDetallesViewHolder> {
    private List<PojoActores> listaActores;
    private Context context;

    public PeliculasDetallesAdapter(List<PojoActores> listaActores, Context context) {
        this.listaActores = listaActores;
        this.context = context;
    }

    @NonNull
    @Override
    public PeliculasDetallesAdapter.PeliculasDetallesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.practicar_peliculas_detalles_holder, parent, false);
        return new PeliculasDetallesViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull PeliculasDetallesViewHolder holder, int position) {
        PojoActores actor = listaActores.get(position);
        holder.tvNombre.setText(actor.getNombre());
        holder.tvUrl.setText(actor.getUrl());
    }

    @Override
    public int getItemCount() {
        return listaActores.size();
    }

    public static class PeliculasDetallesViewHolder extends RecyclerView.ViewHolder {
        private final TextView tvNombre;
        private final TextView tvUrl;

        public PeliculasDetallesViewHolder(@NonNull View itemView){
            super(itemView);
            tvNombre = itemView.findViewById(R.id.practicarpeliculasdetallestvNombre);
            tvUrl = itemView.findViewById(R.id.practicarpeliculasdetallestvUrl);
        }
    }
}

