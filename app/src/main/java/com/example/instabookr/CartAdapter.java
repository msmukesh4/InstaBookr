package com.example.instabookr;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Paint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
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
            convertView = inflater.inflate(R.layout.list_cart_item,null,false);
            holder = new myCartHolder(convertView);
            convertView.setTag(holder);
        }else{
            holder = (myCartHolder) convertView.getTag();
        }
        try {
            Cart objCart = cart_list.get(position);
            Log.d("main", position + " Service name : " + objCart.getService_name() + "\n Time slot : " + objCart.getTime_slots() +
                    "\n Cost : " + objCart.getCost());
            holder.tv_service_name.setText(objCart.getService_name().toString());
            holder.tv_time_slot.setText(objCart.getTime_slots().toString());
            holder.tv_service_cost.setText(Integer.toString(objCart.getCost()));
            if (objCart.getOffer_cost() != 0){
                holder.tv_service_offer_cost.setVisibility(View.VISIBLE);
                holder.tv_service_cost.setTextColor(context.getResources().getColor(R.color.text_gray));
                holder.tv_service_cost.setPaintFlags(holder.tv_service_cost.getPaintFlags()
                        | Paint.STRIKE_THRU_TEXT_FLAG);
                holder.tv_service_offer_cost.setText(String.valueOf(objCart.getOffer_cost()));
            }else{
                holder.tv_service_offer_cost.setVisibility(View.GONE);
            }
            if (objCart.getPeriod() > 1) {
                holder.lblBookingPeriod.setVisibility(View.VISIBLE);
                holder.tv_period.setVisibility(View.VISIBLE);
                String period_text;
                int period_in_days = objCart.getPeriod();
                int period_in_month,period_in_year;
                if (period_in_days >= 30 && period_in_days < 364) {
                    period_in_month = period_in_days / 30;
                    period_text = period_in_month + " month";
                } else{
                    period_in_year = period_in_days / 365;
                    period_text = period_in_year + " year";
                }
                holder.tv_period.setText(period_text);
            }else {
                holder.lblBookingPeriod.setVisibility(View.GONE);
                holder.tv_period.setVisibility(View.GONE);
            }
            holder.btn_remove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    System.out.println("Remove button clicked.");
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);

                    builder.setMessage("Do you want to remove this item from your cart? ").setCancelable(false)
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    try {
                                        // Remove(position);
                                        // Remove the item from list
                                        // Send a post request to the server to remove position
                                        // re fetch the cart list
                                        // for demo we are deleting the item
                                        cart_list.remove(position);
                                    } catch (Exception e) {
                                        // TODO: handle exception
                                    }
                                }
                            }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });
                    AlertDialog alert = builder.create();
                    alert.show();
                }
            });



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
    TextView tv_service_name,tv_service_cost,tv_time_slot,tv_service_offer_cost,tv_period,lblBookingPeriod;
    Button btn_remove;
    myCartHolder(View view){
        tv_service_name = (TextView) view.findViewById(R.id.txtServiceName);
        tv_time_slot = (TextView) view.findViewById(R.id.txtTimeslotVal);
        tv_service_offer_cost = (TextView) view.findViewById(R.id.txtOfferPrice);
        tv_service_cost = (TextView) view.findViewById(R.id.txtPrice);
        tv_period = (TextView) view.findViewById(R.id.txtPeriod);
        btn_remove = (Button) view.findViewById(R.id.btnRemove);
        lblBookingPeriod = (TextView) view.findViewById(R.id.lblBookingPeriod);
//        btn_remove = view
    }
}
