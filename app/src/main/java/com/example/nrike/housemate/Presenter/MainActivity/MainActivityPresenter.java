package com.example.nrike.housemate.Presenter.MainActivity;

import android.content.Context;
import android.net.Uri;

import com.example.nrike.housemate.Model.Entity.Product;
import com.example.nrike.housemate.Model.Entity.User;
import com.example.nrike.housemate.Model.Facebooksdk.FacebookPreferences;
import com.example.nrike.housemate.Model.Firebase.MainActivity.FirebaseMainActivity;
import com.example.nrike.housemate.View.MainActivityView;

import java.util.List;

/**
 * Created by develapps15 on 9/8/16.
 */
public class MainActivityPresenter implements MainActivityPresenterView{

    MainActivityView mainActivityView;
    Context context;

    FacebookPreferences loginPresenter;
    FirebaseMainActivity firebaseMainActivity;

    public MainActivityPresenter(MainActivityView mainActivityView, Context context) {
        this.mainActivityView = mainActivityView;
        this.context = context;
        loginPresenter = new FacebookPreferences(context);
        firebaseMainActivity = new FirebaseMainActivity(context,this);
    }

    public void updateListUsers(){
        firebaseMainActivity.updateListUsers();
    }

    public void updateListProducts(){
        firebaseMainActivity.updateListProducts();
    }

    public void addProduct(String name, String quantity, Uri uri){
        firebaseMainActivity.uploadProduct(name,quantity,uri);

    }

    public void logOut(){
        loginPresenter.LoginFalse();
    }



    @Override
    public void loadUserList(List<User> users) {
        mainActivityView.loadUser(users);
    }

    @Override
    public void loadProductList(List<Product> products) {
        mainActivityView.loadProduct(products);
    }

    @Override
    public void updateUsers(List<User> users) {
        mainActivityView.updateUsers(users);
    }

    @Override
    public void updateProductList(List<Product> products) {
        mainActivityView.updateProducts(products);
    }
}
