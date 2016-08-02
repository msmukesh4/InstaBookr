package com.example.instabookr;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by mukesh on 29/7/16.
 */
public class TimeSelectorActivity extends AppCompatActivity {

    ImageView iv_back, iv_contact, iv_info;
    LinearLayout ll_get_directions;
    TextView tv_shop_name, tv_shop_address, tv_service_name, tv_booking_date, tv_service_actual_cost, tv_discounted_cost;
    Button btn_add_to_cart;
    Spinner spinner_time_slot, spinner_period_selector, spinner_period_time_selector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_selector);

        // hiding ActionBar
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        iv_back = (ImageView) findViewById(R.id.iv_back);
        iv_contact = (ImageView) findViewById(R.id.iv_contact);
        iv_info = (ImageView) findViewById(R.id.iv_info);
        ll_get_directions = (LinearLayout) findViewById(R.id.ll_get_directions);
        tv_shop_name = (TextView) findViewById(R.id.tv_shop_name);
        tv_shop_address = (TextView) findViewById(R.id.tv_shop_address);
        tv_service_name = (TextView) findViewById(R.id.tv_service_name);
        tv_booking_date = (TextView) findViewById(R.id.tv_booking_date);
        tv_service_actual_cost = (TextView) findViewById(R.id.tv_service_actual_cost);
        tv_discounted_cost = (TextView) findViewById(R.id.tv_discounted_cost);
        btn_add_to_cart = (Button) findViewById(R.id.btn_add_to_cart);
        spinner_time_slot = (Spinner) findViewById(R.id.spinner_time_slot);
        spinner_period_selector = (Spinner) findViewById(R.id.spinner_period_selector);
        spinner_period_time_selector = (Spinner) findViewById(R.id.spinner_period_time_selector);

        // banner on touch activities
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // add PhoneStateListener
        PhoneCallListener phoneListener = new PhoneCallListener();
        TelephonyManager telephonyManager = (TelephonyManager) this
                .getSystemService(Context.TELEPHONY_SERVICE);
        telephonyManager.listen(phoneListener, PhoneStateListener.LISTEN_CALL_STATE);

        iv_contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // create a calling intent
                Toast.makeText(getApplicationContext(), "calling...", Toast.LENGTH_SHORT).show();
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:9007229769"));
                if (ActivityCompat.checkSelfPermission(TimeSelectorActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    startActivity(callIntent);
                }
                startActivity(callIntent);
            }
        });

        iv_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // start shop details activity
                Toast.makeText(getApplicationContext(), "showing shop info...", Toast.LENGTH_SHORT).show();
            }
        });

        ll_get_directions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // show shop on google map
                Toast.makeText(getApplicationContext(), "showing shop on map...", Toast.LENGTH_SHORT).show();
            }
        });

        // Populate timeslot spinner
        String[] timeslots = getResources().getStringArray(R.array.time_slots);
        spinner_time_slot.setAdapter(new ArrayAdapter<String>(TimeSelectorActivity.this, android.R.layout.simple_list_item_1, timeslots));

        spinner_period_selector.setAdapter(new ArrayAdapter<String>(TimeSelectorActivity.this,android.R.layout.simple_list_item_1,
                getResources().getStringArray(R.array.periods)));

        final ArrayList<String> time_for_period = new ArrayList<String>();
        final ArrayList<String> time_for_day = new ArrayList<String>();
        for (int i = 1; i<=30; i++)
            time_for_day.add(String.valueOf(i));

        final ArrayList<String> time_for_month = new ArrayList<String>();
        for (int i = 1; i<=24; i++)
            time_for_month.add(String.valueOf(i));
        final ArrayList<String> time_for_year = new ArrayList<String>();
        for (int i = 1; i<=5; i++)
            time_for_year.add(String.valueOf(i));

        spinner_period_selector.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                String item = spinner_period_selector.getSelectedItem().toString();
                Log.i("Selected item : ", item);
                if (spinner_period_selector.getSelectedItemPosition() == 0){
                    spinner_period_time_selector.setAdapter(new ArrayAdapter<String>(TimeSelectorActivity.this,android.R.layout.simple_list_item_1,
                            time_for_day));
                }else if (spinner_period_selector.getSelectedItemPosition() == 1){
                    spinner_period_time_selector.setAdapter(new ArrayAdapter<String>(TimeSelectorActivity.this,android.R.layout.simple_spinner_item,
                            time_for_month));
                }else{
                    spinner_period_time_selector.setAdapter(new ArrayAdapter<String>(TimeSelectorActivity.this,android.R.layout.simple_list_item_1,
                            time_for_year));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {

            }

        });



        btn_add_to_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Adding Item to cart",Toast.LENGTH_SHORT).show();
                Common.initCart();
                startActivity(new Intent(TimeSelectorActivity.this,MyCartActivity.class));
            }
        });
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


    //monitor phone call activities
    private class PhoneCallListener extends PhoneStateListener {

        private boolean isPhoneCalling = false;

        String LOG_TAG = "LOGGING 123";

        @Override
        public void onCallStateChanged(int state, String incomingNumber) {

            if (TelephonyManager.CALL_STATE_RINGING == state) {
                // phone ringing
                Log.i(LOG_TAG, "RINGING, number: " + incomingNumber);
            }

            if (TelephonyManager.CALL_STATE_OFFHOOK == state) {
                // active
                Log.i(LOG_TAG, "OFFHOOK");

                isPhoneCalling = true;
            }

            if (TelephonyManager.CALL_STATE_IDLE == state) {
                // run when class initial and phone call ended,
                // need detect flag from CALL_STATE_OFFHOOK
                Log.i(LOG_TAG, "IDLE");

                if (isPhoneCalling) {

                    Log.i(LOG_TAG, "restart app");

                    // restart app
                    Intent i = getBaseContext().getPackageManager()
                            .getLaunchIntentForPackage(
                                    getBaseContext().getPackageName());
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(i);

                    isPhoneCalling = false;
                }

            }
        }
    }


}
