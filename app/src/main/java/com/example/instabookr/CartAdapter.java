package com.example.instabookr;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.instabookr.models.Cart;

import java.util.List;
import java.util.logging.Logger;

/**
 * Created by mukesh on 6/7/16.
 */
public class CartAdapter extends BaseAdapter {

    Context context;
    LayoutInflater inflater;
    List<Cart> cart_list;

    public CartAdapter(Context context, List<Cart> cart_list) {
        this.context = context;
        this.cart_list = cart_list;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return cart_list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        myCartHolder holder;
        if (convertView == null){
            convertView = inflater.inflate(R.layout.cart_item,null,false);
            holder = new myCartHolder(convertView);
            convertView.setTag(holder);
        }else{
            holder = (myCartHolder) convertView.getTag();
        }
        try {
            Cart objCart = cart_list.get(position);
            Log.d("main",position+" Service name : "+objCart.getService_name()+"\n Time slot : "+objCart.getTime_slots()+
                    "\n Cost : "+objCart.getCost());
            holder.tv_service_name.setText(objCart.getService_name().toString());
            holder.tv_time_slot.setText(objCart.getTime_slots().toString());
            holder.tv_service_cost.setText(Integer.toString(objCart.getCost()));

            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // take user to the details page servive by service uuid
                    Toast.makeText(context, "clicked on " + position, Toast.LENGTH_SHORT).show();
                }
            });

        }catch (Exception ee){
            Toast.makeText(context,"Error",Toast.LENGTH_SHORT).show();
            ee.printStackTrace();
        }
        return convertView;
    }
}
class myCartHolder {
    TextView tv_service_name,tv_service_cost,tv_time_slot;
    myCartHolder(View view){
        tv_service_name = (TextView) view.findViewById(R.id.tv_service_name);
        tv_time_slot = (TextView) view.findViewById(R.id.tv_time_slot_val);
        tv_service_cost = (TextView) view.findViewById(R.id.tv_service_cost);
    }
}
