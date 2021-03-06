package com.example.nrike.housemate.View;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.nrike.housemate.Model.Entity.User;
import com.example.nrike.housemate.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by nrike on 2/08/16.
 */
public class ListAdapterUser extends BaseAdapter {

    Context context;
    LayoutInflater inflater;
    List<User> users;

    public ListAdapterUser(Context context, List<User> users) {
        this.context = context;
        this.users = users;
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return users.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    User user;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = inflater.inflate(R.layout.row_user,null);

        user = users.get(position);
        ImageView img = (ImageView) v.findViewById(R.id.imguser);
        TextView txtname = (TextView) v.findViewById(R.id.lbusername);
        TextView txtcantidad= (TextView) v.findViewById(R.id.lbtotaluser);
        RelativeLayout relativeLayout = (RelativeLayout) v.findViewById(R.id.backgroundUserRow);

        SharedPreferences preferences = context.getSharedPreferences("PREFS",Context.MODE_PRIVATE);
        relativeLayout.setBackgroundColor(context.getResources().getColor(preferences.getInt("colorPrimary",R.color.colorPrimary)));

       // img.setBackground(user.getImage_user());

        Picasso.with(context).load(user.getImage_user()).into(img);

        txtname.setText(user.getName());
        txtcantidad.setText(String.valueOf(user.getBuy_products()));

        return v;




    }
}
