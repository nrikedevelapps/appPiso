package com.example.nrike.housemate.Presenter.MainActivity;

import android.content.Context;

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

    public void updateList(){
        firebaseMainActivity.updateListUsers();
    }



    public void logOut(){
        loginPresenter.LoginFalse();
    }



    @Override
    public void loadUserList(List<User> users) {
        mainActivityView.LoadUser(users);
    }

    @Override
    public void updateUsers(List<User> users) {
        mainActivityView.updateUsers(users);
    }
}
