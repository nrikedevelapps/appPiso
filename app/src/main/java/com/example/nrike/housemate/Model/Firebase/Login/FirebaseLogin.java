package com.example.nrike.housemate.Model.Firebase.Login;

import android.os.Handler;
import android.util.Log;

import com.example.nrike.housemate.Model.Entity.User;
import com.example.nrike.housemate.Presenter.Login.LoginPresenterView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by develapps15 on 8/8/16.
 */
public class FirebaseLogin {

    LoginPresenterView loginPresenterView;

    public FirebaseLogin(LoginPresenterView loginPresenterView) {
        this.loginPresenterView = loginPresenterView;
    }

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference users = database.getReference("users");

    public void uruarioPrueba(String nombre, User user){
        users.child(nombre).setValue(user);
    }

    Boolean userExist = false;
    public void existeUsuario(String user){

        users.child(user).addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if(dataSnapshot.getValue()!=null){
                            userExist = true;
                        }
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Log.w("Error", "getUser:onCancelled", databaseError.toException());
                    }
                });

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(userExist){
                    loginPresenterView.userAlreadyExist();
                }else{
                    loginPresenterView.userNotExist();
                }
            }
        }, 400);



    }

}
