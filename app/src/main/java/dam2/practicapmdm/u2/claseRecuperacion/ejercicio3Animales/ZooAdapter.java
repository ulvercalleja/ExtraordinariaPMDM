package dam2.practicapmdm.u2.claseRecuperacion.ejercicio3Animales;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import dam2.practicapmdm.R;

public class ZooAdapter extends RecyclerView.Adapter<ZooAdapter.ZooViewHolder>{
    private ArrayList<Animales> listaAnimales;

    public ZooAdapter() {
        this.listaAnimales = new ArrayList<Animales>();
    }

    public static class ZooViewHolder extends RecyclerView.ViewHolder {
        TextView nombre;
        TextView tipo;
        TextView color;

        public ZooViewHolder(@NonNull View itemView) {
            super(itemView);
            nombre = itemView.findViewById(R.id.crztvNombre);
            tipo = itemView.findViewById(R.id.crztvTipo);
            color = itemView.findViewById(R.id.crztvColor);
        }

        public void pinta(Animales animal){
            nombre.setText(animal.getNombre());
            color.setText(animal.getColor());
            tipo.setText(animal.getTipo());
        }
    }

    public void add(ArrayList<Animales> nuevos) {
        listaAnimales.addAll(nuevos);
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull ZooAdapter.ZooViewHolder holder, int position) {
        Animales a = listaAnimales.get(position);
        holder.pinta(a);
    }

    @NonNull
    @Override
    public ZooAdapter.ZooViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.clase_recuperacion_zoo_holder, parent, false);

        return new ZooAdapter.ZooViewHolder(view);
    }

    public ArrayList<Animales> getListaAnimales() {
        return listaAnimales;
    }

    public void setListaAnimales(ArrayList<Animales> animales) {
        listaAnimales = animales;
        notifyDataSetChanged();
    }



    @Override
    public int getItemCount() {
        return listaAnimales.size();
    }
}
