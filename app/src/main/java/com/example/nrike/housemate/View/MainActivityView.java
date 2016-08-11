package com.example.nrike.housemate.View;

import com.example.nrike.housemate.Model.Entity.Product;
import com.example.nrike.housemate.Model.Entity.User;

import java.util.List;

/**
 * Created by develapps15 on 9/8/16.
 */
public interface MainActivityView {

    public void loadUser(List<User> users);

    public void updateUsers(List<User> users);

    public void loadProduct(List<Product> products);

    public void updateProducts(List<Product> products);

}
