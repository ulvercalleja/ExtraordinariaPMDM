package dam2.practicapmdm.u2.practicar.peliculas;

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
import dam2.practicapmdm.u2.practicar.peliculas.pojo.PojoPeliculas;

public class PeliculasAdapter extends RecyclerView.Adapter<PeliculasAdapter.PeliculasViewHolder> {
    private List<PojoPeliculas> listaPeliculas;
    public static final String ID_URL="URL";
    private Context context;

    public PeliculasAdapter(List<PojoPeliculas> listaPeliculas, Context context) {
        this.listaPeliculas = listaPeliculas;
        this.context = context;
    }

    @NonNull
    @Override
    public PeliculasAdapter.PeliculasViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.practicar_peliculas_holder, parent, false);
        return new PeliculasViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull PeliculasAdapter.PeliculasViewHolder holder, int position) {
        PojoPeliculas pelicula = listaPeliculas.get(position);
        holder.tvNombre.setText(pelicula.getNombre());
        holder.tvDescripcion.setText(pelicula.getDescripcion());

        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(context, PeliculasDetalles.class);
            intent.putExtra(ID_URL, pelicula.getUrl());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return listaPeliculas.size();
    }

    public static class PeliculasViewHolder extends RecyclerView.ViewHolder {
        private final TextView tvNombre;
        private final TextView tvDescripcion;

        public PeliculasViewHolder(@NonNull View itemView){
            super(itemView);
            tvNombre = itemView.findViewById(R.id.practicarpeliculastvNombre);
            tvDescripcion = itemView.findViewById(R.id.practicarpeliculastvDescripcion);
        }
    }
}
