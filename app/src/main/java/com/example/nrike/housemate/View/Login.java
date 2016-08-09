package com.example.nrike.housemate.View;

import android.annotation.TargetApi;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import com.example.nrike.housemate.Model.Facebooksdk.FacebookPreferences;
import com.example.nrike.housemate.Presenter.Login.LoginPresenter;
import com.example.nrike.housemate.R;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Login extends AppCompatActivity implements LoginView{


    LoginPresenter loginPresenter;

    @BindView(R.id.relativeLayoutLogin)
    RelativeLayout relativeLayoutLogin;

    FacebookPreferences facebookPreferences;

    LoginButton loginButton;
    CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        getSupportActionBar().hide();
        setColorTheme();

        facebookPreferences= new FacebookPreferences(getBaseContext());
        loginPresenter = new LoginPresenter(this,this);

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
                        loginPresenter.exist();

                    }

                    @Override
                    public void onCancel() {

                    }

                    @Override
                    public void onError(FacebookException exception) {
                    ;
                    }
                });


        loginButton.setEnabled(true);

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

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void setColorTheme(){
        int relativeBackgroundColor=0;
        int statusbarBackgroundColor=0;

        SharedPreferences preferences = getSharedPreferences("PREFS",MODE_PRIVATE);
        relativeBackgroundColor = preferences.getInt("colorPrimary",R.color.colorPrimary);
        statusbarBackgroundColor = preferences.getInt("colorPrimaryDark",R.color.colorPrimaryDark);

        relativeLayoutLogin.setBackgroundColor(getResources().getColor(relativeBackgroundColor));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().setStatusBarColor(ContextCompat.getColor(this, statusbarBackgroundColor));
        }


    }


    @Override
    public void enterMain() {
        EnterMain();
    }


}
