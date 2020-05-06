package com.example.login;

public class item {
    public String descuento;
    public String imagen;
    public String nombre;
    public String precio;
    public String descripcion;

    public item(String descuento, String imagen, String nombre, String precio,String descripcion) {
        this.descuento = descuento;
        this.imagen = imagen;
        this.nombre = nombre;
        this.precio = precio;
        this.descripcion=descripcion;
    }
    public item(){}

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescuento() {
        return descuento;
    }

    public void setDescuento(String descuento) {
        this.descuento = descuento;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
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
}