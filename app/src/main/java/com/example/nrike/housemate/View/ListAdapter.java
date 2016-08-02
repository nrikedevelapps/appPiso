package com.example.nrike.housemate.View;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nrike.housemate.Model.entity.Product;
import com.example.nrike.housemate.R;

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

        TextView txtproduct = (TextView) v.findViewById(R.id.lbproducto);
        TextView txtquantity = (TextView) v.findViewById(R.id.lbcantidad);
       // TextView txtbuy = (TextView) v.findViewById(R.id.lbcomprar);
        ImageView img = (ImageView) v.findViewById(R.id.imgProducto);

        txtproduct.setText(product.getName());
        txtquantity.setText(product.getQuantity());
        img.setImageResource(product.getImagen());

        return v;
    }
}
