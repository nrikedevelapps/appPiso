package com.example.nrike.housemate.View;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.example.nrike.housemate.Model.entity.Product;
import com.example.nrike.housemate.Model.entity.User;
import com.example.nrike.housemate.R;

import org.lucasr.twowayview.TwoWayView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    TwoWayView productsList;
    ListView usersList;

    ListAdapter listAdapter ;
    ListAdapterUser listAdapterUser;

    List<Product> products;
    List<User> users;

    public void setUI(){
        productsList = (TwoWayView) findViewById(R.id.lvItems);
        usersList = (ListView) findViewById(R.id.listView);
    }

    public void newProducts(){
        products = new ArrayList<>();
        products.add(new Product("Papel pa la mierda","99",R.drawable.prueba));
        products.add(new Product("Papel pa la mierda","99",R.drawable.prueba));
        products.add(new Product("Papel pa la mierda","99",R.drawable.prueba));
        products.add(new Product("Papel pa la mierda","99",R.drawable.prueba));
        products.add(new Product("Papel pa la mierda","99",R.drawable.prueba));
        products.add(new Product("Papel pa la mierda","99",R.drawable.prueba));
        products.add(new Product("Papel pa la mierda","99",R.drawable.prueba));
    }

    public void newUsers(){
        users = new ArrayList<>();
        users.add(new User("PEPE",13,getResources().getDrawable(R.drawable.prueba)));
        users.add(new User("PEPE",13,getResources().getDrawable(R.drawable.prueba)));
        users.add(new User("PEPE",13,getResources().getDrawable(R.drawable.prueba)));
        users.add(new User("PEPE",13,getResources().getDrawable(R.drawable.prueba)));

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUI();
        //getSupportActionBar().hide();


        newProducts();
        newUsers();

        listAdapter = new ListAdapter(getBaseContext(),products);
        productsList.setAdapter(listAdapter);

        listAdapterUser = new ListAdapterUser(getBaseContext(),users);
        usersList.setAdapter(listAdapterUser);




    }
}
