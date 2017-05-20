package com.example.ariso.planetas.adaptador;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ariso.planetas.R;
import com.example.ariso.planetas.model.PLaneta;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by ariso on 20/05/2017.
 */

public class PlanetaAdapter extends RecyclerView.Adapter<PlanetaAdapter.PlanetaViewHolder> {

    final DateFormat df = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.MEDIUM);

    private PlanetaClickListener clickListener;
    private EditarPlanetaClickListener editarPlanetaClickListener;
    private List<PLaneta> Planetas;



    public PlanetaAdapter(PlanetaClickListener PlanetaClickListener, EditarPlanetaClickListener editarPlanetaClickListener){
        this.clickListener = PlanetaClickListener;
        this.editarPlanetaClickListener = editarPlanetaClickListener;
        this.Planetas = new ArrayList<PLaneta>();
    }

    public void setPlanetas(@NonNull List<PLaneta> planetas) {
        this.Planetas = planetas;
        notifyDataSetChanged();
    }

    public PLaneta getPlaneta(int position) {
        return Planetas.get(position);
    }




    @Override
    public PlanetaAdapter.PlanetaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.planeta_item, parent, false);
        return new PlanetaViewHolder(view, clickListener);
    }

    @Override
    public void onBindViewHolder(PlanetaAdapter.PlanetaViewHolder holder, int position) {
        PLaneta nota = Planetas.get(position);
        holder.tvTexto.setText(nota.getTexto());
        String fecha = "Agregado el " + df.format(nota.getDate());
        holder.tvFecha.setText(fecha);

    }

    @Override
    public int getItemCount() {
        return Planetas.size();
    }



    class PlanetaViewHolder extends RecyclerView.ViewHolder{

        ImageView ivEditar;
        TextView tvTexto;
        TextView tvFecha;

        public PlanetaViewHolder(View itemView, final PlanetaClickListener clickListener) {
            super(itemView);
            ivEditar = (ImageView)itemView.findViewById(R.id.iv_icono);
            tvTexto = (TextView)itemView.findViewById(R.id.tv_titulo);
            tvFecha = (TextView)itemView.findViewById(R.id.tv_fecha);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View view) {
                    if (clickListener != null) {
                        clickListener.onPlanetaClick(getAdapterPosition());
                    }
                }
            });
            ivEditar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (editarPlanetaClickListener != null) {
                        editarPlanetaClickListener.onEditarPlanetaClick(getAdapterPosition());
                    }
                }
            });
        }
    }




    public interface EditarPlanetaClickListener {
        void onEditarPlanetaClick(int position);
    }

    public interface PlanetaClickListener {
        void onPlanetaClick(int position);
    }
}




