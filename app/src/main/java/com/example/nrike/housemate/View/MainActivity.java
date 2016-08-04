package com.example.nrike.housemate.View;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.example.nrike.housemate.Model.Entity.Product;
import com.example.nrike.housemate.Model.Entity.User;
import com.example.nrike.housemate.Model.Facebooksdk.FacebookPreferences;
import com.example.nrike.housemate.R;
import com.facebook.login.LoginManager;

import org.lucasr.twowayview.TwoWayView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    FacebookPreferences loginPresenter;

    ListAdapter listAdapter ;
    ListAdapterUser listAdapterUser;

    List<Product> products;
    List<User> users;

    @BindView(R.id.floatbuttons)
    RelativeLayout floatbuttons;

    @BindView(R.id.btbuy)
    FloatingActionButton btbuy;

    @BindView(R.id.btmore)
    FloatingActionButton btmore;

    @BindView(R.id.listView)
    ListView usersList;

    @BindView(R.id.lvItems)
    TwoWayView productsList;

    @BindView(R.id.HorizontalList)
    RelativeLayout horizontalList;

    @OnClick (R.id.btbuy)
    public void Click_btbuy(){
        int visible = horizontalList.getVisibility();
        if(visible== View.GONE){
            horizontalList.setVisibility(View.VISIBLE);
            btmore.setVisibility(View.VISIBLE);
            btbuy.setImageDrawable(getResources().getDrawable(android.R.drawable.ic_menu_close_clear_cancel));;

        }else{
            horizontalList.setVisibility(View.GONE);
            btmore.setVisibility(View.GONE);
            btbuy.setImageDrawable(getResources().getDrawable(android.R.drawable.ic_menu_crop));;
        }
    }

    @OnClick(R.id.btmore)
    public void Click_btmore(){
        //ToastView
        dialogNewProduct();
    }

    public void newProducts(){
        products = new ArrayList<>();
        products.add(new Product("Papel pa la mierda","2",R.drawable.prueba));
        products.add(new Product("Papel pa","34",R.drawable.degradate));
        products.add(new Product("Papel pa la mierda","45",R.drawable.new_product));
        products.add(new Product("Papel pa la mierda","12",R.drawable.part_menu));
        products.add(new Product("Papel ","99",R.drawable.prueba));
        products.add(new Product("Papel pa la mierda","23",R.drawable.user));
        products.add(new Product("Papel pa la mierda","12",R.drawable.prueba));
    }

    public void newUsers(){
        users = new ArrayList<>();
        users.add(new User("PEPE",13,getResources().getDrawable(R.drawable.prueba)));
        users.add(new User("PEPE",13,getResources().getDrawable(R.drawable.prueba)));
        users.add(new User("PEPE",13,getResources().getDrawable(R.drawable.prueba)));
        users.add(new User("PEPE",13,getResources().getDrawable(R.drawable.prueba)));
        users.add(new User("PEPE",13,getResources().getDrawable(R.drawable.prueba)));
        users.add(new User("PEPE",13,getResources().getDrawable(R.drawable.prueba)));
        users.add(new User("PEPE",13,getResources().getDrawable(R.drawable.prueba)));
        users.add(new User("PEPE",13,getResources().getDrawable(R.drawable.prueba)));
        users.add(new User("PEPE",13,getResources().getDrawable(R.drawable.prueba)));
        users.add(new User("PEPE",13,getResources().getDrawable(R.drawable.prueba)));
        users.add(new User("PEPE",13,getResources().getDrawable(R.drawable.prueba)));
        users.add(new User("PEPE",13,getResources().getDrawable(R.drawable.prueba)));
        users.add(new User("PEPE",13,getResources().getDrawable(R.drawable.prueba)));
        users.add(new User("PEPE",13,getResources().getDrawable(R.drawable.prueba)));
        users.add(new User("PEPE",13,getResources().getDrawable(R.drawable.prueba)));
        users.add(new User("PEPE",13,getResources().getDrawable(R.drawable.prueba)));

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        horizontalList.setVisibility(View.GONE);
        btmore.setVisibility(View.GONE);

        loginPresenter = new FacebookPreferences(getBaseContext());

        newProducts();
        newUsers();

        listAdapter = new ListAdapter(getBaseContext(),products);
        productsList.setAdapter(listAdapter);

        listAdapterUser = new ListAdapterUser(getBaseContext(),users);
        usersList.setAdapter(listAdapterUser);


        usersList.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if(firstVisibleItem==0){
                    floatbuttons.setVisibility(View.VISIBLE);
                }else{
                    floatbuttons.setVisibility(View.GONE);
                }

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.log_out:
                LoginManager.getInstance().logOut();
                loginPresenter.LoginFalse();
                Intent i  = new Intent (MainActivity.this, Login.class);
                startActivity(i);
                finish();
                break;
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
       // finish();
    }

    public void dialogNewProduct(){
        AlertDialog.Builder dialog_new_product = new AlertDialog.Builder(this);

        LayoutInflater inflater ;
        inflater = (LayoutInflater)getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view_new_product = inflater.inflate(R.layout.dialog_new_product, null);
        dialog_new_product.setView(view_new_product);
        //Actions View
        dialog_new_product.show();
    }




}
