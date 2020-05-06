package com.example.login;

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

import java.util.ArrayList;

public class Adaptador extends RecyclerView.Adapter<Adaptador.ViewHolder> implements View.OnClickListener {

    private ArrayList<item> Lista;
    private View.OnClickListener listener;

    Context mContext;
    public Adaptador(Context mContext, ArrayList<item> lista) {
        this.mContext=mContext;
        this.Lista=new ArrayList<item>();
        this.Lista.addAll(lista);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_list,viewGroup,false);

        view.setOnClickListener(this);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder Holder, int i) {
        Holder.tvDescuento.setText(Lista.get(i).getDescuento());
        try {
            Glide.with(mContext)
                    .load(Lista.get(i).getImagen())
                    .placeholder(R.drawable.ic_launcher_background)
                    .error(R.drawable.ic_menu_camera)
                    .apply(RequestOptions.centerInsideTransform())
                    .into(Holder.ivImagen);

        }catch(Exception e){
            Holder.ivImagen.setImageResource(R.drawable.common_google_signin_btn_icon_dark);
        }
        Holder.tvNombre.setText(Lista.get(i).getNombre());
        Holder.tvPrecio.setText(Lista.get(i).getPrecio());
    }

    @Override
    public int getItemCount() {
        return Lista.size();
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
        ImageView ivImagen;
        TextView tvNombre;
        TextView tvPrecio;
        TextView tvDescuento;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivImagen=(ImageView) itemView.findViewById(R.id.ivImagen);
            tvNombre=(TextView) itemView.findViewById(R.id.tvNombre);
            tvPrecio=(TextView) itemView.findViewById(R.id.tvPrecio);
            tvDescuento=(TextView) itemView.findViewById(R.id.tvDescuento);
        }
    }
}
