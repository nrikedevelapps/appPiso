package com.example.nrike.housemate.Model.Entity;

/**
 * Created by develapps15 on 2/8/16.
 */
public class Product {

    String name;
    String quantity;
    String image;

    public Product(String name, String quantity, String imagen) {
        this.name = name;
        this.quantity = quantity;
        this.image = imagen;
    }

    public String getImagen() {
        return image;
    }

    public void setImagen(String imagen) {
        this.image = imagen;
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
