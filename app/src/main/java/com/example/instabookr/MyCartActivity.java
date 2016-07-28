package com.example.instabookr;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ListView;

import com.example.instabookr.models.Cart;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mukesh on 6/7/16.
 */
public class MyCartActivity extends AppCompatActivity {
    ListView cart_list;
    public List<Cart> cartArrayList = new ArrayList<Cart>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }


        cart_list = (ListView) findViewById(R.id.list_view);

        Cart tempCartObj = new Cart();
        tempCartObj.setShop_name("Raju spa");
        tempCartObj.setService_name("full body spa");
        tempCartObj.setService_uuid("123123123123123123123");
        tempCartObj.setTime_slots("12:00-13:00");
        tempCartObj.setCost(1000);
        cartArrayList.add(tempCartObj);

        tempCartObj = new Cart();
        tempCartObj.setShop_name("Shyamu Sports");
        tempCartObj.setService_name("Cricket");
        tempCartObj.setService_uuid("1231231sdcmm23123123123");
        tempCartObj.setTime_slots("15:00-16:00");
        tempCartObj.setCost(200);
        cartArrayList.add(tempCartObj);

        cart_list.setAdapter(new CartAdapter(getApplicationContext(),cartArrayList));

    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

}
