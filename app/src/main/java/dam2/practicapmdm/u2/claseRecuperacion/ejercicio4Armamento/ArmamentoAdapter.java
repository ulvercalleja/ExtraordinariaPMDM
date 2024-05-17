package dam2.practicapmdm.u2.claseRecuperacion.ejercicio4Armamento;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import dam2.practicapmdm.R;
import dam2.practicapmdm.u2.claseRecuperacion.ejercicio3Animales.Animales;

public class ArmamentoAdapter extends RecyclerView.Adapter<ArmamentoAdapter.ArmamentoViewHolder>{
    private ArrayList<Armas> listaArmas;

    public ArmamentoAdapter() {
        this.listaArmas = new ArrayList<Armas>();
    }

    public static class ArmamentoViewHolder extends RecyclerView.ViewHolder {
        TextView nombre;
        TextView tipo;
        TextView pais;

        public ArmamentoViewHolder(@NonNull View itemView) {
            super(itemView);
            nombre = itemView.findViewById(R.id.cratvNombre);
            tipo = itemView.findViewById(R.id.cratvTipo);
            pais = itemView.findViewById(R.id.cratvPais);
        }

        public void pinta(Armas arma){
            nombre.setText(arma.getNombre());
            pais.setText(arma.getPais());
            switch (arma.getTipo()) {
                case "Dron":
                    tipo.setText("o");
                    break;
                case "Tierra":
                    tipo.setText("x");
                    break;
                case "Misil":
                    tipo.setText("i");
                    break;
                default:
                    // Manejo para tipos desconocidos, si es necesario
                    break;
            }

        }
    }

    public void add(ArrayList<Armas> nuevos) {
        listaArmas.addAll(nuevos);
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull ArmamentoAdapter.ArmamentoViewHolder holder, int position) {
        Armas a = listaArmas.get(position);
        holder.pinta(a);
    }

    @NonNull
    @Override
    public ArmamentoAdapter.ArmamentoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.clase_recuperacion_armamento_holder, parent, false);

        return new ArmamentoAdapter.ArmamentoViewHolder(view);
    }

    public ArrayList<Armas> getListaArmas() {
        return listaArmas;
    }

        @Override
    public int getItemCount() {
        return listaArmas.size();
    }
}
