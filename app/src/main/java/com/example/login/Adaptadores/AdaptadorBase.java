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
import com.example.login.Modelos.Negocio;
import com.example.login.R;

import java.util.ArrayList;

import static com.example.login.R.drawable.ic_estrella_completa;
import static com.example.login.R.drawable.ic_media_estrella;

public class AdaptadorBase extends RecyclerView.Adapter<AdaptadorBase.ViewHolder> implements View.OnClickListener{


    ArrayList<Base>AlBase;
    Context mContext;
    public int seleccionado;
    View.OnClickListener listener;

    public AdaptadorBase(ArrayList<Base> alBase, Context mContext, int seleccionado) {
        AlBase = alBase;
        this.mContext = mContext;
        this.seleccionado = seleccionado;
    }

    @NonNull
    @Override
    public AdaptadorBase.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(mContext)
                .inflate(R.layout.item_base,parent,false);
        view.setOnClickListener(this);
        return new AdaptadorBase.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {


        Glide.with(mContext)
                .load(AlBase.get(position).getImagen_B())
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_menu_camera)
                .apply(RequestOptions.centerInsideTransform())
                .into(holder.ImagenPizzaBase);

        holder.NombreBasePizza.setText(AlBase.get(position).getNombre());

        if (seleccionado==position){
            holder.ImagenPizzaSeleccionada.setVisibility(View.VISIBLE);
        }
        else {
            holder.ImagenPizzaSeleccionada.setVisibility(View.GONE);
        }
    }


    @Override
    public int getItemCount() {
        return AlBase.size();
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

        ImageView ImagenPizzaBase,ImagenPizzaSeleccionada;
        TextView NombreBasePizza;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            ImagenPizzaBase=itemView.findViewById(R.id.ImagenPizzaBase);
            NombreBasePizza=itemView.findViewById(R.id.NombreBasePizza);
            ImagenPizzaSeleccionada=itemView.findViewById(R.id.ImagenPizzaSeleccionada);
        }
    }
}
