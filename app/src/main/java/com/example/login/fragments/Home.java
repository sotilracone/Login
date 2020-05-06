package com.example.login.fragments;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.login.Adaptador;
import com.example.login.Adaptadores.AdaptadorBase;
import com.example.login.Adaptadores.AdaptadorIngredientes;
import com.example.login.Adaptadores.AdaptadorNegocios;
import com.example.login.Adaptadores.AdaptadorTamaños;
import com.example.login.Modelos.Base;
import com.example.login.Modelos.Ingredientes;
import com.example.login.Modelos.Negocio;
import com.example.login.Modelos.tamaños;
import com.example.login.R;
import com.example.login.item;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.messaging.FirebaseMessaging;

import org.json.JSONObject;

import java.util.ArrayList;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Home extends Fragment {


    public Context mContext;
    RecyclerView RvNegocios,RvTamaño,RvBase,RvCarne,RvQueso,RvVegetales;
    ArrayList <Negocio> AlNegocios;
    ArrayList <Float>AlCalif;
    ArrayList <tamaños>AlTamaño;
    ArrayList<Base>AlBase;
    ArrayList<String> tokens;
    ArrayList<Ingredientes> AlCarne,AlQueso,AlVegetales;

    Button GuardarPizza;

    //Array de los seleccionados
    ArrayList<Integer> CarnesSeleccionados,VegetalesSeleccionados,QuesosSeleccionados;

    //Base de Datos
    FirebaseDatabase Databas;
    DatabaseReference RefNeg, RefBase,RefIng;

    //Adaptadores
    AdaptadorNegocios AdNeg;
    AdaptadorBase AdBa;
    AdaptadorTamaños AdTa;
    AdaptadorIngredientes AdInC,AdInV,AdInQ;
    LinearLayout Negocios,Base,Tamaño;

    //seleccionado
    int seleccionado,BaseSeleccionada,tamañoSeleccionado;
    boolean NegociosOculto,BaseOculto,TamañoOculto;


    //Tarjeta de Pizza
    ImageView ImagenPizzeria;
    TextView NomPizzeria,PrecioPizzaOriginal,Ingredientes_Extras,PrecioPorIngrediente,PrecioTotalporPizza;
    int IngredientesBase;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mContext = getContext();
        Databas = FirebaseDatabase.getInstance();
        seleccionado=-1;
        BaseSeleccionada=-1;
        RefNeg = Databas.getReference("Negocios");
        NegociosOculto=false;
        TamañoOculto=false;
        BaseOculto=false;
        View vista=inflater.inflate(R.layout.fragment_home, container, false);
        AlNegocios =new ArrayList();
        AlTamaño =new ArrayList();
        AlBase =new ArrayList();
        AlCarne =new ArrayList();
        AlQueso =new ArrayList();
        AlVegetales =new ArrayList();
        AlCalif =new ArrayList();
        tokens=new ArrayList();
        CarnesSeleccionados=new ArrayList();
        VegetalesSeleccionados=new ArrayList();
        QuesosSeleccionados=new ArrayList();
        //Colocamos los recycler
        RvNegocios=vista.findViewById(R.id.RvNegocios);
        RvTamaño=vista.findViewById(R.id.RvTamaño);
        RvBase=vista.findViewById(R.id.RvBase);
        RvCarne=vista.findViewById(R.id.RvCarne);
        RvQueso=vista.findViewById(R.id.RvQueso);
        RvVegetales=vista.findViewById(R.id.RvVegetales);

        //colocamos los textview
        NomPizzeria=vista.findViewById(R.id.NomPizzeria);
        PrecioPizzaOriginal=vista.findViewById(R.id.PrecioPizzaOriginal);
        Ingredientes_Extras=vista.findViewById(R.id.Ingredientes_Extras);
        PrecioPorIngrediente=vista.findViewById(R.id.PrecioPorIngrediente);
        PrecioTotalporPizza=vista.findViewById(R.id.PrecioTotalporPizza);
        ImagenPizzeria=vista.findViewById(R.id.ImagenPizzeria);
        //El botonsito de guardar
        GuardarPizza=vista.findViewById(R.id.GuardarPizza);


        //Linears
        Negocios=vista.findViewById(R.id.Negocios);
        Base=vista.findViewById(R.id.Base);
        Tamaño=vista.findViewById(R.id.Tamaño);


        Negocios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            if (NegociosOculto){
                RvNegocios.setVisibility(View.VISIBLE);
                NegociosOculto=false;
            }
            }
        });

        Base.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (BaseOculto){
                    RvBase.setVisibility(View.VISIBLE);
                    BaseOculto=false;
                }
            }
        });
        Tamaño.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TamañoOculto){
                    RvTamaño.setVisibility(View.VISIBLE);
                    TamañoOculto=false;
                }
            }
        });

        //Primero programamos  y Luego Comemos..... :v


        //Inciamos la consulta a la base de datos
        RefNeg.addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot!=null){
                    for (DataSnapshot Negocios: dataSnapshot.getChildren()){
                        if (Negocios!=null) {
                            Negocio neg = Negocios.getValue(Negocio.class);
                            AlNegocios.add(neg);
                            tokens.add(Negocios.child("tokens").child("token").getValue().toString());
                            if (Negocios.child("Calificacion") != null) {

                                int E1 = Integer.parseInt(Negocios.child("Calificacion").child("1").getValue().toString());
                                int E2 = Integer.parseInt(Negocios.child("Calificacion").child("2").getValue().toString());
                                int E3 = Integer.parseInt(Negocios.child("Calificacion").child("3").getValue().toString());
                                int E4 = Integer.parseInt(Negocios.child("Calificacion").child("4").getValue().toString());
                                int E5 = Integer.parseInt(Negocios.child("Calificacion").child("5").getValue().toString());
                                int Suma = E1 + E2 + E3 + E4 + E5;
                                int SumaTodos = E1 + (E2 * 2) + (E3 * 3) + (E4 * 4) + (E5 * 5);
                                float estrellas;
                                if (Suma != 0){
                                    estrellas=(float) (SumaTodos / Suma);
                                }
                                else {
                                    estrellas=3;
                                }
                                AlCalif.add(estrellas);
                            }
                        }
                    }
                }
                AdNeg.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



        //Sincronizamos El adapter de negocios Porque los demas estaran ocultos
        AdNeg=new AdaptadorNegocios(AlNegocios, AlCalif, mContext, seleccionado);
        AdNeg.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                seleccionado=RvNegocios.getChildAdapterPosition(v);
                AdNeg.seleccionado=seleccionado;
                AdNeg.notifyDataSetChanged();
                RvNegocios.setVisibility(View.GONE);
                NegociosOculto=true;
                RvBase.setVisibility(View.VISIBLE);
                ReiniciarValores();

                if (seleccionado>=0) {
                    Glide.with(mContext)
                            .load(AlNegocios.get(seleccionado).getImagen_N())
                            .placeholder(R.drawable.ic_launcher_background)
                            .error(R.drawable.ic_menu_camera)
                            .apply(RequestOptions.centerInsideTransform())
                            .into(ImagenPizzeria);
                    NomPizzeria.setText(AlNegocios.get(seleccionado).getNombre_Negocio());
                }

                RefBase=Databas.getReference("Pizzas");
                RefBase.child(AlNegocios.get(seleccionado).getUid_Negocio()).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        AlBase.clear();
                        IngredientesBase=0;

                        AlTamaño =new ArrayList();
                        AlBase =new ArrayList();
                        AlCarne =new ArrayList();
                        AlQueso =new ArrayList();
                        AlVegetales =new ArrayList();


                            if (dataSnapshot!=null){
                                for (DataSnapshot Pizzas:dataSnapshot.getChildren()){
                                    Base b=new Base();
                                    b.setNombre(Pizzas.child("nombre").getValue().toString());
                                    b.setImagen_B(Pizzas.child("Imagen_B").getValue().toString());
                                    ArrayList<String> CIng=new ArrayList<>();
                                    for (DataSnapshot Carnes:Pizzas.child("Carnes").getChildren()){
                                        CIng.add(Carnes.getValue().toString());
                                    }
                                    b.setCarnes(CIng);
                                    IngredientesBase+=CIng.size();
                                    ArrayList<String> QIng=new ArrayList<>();
                                    for (DataSnapshot Quesos:Pizzas.child("Quesos").getChildren()){
                                        QIng.add(Quesos.getValue().toString());
                                    }
                                    b.setQuesos(QIng);
                                    IngredientesBase+=QIng.size();
                                    ArrayList<String> VIng=new ArrayList<>();
                                    for (DataSnapshot Verduras:Pizzas.child("Frutas_Verduras_y_Hongos").getChildren()){
                                        VIng.add(Verduras.getValue().toString());
                                    }
                                    b.setFrutas_Verduras_y_Hongos(VIng);
                                    IngredientesBase+=VIng.size();
                                    ArrayList<tamaños> TS=new ArrayList<>();
                                    for (DataSnapshot Tam:Pizzas.child("Tamaños").getChildren()){
                                        TS.add(Tam.getValue(tamaños.class));
                                    }
                                    b.setTamaños(TS);
                                    AlBase.add(b);
                                }
                                AdBa=new AdaptadorBase(AlBase,mContext,BaseSeleccionada);
                                AdBa.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        BaseSeleccionada=RvBase.getChildAdapterPosition(v);
                                        AdBa.seleccionado=BaseSeleccionada;
                                        AdBa.notifyDataSetChanged();
                                        RvBase.setVisibility(View.GONE);
                                        BaseOculto=true;
                                        RvTamaño.setVisibility(View.VISIBLE);
                                        AdTa=new AdaptadorTamaños(AlBase.get(0).getTamaños(),mContext,-1);
                                        AdTa.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                tamañoSeleccionado=RvTamaño.getChildAdapterPosition(v);
                                                AdTa.seleccionado=tamañoSeleccionado;
                                                AdTa.notifyDataSetChanged();
                                                RvTamaño.setVisibility(View.GONE);
                                                TamañoOculto=true;
                                                if (tamañoSeleccionado>=0){
                                                    PrecioPizzaOriginal.setText(AlBase.get(0).getTamaños().get(tamañoSeleccionado).getPrecio().toString());
                                                    PrecioPorIngrediente.setText(AlBase.get(0).getTamaños().get(tamañoSeleccionado).getIngredienteExtra().toString());
                                                }

                                                AdInC = new AdaptadorIngredientes(AlCarne,mContext,CarnesSeleccionados);
                                                AdInQ = new AdaptadorIngredientes(AlQueso,mContext,QuesosSeleccionados);
                                                AdInV = new AdaptadorIngredientes(AlVegetales,mContext,VegetalesSeleccionados);

                                                RvCarne.setLayoutManager(new LinearLayoutManager(mContext,LinearLayoutManager.HORIZONTAL,false));
                                                RvCarne.setAdapter(AdInC);
                                                RvQueso.setLayoutManager(new LinearLayoutManager(mContext,LinearLayoutManager.HORIZONTAL,false));
                                                RvQueso.setAdapter(AdInQ);
                                                RvVegetales.setLayoutManager(new LinearLayoutManager(mContext,LinearLayoutManager.HORIZONTAL,false));
                                                RvVegetales.setAdapter(AdInV);
                                                RefIng = Databas.getReference("ingredientes").child(AlNegocios.get(seleccionado).getUid_Negocio());
                                                RefIng.addValueEventListener(new ValueEventListener() {
                                                    @Override
                                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                        AlCarne=new ArrayList<>();
                                                        AlQueso=new ArrayList<>();
                                                        AlVegetales=new ArrayList<>();
                                                        CarnesSeleccionados=new ArrayList<>();
                                                        QuesosSeleccionados=new ArrayList<>();
                                                        VegetalesSeleccionados=new ArrayList<>();
                                                        if (dataSnapshot != null) {
                                                            for (DataSnapshot Carnes : dataSnapshot.child("Carnes").getChildren()) {
                                                                if (Carnes.getKey() != null && Carnes.getKey()!="default") {
                                                                    AlCarne.add(Carnes.getValue(Ingredientes.class));
                                                                }
                                                            }
                                                            for (DataSnapshot Quesos : dataSnapshot.child("Quesos").getChildren()) {
                                                                if (Quesos.getKey() != null) {
                                                                    AlQueso.add(Quesos.getValue(Ingredientes.class));
                                                                }

                                                            }
                                                            for (DataSnapshot Vegetales : dataSnapshot.child("Frutas_Verduras_y_Hongos").getChildren()) {
                                                                if (Vegetales.getKey() != null) {
                                                                    AlVegetales.add(Vegetales.getValue(Ingredientes.class));
                                                                }
                                                            }

                                                        }
                                                        seleccionar();
                                                    }
                                                    @Override
                                                    public void onCancelled(@NonNull DatabaseError databaseError) {

                                                    }
                                                });
                                                AdInC.AlIngredientes=AlCarne;
                                                AdInQ.AlIngredientes=AlQueso;
                                                AdInV.AlIngredientes=AlVegetales;

                                                AdInC.setOnClickListener(new View.OnClickListener()
                                                {
                                                    @Override
                                                    public void onClick(View v) {
                                                        int i=RvCarne.getChildAdapterPosition(v);
                                                        if (!CarnesSeleccionados.contains(i)){
                                                            CarnesSeleccionados.add(i);
                                                        }
                                                        else {
                                                            for (int j = 0; j < CarnesSeleccionados.size(); j++) {
                                                                if (CarnesSeleccionados.get(j)==i){
                                                                    CarnesSeleccionados.remove(j);
                                                                }
                                                            }
                                                        }
                                                        AdInC.seleccionado=CarnesSeleccionados;
                                                        AdInC.notifyDataSetChanged();
                                                    }
                                                });


                                                AdInQ.setOnClickListener(new View.OnClickListener()
                                                {
                                                    @Override
                                                    public void onClick(View v) {
                                                        int i=RvQueso.getChildAdapterPosition(v);
                                                        if (!QuesosSeleccionados.contains(i)){
                                                            QuesosSeleccionados.add(i);
                                                        }
                                                        else {
                                                            for (int j = 0; j < QuesosSeleccionados.size(); j++) {
                                                                if (QuesosSeleccionados.get(j)==i){
                                                                    QuesosSeleccionados.remove(j);
                                                                }
                                                            }
                                                        }
                                                        AdInQ.seleccionado=QuesosSeleccionados;
                                                        AdInQ.notifyDataSetChanged();
                                                    }
                                                });


                                                AdInV.setOnClickListener(new View.OnClickListener()
                                                {
                                                    @Override
                                                    public void onClick(View v) {
                                                        int i=RvVegetales.getChildAdapterPosition(v);
                                                        if (!VegetalesSeleccionados.contains(i)){
                                                            VegetalesSeleccionados.add(i);
                                                        }
                                                        else {
                                                            for (int j = 0; j < VegetalesSeleccionados.size(); j++) {
                                                                if (VegetalesSeleccionados.get(j)==i){
                                                                    VegetalesSeleccionados.remove(j);
                                                                }
                                                            }
                                                        }
                                                        AdInV.seleccionado=VegetalesSeleccionados;
                                                        AdInV.notifyDataSetChanged();
                                                    }
                                                });

                                                RvQueso.setVisibility(View.VISIBLE);
                                                RvCarne.setVisibility(View.VISIBLE);
                                                RvVegetales.setVisibility(View.VISIBLE);

                                            }
                                        });
                                        RvTamaño.setLayoutManager(new LinearLayoutManager(mContext,LinearLayoutManager.HORIZONTAL,false));
                                        RvTamaño.setAdapter(AdTa);
                                    }
                                });
                                RvBase.setLayoutManager(new LinearLayoutManager(mContext,LinearLayoutManager.HORIZONTAL,false));
                                RvBase.setAdapter(AdBa);



                            }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });
        RvNegocios.setLayoutManager(new LinearLayoutManager(mContext,LinearLayoutManager.HORIZONTAL,false));
        RvNegocios.setAdapter(AdNeg);

        GuardarPizza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (seleccionado!=-1){
                    sendNotification(tokens.get(seleccionado),"","");

            }
            }
        });

        return vista;
    }

    private void ReiniciarValores() {
        BaseSeleccionada=-1;
        AlTamaño =new ArrayList();
        AlBase =new ArrayList();
        AlCarne =new ArrayList();
        AlQueso =new ArrayList();
        AlVegetales =new ArrayList();
    }

     public void seleccionar(){
        CarnesSeleccionados.clear();
        QuesosSeleccionados.clear();
        VegetalesSeleccionados.clear();
         for (int i = 0; i < AlCarne.size(); i++) {
             if (AlBase.get(BaseSeleccionada).getCarnes().contains(AlCarne.get(i).getNombre_I())){
                 for (int j = 0; j < AlBase.get(BaseSeleccionada).getCarnes().size(); j++) {
                     if (AlBase.get(BaseSeleccionada).getCarnes().get(j).equals(AlCarne.get(i).getNombre_I())){
                         CarnesSeleccionados.add(i);
                     }
                 }
             }
         }

         for (int k = 0; k < AlQueso.size(); k++) {
             if (AlBase.get(BaseSeleccionada).getQuesos().contains(AlQueso.get(k).getNombre_I())){
                 for (int j = 0; j < AlBase.get(BaseSeleccionada).getQuesos().size(); j++) {
                     if (AlBase.get(BaseSeleccionada).getQuesos().get(j).equals(AlQueso.get(k).getNombre_I())){
                         QuesosSeleccionados.add(k);
                     }
                 }
             }
         }

         for (int l = 0; l < AlVegetales.size(); l++) {
             if (AlBase.get(BaseSeleccionada).getQuesos().contains(AlVegetales.get(l).getNombre_I())){
                 for (int j = 0; j < AlBase.get(BaseSeleccionada).getFrutas_Verduras_y_Hongos().size(); j++) {
                     if (AlBase.get(BaseSeleccionada).getFrutas_Verduras_y_Hongos().get(j).equals(AlVegetales.get(l).getNombre_I())){
                         VegetalesSeleccionados.add(l);
                     }
                 }
             }
         }
         Toast.makeText(mContext, IngredientesBase+"", Toast.LENGTH_SHORT).show();
     }


    public void sendNotification(String regToken, final String title, final String message) {
        //   Log.d(TAG, "sendNotification: TOKEN "+regToken);
        final String regToken2 = regToken;
        Toast.makeText(mContext, regToken, Toast.LENGTH_SHORT).show();
        FirebaseMessaging.getInstance().setAutoInitEnabled(true);
        final String LEGACY_SERVER_KEY = "AAAAMB5gl_Q:APA91bENwskrjMi18K9kbDjyp2kfgBi5dM8ziI63Zijd8P0ip4Iyb1cQRKMo8afg6Hu1J680sgCFft0NNcs5wNCE5lwsC3NeXBHgfGuUd1LAE8w9kgv7Ak28ueFlw7n3xSw9AwVvfgfe";
        final MediaType JSON
                = MediaType.parse("application/json; charset=utf-8");

        new AsyncTask<Void,Void,Void>(){
            @Override
            protected Void doInBackground(Void... params) {
                try {
                    OkHttpClient client = new OkHttpClient();
                    JSONObject json=new JSONObject();
                    JSONObject dataJson=new JSONObject();
                    dataJson.put("body","Se te ah solicitado una pizza");
                    dataJson.put("title","PizzJamApp");
                    dataJson.put("sound","default");
                    dataJson.put("priority","alta");

                    json.put("data",dataJson);
                    Log.d("TOKEN_DEL_NEGOCIO","El token es"+regToken2);
                    json.put("to",regToken2);
                    RequestBody body = RequestBody.create(JSON, json.toString());
                    Request request = new Request.Builder()
                            .header("Authorization","key="+ LEGACY_SERVER_KEY)
                            .url("https://fcm.googleapis.com/fcm/send")
                            .post(body)
                            .build();
                    Response response = client.newCall(request).execute();
                    String finalResponse = response.body().string();
                }catch (Exception e){
                    //Log.d(TAG,e+"");
                }
                return null;
            }
        }.execute();



    }
}
