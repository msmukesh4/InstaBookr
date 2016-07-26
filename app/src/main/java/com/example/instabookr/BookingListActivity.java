package com.example.instabookr;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.example.instabookr.models.Booking;
import com.example.instabookr.models.Service;
import com.example.instabookr.models.Shop;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by mukesh on 12/7/16.
 */
public class BookingListActivity extends AppCompatActivity {

    ListView booking_list;
    List<Booking> bookingArrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        booking_list = (ListView) findViewById(R.id.list_view);
        bookingArrayList = new ArrayList<>();

        Booking booking = new Booking();
        booking.setUuid("1sdasdasdqzxczxczxcq");
        booking.setBooking_token("bummybookingtoken");
        booking.setCost(5000);
        booking.setStart_date(new Date(2016, 8, 01));
        booking.setTime_slots("14:00-15:00");
        Shop b_shop = booking.getShop();
        b_shop.setShop_name("Raja lal sports");
        b_shop.setLocation_id(12);
        b_shop.setAddress("Lajpat Nagar, Boriwalli");
        b_shop.setContact_number1("1234567890");
        b_shop.setLatitude(22.345678);
        b_shop.setLongitude(88.876543);
        Service b_service = booking.getService();
        b_service.setService_name("Cricket");
        b_service.setService_type_id(12);

        bookingArrayList.add(booking);

        booking = new Booking();
        booking.setUuid("2sdasdasdqzxczxczxcq");
        booking.setBooking_token("bummybookingtoken");
        booking.setCost(5000);
        booking.setStart_date(new Date(2016, 8, 01));
        booking.setTime_slots("14:00-15:00");
        b_shop = booking.getShop();
        b_shop.setShop_name("Raja lal sports");
        b_shop.setLocation_id(12);
        b_shop.setAddress("Lajpat Nagar, Boriwalli");
        b_shop.setContact_number1("1234567890");
        b_shop.setLatitude(22.345678);
        b_shop.setLongitude(88.876543);
        b_service = booking.getService();
        b_service.setService_name("Cricket");
        b_service.setService_type_id(12);

        bookingArrayList.add(booking);

        booking = new Booking();
        booking.setUuid("3sdasdasdqzxczxczxcq");
        booking.setBooking_token("bummybookingtoken");
        booking.setCost(5000);
        booking.setStart_date(new Date(2016, 8, 01));
        booking.setTime_slots("14:00-15:00");
        b_shop = booking.getShop();
        b_shop.setShop_name("Raja lal sports");
        b_shop.setLocation_id(12);
        b_shop.setAddress("Lajpat Nagar, Boriwalli");
        b_shop.setContact_number1("1234567890");
        b_shop.setLatitude(22.345678);
        b_shop.setLongitude(88.876543);
        b_service = booking.getService();
        b_service.setService_name("Cricket");
        b_service.setService_type_id(12);
        bookingArrayList.add(booking);

        booking_list.setAdapter(new BookingAdapter(getApplicationContext(),bookingArrayList));


    }
}
