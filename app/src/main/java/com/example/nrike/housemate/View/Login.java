package com.example.nrike.housemate.View;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.nrike.housemate.Model.Facebooksdk.FacebookPreferences;
import com.example.nrike.housemate.R;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

public class Login extends AppCompatActivity{

    FacebookPreferences facebookPreferences;

    LoginButton loginButton;
    CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();

        facebookPreferences= new FacebookPreferences(getBaseContext());

        EnterMain();

        loginButton = (LoginButton) findViewById(R.id.login_button);
        loginButton.setPadding(50,50,50,50);

        callbackManager = CallbackManager.Factory.create();
        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        facebookPreferences.GetProfileData(loginResult);
                        facebookPreferences.LoginTrue();
                        EnterMain();
                    }

                    @Override
                    public void onCancel() {

                    }

                    @Override
                    public void onError(FacebookException exception) {
                    ;
                    }
                });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    public void EnterMain(){
        Intent i = new Intent(Login.this,MainActivity.class);
        SharedPreferences preferences = getSharedPreferences("PREFS",MODE_PRIVATE);
        if(preferences.getBoolean("enter_main",false)){
            //overridePendingTransition();
            startActivity(i);
            finish();
        }
    }


}
