package com.example.nrike.housemate.Presenter.Login;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.nrike.housemate.Model.Entity.User;
import com.example.nrike.housemate.Model.Firebase.Login.FirebaseLogin;
import com.example.nrike.housemate.View.LoginView;

/**
 * Created by develapps15 on 8/8/16.
 */
public class LoginPresenter implements LoginPresenterView {

    FirebaseLogin firebase;
    Context context;
    LoginView loginView;

    public LoginPresenter(Context context, LoginView loginView) {
        this.context = context;
        this.loginView = loginView;
        firebase = new FirebaseLogin(this);
    }
    SharedPreferences preferences;
    public void newUser(){
        preferences = context.getSharedPreferences("PREFS",context.MODE_PRIVATE);
        String name = preferences.getString("user_name","unknown");
        String img_user = preferences.getString("user_image_profile","unknown");
        User user = new User(name,0,img_user);
        firebase.uruarioPrueba(name,user);
    }

    public void exist(){
        preferences = context.getSharedPreferences("PREFS",context.MODE_PRIVATE);
        firebase.existeUsuario(preferences.getString("user_name","unknown"));
    }



    @Override
    public void userAlreadyExist() {
        loginView.enterMain();
    }

    @Override
    public void userNotExist() {
        newUser();
        loginView.enterMain();
    }
}
