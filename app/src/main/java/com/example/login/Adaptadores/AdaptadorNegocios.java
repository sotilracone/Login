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
import com.example.login.Modelos.Negocio;
import com.example.login.R;

import java.util.ArrayList;

import static com.example.login.R.drawable.ic_estrella_completa;
import static com.example.login.R.drawable.ic_media_estrella;

public class AdaptadorNegocios extends RecyclerView.Adapter<AdaptadorNegocios.ViewHolder> implements View.OnClickListener {

    ArrayList<Negocio> ListaNeg;
    ArrayList<Float> ListaCalif;
    Context mContext;
    public int seleccionado;
    View.OnClickListener listener;

    public AdaptadorNegocios(ArrayList<Negocio> listaNeg, ArrayList<Float> listaCalif, Context mContext, int seleccionado) {
        ListaNeg = listaNeg;
        ListaCalif = listaCalif;
        this.mContext = mContext;
        this.seleccionado = seleccionado;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(mContext)
                .inflate(R.layout.item_negocio,parent,false);
        view.setOnClickListener(this);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.NombreNeg.setText(ListaNeg.get(position).getNombre_Negocio());
        Glide.with(mContext)
                .load(ListaNeg.get(position).getImagen_N())
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_menu_camera)
                .apply(RequestOptions.centerInsideTransform())
                .into(holder.Img_neg);
        if (ListaCalif.get(position)>=1){
            holder.Estrella1.setImageResource(ic_estrella_completa);
            if (ListaCalif.get(position)>=2){
                holder.Estrella2.setImageResource(ic_estrella_completa);
                if (ListaCalif.get(position)>=3){
                    holder.Estrella3.setImageResource(ic_estrella_completa);
                    if (ListaCalif.get(position)>=4){
                        holder.Estrella4.setImageResource(ic_estrella_completa);
                        if (ListaCalif.get(position)==5){
                            holder.Estrella5.setImageResource(ic_estrella_completa);
                        }
                        else {
                                holder.Estrella5.setImageResource(ic_media_estrella);
                        }
                    }
                    else {
                            holder.Estrella4.setImageResource(ic_media_estrella);
                    }
                }
                else {
                        holder.Estrella3.setImageResource(ic_media_estrella);
                }
            }
            else {
                    holder.Estrella2.setImageResource(ic_media_estrella);
            }
        }
        else {
            if (ListaCalif.get(position)>0) {
                holder.Estrella1.setImageResource(ic_media_estrella);
            }
        }

        if (seleccionado==position){
            holder.seleccion.setVisibility(View.VISIBLE);
        }
        else {
            holder.seleccion.setVisibility(View.GONE);
        }

    }

    @Override
    public int getItemCount() {
        return ListaNeg.size();
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

        ImageView Img_neg,seleccion;
        ImageView Estrella1,Estrella2,Estrella3,Estrella4,Estrella5;
        TextView NombreNeg;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            Img_neg=itemView.findViewById(R.id.Img_neg);
            NombreNeg=itemView.findViewById(R.id.NombreNeg);
            Estrella1=itemView.findViewById(R.id.Estrella1);
            Estrella2=itemView.findViewById(R.id.Estrella2);
            Estrella3=itemView.findViewById(R.id.Estrella3);
            Estrella4=itemView.findViewById(R.id.Estrella4);
            Estrella5=itemView.findViewById(R.id.Estrella5);
            seleccion=itemView.findViewById(R.id.seleccion);

        }
    }
}
