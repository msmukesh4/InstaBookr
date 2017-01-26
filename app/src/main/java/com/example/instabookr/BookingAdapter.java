package com.example.instabookr;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.instabookr.models.Booking;
import com.example.instabookr.models.Cart;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mukesh on 13/7/16.
 */
public class BookingAdapter extends BaseAdapter {

    Context context;
    LayoutInflater inflater;
    List<Booking> bookingArrayList;
    public BookingAdapter(Context context, List<Booking> bookingArrayList) {
        this.context = context;
        this.bookingArrayList = bookingArrayList;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return bookingArrayList.size();
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
        myBookingHolder holder;
        if (convertView == null){
            convertView = inflater.inflate(R.layout.booking_item,null,false);
            holder = new myBookingHolder(convertView);
            convertView.setTag(holder);
        }else{
            holder = (myBookingHolder) convertView.getTag();
        }
        try {
            Booking objBooking = bookingArrayList.get(position);
            holder.shop_name.setText(objBooking.getShop().getShop_name().toString());
            holder.service_name.setText(objBooking.getService().getService_name().toString());
            holder.cost.setText("5000");
            holder.date_time.setText(objBooking.getTime_slots().toString());

            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // take user to the details page servive by service uuid
                    Toast.makeText(context, "clicked on " + position, Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(context,BookingDetailsActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra("booking_uuid",bookingArrayList.get(position).getUuid().toString());
                    context.startActivity(intent);
                }
            });

        }catch (Exception ee){
//            Toast.makeText(context,"Error",Toast.LENGTH_SHORT).show();
            ee.printStackTrace();
        }
        return convertView;
    }
}

class myBookingHolder{
    TextView shop_name,service_name,date_time,cost;
    myBookingHolder(View view){
        shop_name = (TextView) view.findViewById(R.id.tv_shopname);
        service_name = (TextView) view.findViewById(R.id.tv_service_name);
        date_time = (TextView) view.findViewById(R.id.tv_time_slot_val);
        cost = (TextView) view.findViewById(R.id.tv_service_cost);
    }
}
