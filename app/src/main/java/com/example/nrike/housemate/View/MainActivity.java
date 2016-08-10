package com.example.nrike.housemate.View;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AbsListView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nrike.housemate.Model.Entity.Product;
import com.example.nrike.housemate.Model.Entity.User;
import com.example.nrike.housemate.Presenter.MainActivity.MainActivityPresenter;
import com.example.nrike.housemate.R;
import com.facebook.login.LoginManager;

import org.lucasr.twowayview.TwoWayView;

import java.util.List;

import at.markushi.ui.CircleButton;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements MainActivityView {



    MainActivityPresenter mainActivityPresenter;

    ListAdapter listAdapter ;
    ListAdapterUser listAdapterUser;

    List<Product> listProducts;
    List<User> listUsers;

    ActionBar actionBar;

    @BindView(R.id.separator)
    LinearLayout separator;

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

    String product;
    String quantity;

    /*
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
    */
/*
    public void newUsers(){
        users = new ArrayList<>();
        users.add(new User("PEPE",13,"nanai"));
        users.add(new User("PEPE",13,"nanai"));
        users.add(new User("PEPE",13,"nanai"));
        users.add(new User("PEPE",13,"nanai"));

    }
*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        horizontalList.setVisibility(View.GONE);
        btmore.setVisibility(View.GONE);
        actionBar =getSupportActionBar();

        mainActivityPresenter = new MainActivityPresenter(this,this);

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

        changeColors();

        mainActivityPresenter.updateListUsers();
        mainActivityPresenter.updateListProducts();

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
                mainActivityPresenter.logOut();
                Intent i  = new Intent (MainActivity.this, Login.class);
                startActivity(i);
                finish();
                break;
            case R.id.color:
                dialogColor();
                break;

        }
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
       // finish();
    }

    int ACTIVITY_SELECT_IMAGE = 1020;
    public void dialogNewProduct(){
        AlertDialog.Builder dialog_new_product = new AlertDialog.Builder(this);

        LayoutInflater inflater ;
        inflater = (LayoutInflater)getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view_new_product = inflater.inflate(R.layout.dialog_new_product, null);
        dialog_new_product.setView(view_new_product);
        //Actions View
        FloatingActionButton btTakePhoto = (FloatingActionButton) view_new_product.findViewById(R.id.btTakePhoto);
        final TextView txtproduct = (TextView) view_new_product.findViewById(R.id.txtnuevoproducto);
        final TextView txtquantity = (TextView) view_new_product.findViewById(R.id.txtnuevacantidad);

        btTakePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (txtproduct.getText().toString().equals("")||txtquantity.getText().toString().equals("")){
                    Toast.makeText(MainActivity.this, "Completa los datos", Toast.LENGTH_SHORT).show();
                }else{
                    Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
                    product = txtproduct.getText().toString();
                    quantity = txtquantity.getText().toString();
                    galleryIntent.setType("image/*");
                    startActivityForResult(galleryIntent, ACTIVITY_SELECT_IMAGE);
                }
            }
        });


        dialog_new_product.show();
    }

    public void dialogColor(){
        final AlertDialog.Builder dialog_new_product = new AlertDialog.Builder(this);

        LayoutInflater inflater ;
        inflater = (LayoutInflater)getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view_color = inflater.inflate(R.layout.dialog_color, null);
        dialog_new_product.setView(view_color);
        //Actions View

        CircleButton btred = (CircleButton) view_color.findViewById(R.id.color_red);
        CircleButton btviolet = (CircleButton) view_color.findViewById(R.id.color_violet);
        CircleButton btyellow = (CircleButton) view_color.findViewById(R.id.color_yellow);
        CircleButton btblue = (CircleButton) view_color.findViewById(R.id.color_blue);

        SharedPreferences preferences = getSharedPreferences("PREFS",MODE_PRIVATE);
        final SharedPreferences.Editor editor = preferences.edit();

        btred.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putInt("colorPrimary",R.color.colorPrimary_red);
                editor.putInt("colorPrimaryDark",R.color.colorPrimaryDark_red);
                editor.putInt("colorAccent",R.color.colorAccent_red);
                editor.putInt("colorPrimarySemiTransparent",R.color.colorPrimarySemiTransparent_red);
                editor.commit();
                changeColors();
                listAdapterUser.notifyDataSetChanged();

            }
        });

        btviolet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putInt("colorPrimary",R.color.colorPrimary_violet);
                editor.putInt("colorPrimaryDark",R.color.colorPrimaryDark_violet);
                editor.putInt("colorAccent",R.color.colorAccent_violet);
                editor.putInt("colorPrimarySemiTransparent",R.color.colorPrimarySemiTransparent_violet);
                editor.commit();
                changeColors();
                listAdapterUser.notifyDataSetChanged();

            }
        });

        btyellow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putInt("colorPrimary",R.color.colorPrimary_yellow);
                editor.putInt("colorPrimaryDark",R.color.colorPrimaryDark_yellow);
                editor.putInt("colorAccent",R.color.colorAccent_yellow);
                editor.putInt("colorPrimarySemiTransparent",R.color.colorPrimarySemiTransparent_yellow);
                editor.commit();
                changeColors();
                listAdapterUser.notifyDataSetChanged();

            }
        });

        btblue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putInt("colorPrimary",R.color.colorPrimary);
                editor.putInt("colorPrimaryDark",R.color.colorPrimaryDark);
                editor.putInt("colorAccent",R.color.colorAccent);
                editor.putInt("colorPrimarySemiTransparent",R.color.colorPrimarySemiTransparent);
                editor.commit();
                changeColors();
                listAdapterUser.notifyDataSetChanged();

            }
        });



        dialog_new_product.show();
    }

    public void changeColors(){
        SharedPreferences preferences = getSharedPreferences("PREFS",MODE_PRIVATE);

        int colorPrimary=preferences.getInt("colorPrimary",R.color.colorPrimary);
        int colorPrimaryDark=preferences.getInt("colorPrimaryDark",R.color.colorPrimaryDark);
        int colorSemiTransparent = preferences.getInt("colorPrimarySemiTransparent",R.color.colorPrimarySemiTransparent);

        ColorDrawable colorDrawable = new ColorDrawable(getResources().getColor(colorPrimary));
        actionBar.setBackgroundDrawable(colorDrawable);
        separator.setBackgroundColor(getResources().getColor(colorPrimary));

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().setStatusBarColor(ContextCompat.getColor(this, colorPrimaryDark));
        }
        horizontalList.setBackgroundColor(getResources().getColor(colorSemiTransparent));;
        btbuy.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(colorPrimary)));
        btmore.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(colorPrimary)));

    }



    @Override
    public void loadUser(List<User> users) {
         this.listUsers = users;
        //Log.i("USERS_NUM",">>"+users.size());
        listAdapterUser = new ListAdapterUser(getBaseContext(),users);
        usersList.setAdapter(listAdapterUser);
        listAdapterUser.notifyDataSetChanged();
    }

    @Override
    public void updateUsers(List<User> users) {
        this.listUsers = users;
        listAdapterUser.notifyDataSetChanged();
    }

    @Override
    public void loadProduct(List<Product> products) {
        this.listProducts=products;
        listAdapter = new ListAdapter(getBaseContext(),products);
        productsList.setAdapter(listAdapter);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == ACTIVITY_SELECT_IMAGE && resultCode == RESULT_OK) {
            Toast.makeText(MainActivity.this, "Subiendo...", Toast.LENGTH_SHORT).show();
            Uri uri = data.getData();
            mainActivityPresenter.addProduct(product,quantity,uri);

        }
    }


}
