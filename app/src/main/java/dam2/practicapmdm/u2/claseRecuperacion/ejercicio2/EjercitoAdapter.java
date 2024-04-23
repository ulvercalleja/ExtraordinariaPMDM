package dam2.practicapmdm.u2.claseRecuperacion.ejercicio2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;

import dam2.practicapmdm.R;

public class EjercitoAdapter extends RecyclerView.Adapter<EjercitoAdapter.EjercitoViewHolder>{
    private ArrayList<Pieza> listaPiezas;

    public EjercitoAdapter(Pieza[] dataSet) {
        listaPiezas = new ArrayList<Pieza>();
        add(dataSet);
    }

    public static class EjercitoViewHolder extends RecyclerView.ViewHolder {
        private final TextView piezas;
        public EjercitoViewHolder(View view) {
            super(view);
            piezas = view.findViewById(R.id.cre2Piezas);
        }

        public TextView getTextPiezas() {
            return piezas;
        }

    }

    @Override
    public EjercitoAdapter.EjercitoViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.clase_recuperacion_ejercito_holder, viewGroup, false);

        return new EjercitoAdapter.EjercitoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(EjercitoViewHolder viewHolder, int position) {
        viewHolder.getTextPiezas().setText(listaPiezas.get(position).nombre);
    }

    @Override
    public int getItemCount() {
        return listaPiezas.size();
    }

    public void add(Pieza[] dataSet){
        listaPiezas.addAll(Arrays.asList(dataSet));
        notifyDataSetChanged();
    }
}
