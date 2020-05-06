package com.example.login.Adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.login.Modelos.Base;
import com.example.login.Modelos.tamaños;
import com.example.login.R;

import java.util.ArrayList;

public class AdaptadorTamaños extends RecyclerView.Adapter<AdaptadorTamaños.ViewHolder> implements View.OnClickListener {


    ArrayList<tamaños> Altamaños;
    Context mContext;
    public int seleccionado;
    View.OnClickListener listener;

    public AdaptadorTamaños(ArrayList<tamaños> alBase, Context mContext, int seleccionado) {
        Altamaños = alBase;
        this.mContext = mContext;
        this.seleccionado = seleccionado;
    }

    @NonNull
    @Override
    public AdaptadorTamaños.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(mContext)
                .inflate(R.layout.item_tamanios,parent,false);
        view.setOnClickListener(this);
        return new AdaptadorTamaños.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            if (seleccionado==position){
                holder.tamañoSeleccionado.setVisibility(View.VISIBLE);
            }
            else {
                holder.tamañoSeleccionado.setVisibility(View.GONE);
            }
            holder.Tamaño.setText(Altamaños.get(position).getDiametro());
    }


    @Override
    public int getItemCount() {
        return Altamaños.size();
    }

    public void setOnClickListener(View.OnClickListener listener)
    {
        this.listener=listener;
    }
    @Override
    public void onClick(View v) {
        if(listener!=null){
            listener.onClick(v);
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView tamañoSeleccionado;
        TextView Tamaño;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tamañoSeleccionado=itemView.findViewById(R.id.tamañoSeleccionado);
            Tamaño=itemView.findViewById(R.id.Tamaño);
        }
    }
}
