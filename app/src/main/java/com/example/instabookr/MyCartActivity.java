package com.example.instabookr;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.instabookr.models.Cart;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mukesh on 6/7/16.
 */
public class MyCartActivity extends AppCompatActivity {
    ListView cart_list;
    public List<Cart> cartArrayList = new ArrayList<Cart>();
    LinearLayout relativeTotal;
    TextView txtTotal;
    Button btnGoToPaymentGateway;
    int cart_total=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mycart);

        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }


        cart_list = (ListView) findViewById(R.id.cart_list);
        relativeTotal = (LinearLayout) findViewById(R.id.relativeTotal);
        txtTotal = (TextView) findViewById(R.id.txtTotal);
        btnGoToPaymentGateway = (Button) findViewById(R.id.btnGoToPaymentGateway);
        cartArrayList = Common.cartArrayList;

        if (!cartArrayList.isEmpty()){
            cart_list.setAdapter(new CartAdapter(MyCartActivity.this,cartArrayList));
            // txtTotal set it to sum of all cost
            for (int i = 0; i<cartArrayList.size();i++) {
                // offercost is not available
                if (cartArrayList.get(i).getOffer_cost() == 0) {
                    cart_total += cartArrayList.get(i).getCost();
                }else{
                    // offer cost is available
                    cart_total += cartArrayList.get(i).getOffer_cost();
                }
            }
            txtTotal.setText(String.valueOf(cart_total));
        }else{
            cart_list.setVisibility(View.GONE);
            relativeTotal.setVisibility(View.GONE);
        }

        btnGoToPaymentGateway.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MyCartActivity.this,"Opening Payment Gateway...",Toast.LENGTH_SHORT).show();
            }
        });

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
