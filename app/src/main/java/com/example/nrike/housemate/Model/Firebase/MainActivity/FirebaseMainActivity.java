package com.example.nrike.housemate.Model.Firebase.MainActivity;

import android.content.Context;
import android.net.Uri;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.example.nrike.housemate.Model.Entity.Product;
import com.example.nrike.housemate.Model.Entity.User;
import com.example.nrike.housemate.Model.tools.Tools;
import com.example.nrike.housemate.Presenter.MainActivity.MainActivityPresenterView;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by develapps15 on 9/8/16.
 */
public class FirebaseMainActivity {

    Context context;
    MainActivityPresenterView mainActivityPresenterView;
    Tools tools;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference users;
    DatabaseReference products;

    List<User> list_users;
    List<Product> list_products;


    public FirebaseMainActivity(Context context, MainActivityPresenterView mainActivityPresenterView) {
        this.context = context;
        this.mainActivityPresenterView = mainActivityPresenterView;
        list_users = new ArrayList<>();
        list_products = new ArrayList<>();
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
        }, 2500);

       // users.removeEventListener(childEventListener);

    }

    public void updateListProducts(){
        products = database.getReference();
        Query query = products.child("products");
        query.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                String name = String.valueOf(dataSnapshot.child("name").getValue());
                String image = String.valueOf(dataSnapshot.child("imagen").getValue());
                String quantity = String.valueOf(dataSnapshot.child("quantity").getValue());

                Product product = new Product(name,quantity,image);
                list_products.add(product);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                String name = String.valueOf(dataSnapshot.child("name").getValue());
                String image = String.valueOf(dataSnapshot.child("imagen").getValue());
                String quantity = String.valueOf(dataSnapshot.child("quantity").getValue());

                Product aux = new Product(name,quantity,image);

                for(int i =0; i<= list_products.size()-1;i++){
                    Product product = list_products.get(i);
                    if(aux.getName().equals(product.getName())){
                        list_products.remove(product);
                        list_products.add(i,aux);

                    }
                }
                mainActivityPresenterView.updateProductList(list_products);
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

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
                mainActivityPresenterView.loadProductList(list_products);
            }
        },2500);
    }

    public void uploadProduct(final String name_product, final String quantity , Uri uri){
        tools = new Tools();
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageRef = storage.getReferenceFromUrl("gs://housemate-a8acf.appspot.com");
        StorageReference imagesRef = storageRef.child(name_product);

        UploadTask uploadTask = null;
        try {
            uploadTask = imagesRef.putBytes(tools.uriToByteArray(context,uri));
            uploadTask.addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception exception) {
                    Toast.makeText(context, "No se ha subido correctamente el archivo", Toast.LENGTH_SHORT).show();
                }
            }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Uri downloadUrl = taskSnapshot.getDownloadUrl();
                    Toast.makeText(context, "Imagen subida", Toast.LENGTH_SHORT).show();
                    Product save_product;

                    save_product= new Product(name_product,quantity,String.valueOf(downloadUrl));
                    saveProduct(save_product);
                }
            });
        } catch (IOException e) {
            Toast.makeText(context, "Fallo inseperado en el servidor", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    public void saveProduct(Product product){
        products = database.getReference("products");
        products.child(product.getName()).setValue(product);
    }


}
