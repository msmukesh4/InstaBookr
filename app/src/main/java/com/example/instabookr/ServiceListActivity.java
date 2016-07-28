package com.example.instabookr;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.instabookr.models.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import io.apptik.widget.MultiSlider;

/**
 * Created by mukesh on 27/7/16.
 */
public class ServiceListActivity extends AppCompatActivity {

    MultiSlider multiSlider5;
    TextView tv_start_time,tv_end_time;
    ListView service_listView;
    List<Service> service_list;
    int date;
    int day;
    Calendar calendar;
    TextView tv_date_1_1,tv_date_1_2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_list);

        // for back button pressing
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        multiSlider5 = (MultiSlider)findViewById(R.id.range_slider5);
        tv_start_time = (TextView) findViewById(R.id.tv_start_time);
        tv_end_time = (TextView) findViewById(R.id.tv_end_time);
        service_listView = (ListView) findViewById(R.id.service_listView);
        service_list = new ArrayList<Service>();
        calendar = Calendar.getInstance();
        tv_date_1_1 = (TextView) findViewById(R.id.tv_date_1_1);
        tv_date_1_2 = (TextView) findViewById(R.id.tv_date_1_2);

        String service_name = getIntent().getStringExtra("service_name");
        String service_uuid = getIntent().getStringExtra("service_uuid");
        Toast.makeText(getApplicationContext(),"service_name : "+service_name+" service_uuid : "+service_uuid,
                Toast.LENGTH_SHORT).show();

        date = calendar.get(Calendar.DATE);
        day = calendar.get(Calendar.DAY_OF_WEEK);
        tv_date_1_1.setText(String.valueOf(getDayName(day)));
        tv_date_1_2.setText(String.valueOf(date));



        Service s = new Service("schp_uuid",service_uuid,1,"Full body spa",400,"demo");
        service_list.add(s);

        s = new Service("schp_uuid",service_uuid,1,"Foot message",1000,"demo",300);
        service_list.add(s);

        multiSlider5.setOnThumbValueChangeListener(new MultiSlider.OnThumbValueChangeListener() {
            @Override
            public void onValueChanged(MultiSlider multiSlider, MultiSlider.Thumb thumb, int thumbIndex, int value) {
                if (thumbIndex == 0) {
                    tv_start_time.setText(String.valueOf(value));
//                    Toast.makeText(getApplicationContext(),String.valueOf(value)+"",Toast.LENGTH_SHORT).show();

                } else {
                    tv_end_time.setText(String.valueOf(value));
//                    Toast.makeText(getApplicationContext(),String.valueOf(value)+"",Toast.LENGTH_SHORT).show();
                }
            }
        });

        service_listView.setAdapter(new ServiceListAdapter(ServiceListActivity.this,service_list));

    }

    public String getDayName(int day){
        switch(day){
            case 1:
                return "SUN";
            case 2:
                return "MON";
            case 3:
                return "TUE";
            case 4:
                return "WED";
            case 5:
                return "THUR";
            case 6:
                return  "FRI";
            case 7:
                return "SAT";
        }

        return "Worng Day";
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
