package com.example.login;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    Button Aceptar,registra,lista;
    EditText correo, contra;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //  ImageView imageView = (ImageView) findViewById(R.id.imageView);
        // Glide.with(this).load("https://waicllstkers4you.com/products/vinyl-wall-decal-coffee-lover-physical-formula-funny-cafe-art-decor-stickers-mural-ig5392").into(imageView);

        Aceptar = findViewById(R.id.btAceptar);
        lista = findViewById(R.id.btlista);
        registra = findViewById(R.id.btregistrarse);
        correo = findViewById(R.id.Correo);
        contra = findViewById(R.id.pas);

        registra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Registro.class));
            }
        });

        lista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Inicio.class));
            }
        });

        Aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iniciarSesion();
            }
        });
    }


    private void iniciarSesion() {

        String pass = contra.getText().toString();
        String corre = correo.getText().toString();

        if(!corre.isEmpty()){
            if(!pass.isEmpty()){
                FirebaseAuth mauth = FirebaseAuth.getInstance();
                mauth.signInWithEmailAndPassword(corre, pass).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            startActivity(new Intent(getApplicationContext(), Inicio.class));
                        }else{
                            Toast.makeText(MainActivity.this, "Error al iniciar sesión", Toast.LENGTH_SHORT).show();
                        }
                    }
                });


            }else{
                contra.setError("Ingresa password");
            }

        }else{
            correo.setError("Ingresa un correo");
        }
    }
}
