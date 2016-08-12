package com.example.nrike.housemate.View;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.nrike.housemate.Model.Entity.Product;
import com.example.nrike.housemate.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by nrike on 1/08/16.
 */
public class ListAdapter extends BaseAdapter {

    List <Product> products;
    Context context;
    LayoutInflater inflater;

    public ListAdapter(Context context, List<Product> products) {
        this.context = context;
        this.products = products;
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return products.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    Product product;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        product = products.get(position);
        View v = inflater.inflate(R.layout.row,null);

        final RelativeLayout rlIconBuy = (RelativeLayout) v.findViewById(R.id.relativeLayout_buy_icon);
        TextView txtproduct = (TextView) v.findViewById(R.id.lbproducto);
        TextView txtquantity = (TextView) v.findViewById(R.id.lbcantidad);
       // TextView txtbuy = (TextView) v.findViewById(R.id.lbcomprar);
        ImageView img = (ImageView) v.findViewById(R.id.imgProducto);

        CardView cardView = (CardView) v.findViewById(R.id.card_view);

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rlIconBuy.setVisibility(View.VISIBLE);
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        rlIconBuy.setVisibility(View.GONE);
                        //Show dialog buy
                        dialogBuyProduct(product);

                    }
                },400);
            }

        });

        txtproduct.setText(product.getName());
        txtquantity.setText(product.getQuantity());
        Picasso.with(context).load(product.getImagen()).into(img);

        return v;
    }

    public void dialogBuyProduct(Product product){
        final AlertDialog.Builder dialog_new_product = new AlertDialog.Builder(context);
        dialog_new_product.setTitle("Comprado");

        dialog_new_product.setMessage("dfkjsldfjs");
        dialog_new_product.setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        dialog_new_product.show();
    }


}
