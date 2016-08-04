package com.example.nrike.housemate.Model.Entity;

import android.graphics.drawable.Drawable;

/**
 * Created by nrike on 2/08/16.
 */
public class User {

    String name;
    int buy_products;
    Drawable image_user;

    public User(String name, int buy_products, Drawable image_user) {
        this.name = name;
        this.buy_products = buy_products;
        this.image_user = image_user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBuy_products() {
        return buy_products;
    }

    public void setBuy_products(int buy_products) {
        this.buy_products = buy_products;
    }

    public Drawable getImage_user() {
        return image_user;
    }

    public void setImage_user(Drawable image_user) {
        this.image_user = image_user;
    }
}
