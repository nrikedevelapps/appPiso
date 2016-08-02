package com.example.nrike.housemate.Model.entity;

/**
 * Created by develapps15 on 2/8/16.
 */
public class Product {

    String name;
    String quantity;
    int imagen;

    public Product(String name, String quantity, int imagen) {
        this.name = name;
        this.quantity = quantity;
        this.imagen = imagen;
    }

    public int getImagen() {
        return imagen;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
}
