package com.example.login.Modelos;

public class Ingredientes {
    String Imagen_I;
    String Nombre_I;

    public Ingredientes(String imagen_I, String nombre_I) {
        Imagen_I = imagen_I;
        Nombre_I = nombre_I;
    }

    public Ingredientes() {
    }

    public String getImagen_I() {
        return Imagen_I;
    }

    public void setImagen_I(String imagen_I) {
        Imagen_I = imagen_I;
    }

    public String getNombre_I() {
        return Nombre_I;
    }

    public void setNombre_I(String nombre_I) {
        Nombre_I = nombre_I;
    }
}
