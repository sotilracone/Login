package com.example.login.Modelos;

import java.util.ArrayList;

public class Base {
    String nombre;
    String Imagen_B;
    ArrayList<String> Carnes;
    ArrayList<String>Frutas_Verduras_y_Hongos;
    ArrayList<String>Quesos;
    ArrayList<tamaños> Tamaños;

    public Base(String nombre, String imagen_B, ArrayList<String> carnes, ArrayList<String> frutas_Verduras_y_Hongos, ArrayList<String> quesos, ArrayList<tamaños> tamaños) {
        this.nombre = nombre;
        Imagen_B = imagen_B;
        Carnes = carnes;
        Frutas_Verduras_y_Hongos = frutas_Verduras_y_Hongos;
        Quesos = quesos;
        Tamaños = tamaños;
    }

    public Base() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getImagen_B() {
        return Imagen_B;
    }

    public void setImagen_B(String imagen_B) {
        Imagen_B = imagen_B;
    }

    public ArrayList<String> getCarnes() {
        return Carnes;
    }

    public void setCarnes(ArrayList<String> carnes) {
        Carnes = carnes;
    }

    public ArrayList<String> getFrutas_Verduras_y_Hongos() {
        return Frutas_Verduras_y_Hongos;
    }

    public void setFrutas_Verduras_y_Hongos(ArrayList<String> frutas_Verduras_y_Hongos) {
        Frutas_Verduras_y_Hongos = frutas_Verduras_y_Hongos;
    }

    public ArrayList<String> getQuesos() {
        return Quesos;
    }

    public void setQuesos(ArrayList<String> quesos) {
        Quesos = quesos;
    }

    public ArrayList<tamaños> getTamaños() {
        return Tamaños;
    }

    public void setTamaños(ArrayList<tamaños> tamaños) {
        Tamaños = tamaños;
    }
}
