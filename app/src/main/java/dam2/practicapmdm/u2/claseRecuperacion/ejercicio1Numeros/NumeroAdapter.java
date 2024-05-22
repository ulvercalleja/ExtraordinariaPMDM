package dam2.practicapmdm.u2.claseRecuperacion.ejercicio1Numeros;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import dam2.practicapmdm.R;

public class NumeroAdapter extends RecyclerView.Adapter<NumeroAdapter.NumeroViewHolder>{

    private ArrayList<Numeros> listaNumeros;

    public NumeroAdapter() {
        this.listaNumeros = new ArrayList<>();
    }

    public static class NumeroViewHolder extends RecyclerView.ViewHolder {
        TextView numero;

        public NumeroViewHolder(@NonNull View itemView) {
            super(itemView);
            numero = itemView.findViewById(R.id.crngtvNumero);
        }

        public void pinta(Numeros numeros){
            numero.setText(String.valueOf(numeros.getNumero()));
        }
    }

    public void add(ArrayList<Numeros> nuevos) {
        listaNumeros.addAll(nuevos);
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull NumeroAdapter.NumeroViewHolder holder, int position) {
        Numeros n = listaNumeros.get(position);
        holder.pinta(n);
    }

    @NonNull
    @Override
    public NumeroAdapter.NumeroViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.clase_recuperacion_numeros_generados_holder, parent, false);

        return new NumeroAdapter.NumeroViewHolder(view);
    }

    public ArrayList<Numeros> getListaNumeros() {
        return listaNumeros;
    }

    public void setListaNumeros(ArrayList<Numeros> numeros) {
        listaNumeros.clear();
        listaNumeros.addAll(numeros);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return listaNumeros.size();
    }
}
