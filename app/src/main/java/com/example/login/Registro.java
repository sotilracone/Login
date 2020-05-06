package com.example.login;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;
import java.util.Map;

public class Registro extends AppCompatActivity {


    EditText Email, Contraseña, Pass, Cel;
    EditText NombreUser, ColUser, CPUser, CalleUser, CalleNum, CiudadUser, EstadoUser, UidNeg;
    Button Registrar;
    DatabaseReference mDataBase;
    FirebaseAuth auth;
    FirebaseUser UIUser;
    ImageView ImagenDeRepartidor;
    Uri foto = null;
    //Referencia de FireBase Storage
    StorageReference sRFb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        sRFb = FirebaseStorage.getInstance().getReference();
        mDataBase = FirebaseDatabase.getInstance().getReference();

        //Datos del Usuario Basicos
        ImagenDeRepartidor = (ImageView) findViewById(R.id.ImagenDeRepartidor);
        NombreUser = (EditText) findViewById(R.id.nameUser);
        Email = (EditText) findViewById(R.id.Email);
        Contraseña = (EditText) findViewById(R.id.Contraseña);
        Pass = (EditText) findViewById(R.id.ContraseñaConf);
        Cel = (EditText) findViewById(R.id.NumberPhone);
        //Datos Basicos de Direccion del Repartidor
        ColUser = (EditText) findViewById(R.id.ColUser);
        CPUser = (EditText) findViewById(R.id.CPUser);
        CalleUser = (EditText) findViewById(R.id.CalleUser);
        CalleNum = (EditText) findViewById(R.id.CalleNum);
        CiudadUser = (EditText) findViewById(R.id.CiudadUser);
        EstadoUser = (EditText) findViewById(R.id.EstadoUser);



        Registrar = (Button) findViewById(R.id.registrarFb);


        ImagenDeRepartidor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent penIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                penIntent.setType("image/");
                startActivityForResult(penIntent.createChooser(penIntent, "Seleccione la aplicacion"), 10);
            }
        });


        Registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Email.equals("") || Pass.equals("") || Cel.equals("") || Contraseña.equals("") || ColUser.equals("") || CPUser.equals("") || CalleUser.equals("") || CalleNum.equals("") || CiudadUser.equals("") ||
                        EstadoUser.equals("") || foto == null) {
                    if (foto == null) {
                        Toast.makeText(getApplicationContext(), "Falta la foto del usuario", Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(getApplicationContext(), "Falta datos", Toast.LENGTH_LONG).show();
                    }
                } else {

                    final String email = Email.getText().toString();
                    final String Password = Pass.getText().toString();
                    final String PhoneNumber = Cel.getText().toString();
                    String Confirmar = Contraseña.getText().toString();
                    final String Token = FirebaseInstanceId.getInstance().getToken();
                    final String Colonia = ColUser.getText().toString();
                    final String CodigoPostal = CPUser.getText().toString();
                    final String Calle = CalleUser.getText().toString();
                    final String NumeroCalle = CalleNum.getText().toString();
                    final String Ciudad = CiudadUser.getText().toString();
                    final String Estado = EstadoUser.getText().toString();
                    final String[] valorFoto = {""};

                    if (Password.equals(Confirmar)) {
                        auth = FirebaseAuth.getInstance();

                        auth.createUserWithEmailAndPassword(email, Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull final Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    auth.signInWithEmailAndPassword(email, Password);
                                    UIUser = auth.getCurrentUser();
                                    final StorageReference file = sRFb.child("Clientes").child(auth.getCurrentUser().getUid().toString()).child(foto.getLastPathSegment());
                                    file.putFile(foto).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                        @Override
                                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                            Toast.makeText(getApplicationContext(), "Si se subio.... se supone", Toast.LENGTH_LONG);
                                            valorFoto[0] = String.valueOf(taskSnapshot.getStorage().getDownloadUrl());
                                            String Nombre = NombreUser.getText().toString();
                                            Map<String, Object> map = new HashMap<>();
                                            map.put("nombre_Usuario", Nombre);
                                            map.put("Estado", Estado);
                                            map.put("Colonia", Colonia);
                                            map.put("CodigoPostal", CodigoPostal);
                                            map.put("Calle", Calle);
                                            map.put("NumeroTelefono", PhoneNumber);
                                            map.put("NumeroCalle", NumeroCalle);
                                            map.put("Ciudad", Ciudad);
                                            map.put("Imagen", valorFoto[0].toString());
                                            map.put(Token, Token);
                                            String id = auth.getCurrentUser().getUid();
                                            Log.d("id", id);
                                            mDataBase.child("Clientes").child(id).setValue(map);
                                            Intent intent = new Intent(getApplicationContext(), Inicio.class);
                                            startActivity(intent);
                                            Registro.this.finish();
                                        }
                                    });
                                }
                                else {
                                   Toast.makeText(getApplicationContext(),"Es posible que este correo ya este registrado",Toast.LENGTH_LONG).show();
                                }
                            }
                        });
                    }


                }

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            foto = data.getData();
            ImagenDeRepartidor.setImageURI(foto);
        }
    }
}