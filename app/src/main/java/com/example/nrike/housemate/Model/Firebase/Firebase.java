package com.example.nrike.housemate.Model.Firebase;

import android.util.Log;

import com.example.nrike.housemate.Model.Entity.User;
import com.example.nrike.housemate.View.LoginView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by develapps15 on 8/8/16.
 */
public class Firebase  {

    LoginView loginView;

    public Firebase(LoginView loginView) {
        this.loginView = loginView;
    }

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference users = database.getReference("users");

    public void uruarioPrueba(String nombre, User user){
        users.child(nombre).setValue(user);
    }

    public void existeUsuario(){

        final boolean[] exist = {false};
        users.child("message").child("NRIKITO").addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        // Get user value
                        exist[0] =true;
                        System.out.println(dataSnapshot);
                        loginView.enterMain();



                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Log.w("eeerorr", "getUser:onCancelled", databaseError.toException());
                    }
                });

    }

}
