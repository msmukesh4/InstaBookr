package com.example.instabookr;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

/**
 * Created by mukesh on 13/7/16.
 */
public class BookingDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_details);
        String u = getIntent().getStringExtra("booking_uuid");
        Log.d ("Lee","u : "+u);
    }
}
