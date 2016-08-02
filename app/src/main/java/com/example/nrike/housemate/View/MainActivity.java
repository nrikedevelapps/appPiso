package com.example.nrike.housemate.View;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.nrike.housemate.Model.entity.Product;
import com.example.nrike.housemate.R;

import org.lucasr.twowayview.TwoWayView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    TwoWayView listaProductos ;
    ListAdapter listAdapter ;

    List<Product> products;

    public void setUI(){
      listaProductos = (TwoWayView) findViewById(R.id.lvItems);

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUI();
        //getSupportActionBar().hide();


        newProducts();

        listAdapter = new ListAdapter(getBaseContext(),products);
        listaProductos.setAdapter(listAdapter);




    }
}
