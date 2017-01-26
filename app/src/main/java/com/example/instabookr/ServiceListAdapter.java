package com.example.instabookr;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.instabookr.models.Booking;
import com.example.instabookr.models.Service;

import java.util.List;

/**
 * Created by mukesh on 28/7/16.
 */
public class ServiceListAdapter extends BaseAdapter {

    Context context;
    LayoutInflater inflater;
    List<Service> service_list;

    public ServiceListAdapter(ServiceListActivity context, List<Service> service_list) {
        this.context = context;
        this.service_list = service_list;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
       return service_list.size();
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
        myServiceListHolder holder;
        if (convertView == null){
            convertView = inflater.inflate(R.layout.service_item,null,false);
            holder = new myServiceListHolder(convertView);
            convertView.setTag(holder);
        }else{
            holder = (myServiceListHolder) convertView.getTag();
        }
        try {
            Service service = service_list.get(position);
            System.out.println("Discounted cost : " + service.getDiscounted_cost());
            if (service.getIcon() == "demo") {
                holder.img_service_icon.setImageResource(R.mipmap.ic_launcher);
            }else{
                // download the image and add ot the image view with picasso
                Toast.makeText(context,"Downloading Images please wait",Toast.LENGTH_SHORT).show();
            }
            holder.tv_shopname.setText(service.getShop_uuid().toString());
            holder.tv_shop_address.setText(service.getService_name());
            if (service.getDiscounted_cost() != 0){
                holder.tv_service_actual_cost.setText(String.valueOf(service.getCost()));
                holder.tv_service_actual_cost.setTextColor(context.getResources().getColor(R.color.text_gray));
                holder.tv_service_actual_cost.setPaintFlags(holder.tv_service_actual_cost.getPaintFlags()
                        | Paint.STRIKE_THRU_TEXT_FLAG);
                holder.tv_discount.setText(String.valueOf(service.getDiscounted_cost()));
            }else{
                holder.tv_service_actual_cost.setVisibility(View.GONE);
                holder.tv_discount.setText(String.valueOf(service.getCost()));
            }

            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // take user to the details page servive by service uuid
                    Toast.makeText(context, "clicked on " + position, Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(context,TimeSelectorActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                    intent.putExtra("service_uuid",service_list.get(position).getUuid().toString());
                    context.startActivity(intent);
                }
            });

        }catch (Exception ee){
            Toast.makeText(context,"Error",Toast.LENGTH_SHORT).show();
            ee.printStackTrace();
        }
        return convertView;
    }

    private class myServiceListHolder {
        ImageView img_service_icon;
        TextView tv_shopname,tv_shop_address,tv_service_actual_cost,tv_discount;
        public myServiceListHolder(View view) {
            img_service_icon = (ImageView) view.findViewById(R.id.img_service_icon);
            tv_shopname = (TextView) view.findViewById(R.id.tv_shopname);
            tv_shop_address = (TextView) view.findViewById(R.id.tv_shop_address);
            tv_service_actual_cost = (TextView) view.findViewById(R.id.tv_service_actual_cost);
            tv_discount = (TextView) view.findViewById(R.id.tv_discount);
        }

    }
}
