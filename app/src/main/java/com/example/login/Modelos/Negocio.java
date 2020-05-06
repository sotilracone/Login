package com.example.login.Modelos;

import java.util.ArrayList;

public class Negocio {
    String Calle_N;
    String Colonia_N;
    String Imagen_N;
    String Nombre_Encargado;
    String Nombre_Negocio;
    String NumeroC;
    String NumeroD_N;
    String Uid_Negocio;

    public Negocio(String calle_N, String colonia_N, String imagen_N, String nombre_Encargado, String nombre_Negocio, String numeroC, String numeroD_N, String uid_Negocio) {
        Calle_N = calle_N;
        Colonia_N = colonia_N;
        Imagen_N = imagen_N;
        Nombre_Encargado = nombre_Encargado;
        Nombre_Negocio = nombre_Negocio;
        NumeroC = numeroC;
        NumeroD_N = numeroD_N;
        Uid_Negocio = uid_Negocio;
    }

    public Negocio() {
    }

    public String getCalle_N() {
        return Calle_N;
    }

    public void setCalle_N(String call_N) {
        Calle_N = call_N;
    }

    public String getColonia_N() {
        return Colonia_N;
    }

    public void setColonia_N(String colonia_N) {
        Colonia_N = colonia_N;
    }

    public String getImagen_N() {
        return Imagen_N;
    }

    public void setImagen_N(String imagen_N) {
        Imagen_N = imagen_N;
    }

    public String getNombre_Encargado() {
        return Nombre_Encargado;
    }

    public void setNombre_Encargado(String nombre_Encargado) {
        Nombre_Encargado = nombre_Encargado;
    }

    public String getNombre_Negocio() {
        return Nombre_Negocio;
    }

    public void setNombre_Negocio(String nombre_Negocio) {
        Nombre_Negocio = nombre_Negocio;
    }

    public String getNumeroC() {
        return NumeroC;
    }

    public void setNumeroC(String numeroC) {
        NumeroC = numeroC;
    }

    public String getNumeroD_N() {
        return NumeroD_N;
    }

    public void setNumeroD_N(String numeroD_N) {
        NumeroD_N = numeroD_N;
    }

    public String getUid_Negocio() {
        return Uid_Negocio;
    }

    public void setUid_Negocio(String uid_Negocio) {
        Uid_Negocio = uid_Negocio;
    }
}