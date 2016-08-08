package com.example.nrike.housemate.Presenter;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.nrike.housemate.Model.Entity.User;
import com.example.nrike.housemate.Model.Firebase.Firebase;
import com.example.nrike.housemate.View.LoginView;

/**
 * Created by develapps15 on 8/8/16.
 */
public class LoginPresenter {
    Firebase firebase;
    Context context;
    LoginView loginView;

    public LoginPresenter(Context context, LoginView loginView) {
        this.context = context;
        this.loginView = loginView;
        firebase = new Firebase(loginView);
    }

    public void newUser(){
        SharedPreferences preferences = context.getSharedPreferences("PREFS",context.MODE_PRIVATE);
        User userPrueba = new User(preferences.getString("user_name","nada"),0,preferences.getString("user_name","nada"));
        firebase.uruarioPrueba("NRIKITO",userPrueba);
    }

    public void exist(){
        firebase.existeUsuario();
    }
}
