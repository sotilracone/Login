package com.example.login.Modelos;

public class tamaños {
    String diametro;
    String nombre;
    String precio;
    String IngredienteExtra;

    public tamaños(String diametro, String nombre, String precio, String ingredienteExtra) {
        this.diametro = diametro;
        this.nombre = nombre;
        this.precio = precio;
        IngredienteExtra = ingredienteExtra;
    }

    public tamaños() {
    }

    public String getDiametro() {
        return diametro;
    }

    public void setDiametro(String diametro) {
        this.diametro = diametro;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getIngredienteExtra() {
        return IngredienteExtra;
    }

    public void setIngredienteExtra(String ingredienteExtra) {
        IngredienteExtra = ingredienteExtra;
    }
}
