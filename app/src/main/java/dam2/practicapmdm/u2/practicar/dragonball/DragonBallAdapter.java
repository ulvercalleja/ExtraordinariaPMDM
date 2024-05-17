package dam2.practicapmdm.u2.practicar.dragonball;

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
import dam2.practicapmdm.u2.practicar.dragonball.pojo.PojoDragonBall;

public class DragonBallAdapter extends RecyclerView.Adapter<DragonBallAdapter.DragonBallViewHolder>{

    private List<PojoDragonBall> listaPersonajes;
    public static final String ID_DETALLE="ID";
    private Context context;

    public DragonBallAdapter(List<PojoDragonBall> listaPersonajes, Context context) {
        this.listaPersonajes = listaPersonajes;
        this.context = context;
    }

    @NonNull
    @Override
    public DragonBallViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.practicar_dragonball_holder, parent, false);
        return new DragonBallViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull DragonBallViewHolder holder, int position) {
        PojoDragonBall personaje = listaPersonajes.get(position);
        holder.tvNombre.setText(personaje.getName());
        holder.tvKi.setText(personaje.getKi());

        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(context, DragonBallDetalles.class);
            intent.putExtra(ID_DETALLE, personaje.getId());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return listaPersonajes.size();
    }

    public static class DragonBallViewHolder extends RecyclerView.ViewHolder {
        private final TextView tvNombre;
        private final TextView tvKi;
        public DragonBallViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNombre = itemView.findViewById(R.id.practicardragonballtvNombre);
            tvKi = itemView.findViewById(R.id.practicardragonballtvKi);
        }
    }
}
