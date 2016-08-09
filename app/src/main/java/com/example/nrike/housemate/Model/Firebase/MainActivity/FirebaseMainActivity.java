package com.example.nrike.housemate.Model.Firebase.MainActivity;

import android.content.Context;
import android.os.Handler;
import android.util.Log;

import com.example.nrike.housemate.Model.Entity.User;
import com.example.nrike.housemate.Presenter.MainActivity.MainActivityPresenterView;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by develapps15 on 9/8/16.
 */
public class FirebaseMainActivity {

    Context context;
    MainActivityPresenterView mainActivityPresenterView;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference users;

    List<User> list_users;


    public FirebaseMainActivity(Context context, MainActivityPresenterView mainActivityPresenterView) {
        this.context = context;
        this.mainActivityPresenterView = mainActivityPresenterView;
        list_users = new ArrayList<>();
    }



    public void updateListUsers(){

        users= database.getReference();
        final Query Users = users.child("users");
        ChildEventListener childEventListener;
        childEventListener = Users.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                //Add new users

                String name = String.valueOf(dataSnapshot.child("name").getValue());
                int buy_products = Integer.parseInt(String.valueOf(dataSnapshot.child("buy_products").getValue()));
                String image = String.valueOf(dataSnapshot.child("image_user").getValue());

                User user = new User(name,buy_products,image);
                list_users.add(user);
                Log.i("USERS_NUM","child >>"+list_users.size());

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                String name = String.valueOf(dataSnapshot.child("name").getValue());
                int buy_products = Integer.parseInt(String.valueOf(dataSnapshot.child("buy_products").getValue()));
                String image = String.valueOf(dataSnapshot.child("image_user").getValue());

                User aux = new User(name,buy_products,image);

                for(int i =0; i<= list_users.size()-1;i++){
                    User user = list_users.get(i);
                    if(aux.getName().equals(user.getName())){
                        list_users.remove(user);
                        list_users.add(i,aux);

                    }
                }
                mainActivityPresenterView.updateUsers(list_users);

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

                String name = String.valueOf(dataSnapshot.child("name").getValue());
                int buy_products = Integer.parseInt(String.valueOf(dataSnapshot.child("buy_products").getValue()));
                String image = String.valueOf(dataSnapshot.child("image_user").getValue());

                User aux = new User(name,buy_products,image);

                for(int i =0; i<= list_users.size()-1;i++){
                    if(list_users.get(i).getName().equals(aux.getName())){
                        list_users.remove(i);
                        System.out.println(list_users.size());
                    }
                }

                mainActivityPresenterView.updateUsers(list_users);

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mainActivityPresenterView.loadUserList(list_users);
                mainActivityPresenterView.updateUsers(list_users);
                Log.i("USERS_NUM","lectura >>"+list_users.size());
            }
        }, 3000);

       // users.removeEventListener(childEventListener);

    }




}
