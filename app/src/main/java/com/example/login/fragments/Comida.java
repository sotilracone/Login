package com.example.login.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.login.Adaptador;
import com.example.login.Contenido;
import com.example.login.R;
import com.example.login.item;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;

public class Comida extends Fragment {

    RecyclerView recycler;
    ArrayList<item> Lista;
    DatabaseReference dbr;
    Context mContext;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mContext=getContext();
        View vista=inflater.inflate(R.layout.fragment_comida, container, false);
        recycler=(RecyclerView) vista.findViewById(R.id.Recyclerhelados);
        recycler.setLayoutManager(new GridLayoutManager(mContext,2));
        dbr= FirebaseDatabase.getInstance().getReference();
        Lista=new ArrayList<>();

        dbr.child("menu").child("Comida").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                try {


                    if(dataSnapshot.exists()){
                        Lista.clear();

                        for (DataSnapshot chat:dataSnapshot.getChildren()){
                            Lista.add(chat.getValue(item.class));
                        }
                        Adaptador adapter=new Adaptador(mContext,Lista);
                        adapter.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent in=new Intent(mContext, Contenido.class);
                                in.putExtra("Descripcion",Lista.get(recycler.getChildAdapterPosition(view)).getDescripcion());
                                in.putExtra("Foto",Lista.get(recycler.getChildAdapterPosition(view)).getImagen());
                                startActivity(in);
                            }
                        });
                        recycler.setAdapter(adapter);
                    }
                }catch(Exception e){

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return vista;
    }
}