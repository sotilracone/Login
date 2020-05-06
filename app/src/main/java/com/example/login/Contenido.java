package com.example.login;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

public class Contenido extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contenido);
        Button btAceptar;
        btAceptar = findViewById(R.id.btn_ir_inicioSesion);
        btAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Inicio.class));
            }
        });

        TextView Describe;
        ImageView fot;
        fot=findViewById(R.id.imagen);
        Describe=findViewById(R.id.texto);


        String receive = getIntent().getExtras().getString("Descripcion");
        String imag= getIntent().getExtras().getString("Foto");

        Describe.setText(receive);

            Glide.with(this)
                    .load(imag)
                    .error(R.drawable.ic_menu_camera)
                    .into(fot);
    }

}

