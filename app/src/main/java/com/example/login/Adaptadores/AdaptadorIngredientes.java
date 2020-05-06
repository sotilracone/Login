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
import com.example.login.Modelos.Ingredientes;
import com.example.login.Modelos.tamaños;
import com.example.login.R;

import java.util.ArrayList;

public class AdaptadorIngredientes extends RecyclerView.Adapter<AdaptadorIngredientes.ViewHolder> implements View.OnClickListener{



    public ArrayList<Ingredientes> AlIngredientes;
    Context mContext;
    public ArrayList<Integer> seleccionado;
    View.OnClickListener listener;

    public AdaptadorIngredientes(ArrayList<Ingredientes> alBase, Context mContext, ArrayList<Integer> seleccionado) {
        this.AlIngredientes = alBase;
        this.mContext = mContext;
        this.seleccionado = seleccionado;
    }

    @NonNull
    @Override
    public AdaptadorIngredientes.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(mContext)
                .inflate(R.layout.item_ingredientes,parent,false);
        view.setOnClickListener(this);
        return new AdaptadorIngredientes.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (seleccionado.contains(position)){
            holder.ImagenIngredienteSel.setVisibility(View.VISIBLE);
        }
        else {
            holder.ImagenIngredienteSel.setVisibility(View.GONE);
        }
        Glide.with(mContext)
                .load(AlIngredientes.get(position).getImagen_I())
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_menu_camera)
                .apply(RequestOptions.centerInsideTransform())
                .into(holder.ImagenIngrediente);
        holder.NombreIngrediente.setText(AlIngredientes.get(position).getNombre_I());

    }

    @Override
    public int getItemCount() {
        return AlIngredientes.size();
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

        ImageView ImagenIngrediente,ImagenIngredienteSel;
        TextView NombreIngrediente;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ImagenIngredienteSel=itemView.findViewById(R.id.ImagenIngredienteSel);
            ImagenIngrediente=itemView.findViewById(R.id.ImagenIngrediente);
            NombreIngrediente=itemView.findViewById(R.id.NombreIngrediente);
        }
    }
}
