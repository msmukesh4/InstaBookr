package com.example.instabookr;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.amulyakhare.textdrawable.TextDrawable;
import com.example.instabookr.models.User;

/**
 * Created by mukesh on 7/7/16.
 */
public class UserProfileActivity extends AppCompatActivity {

    ImageView image;
    String user_image_string;
    TextView tv_name,tv_email,tv_phone_number,tv_edit_profile,tv_bookings,tv_change_password,tv_user_loyality_discounts,tv_contact_us;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        image = (ImageView) findViewById(R.id.user_image);
        tv_name = (TextView) findViewById(R.id.tv_name);
        tv_email = (TextView) findViewById(R.id.tv_email);
        tv_phone_number = (TextView) findViewById(R.id.tv_phone_number);
        tv_edit_profile = (TextView) findViewById(R.id.tv_edit_profile);
        tv_bookings = (TextView) findViewById(R.id.tv_bookings);
        tv_change_password = (TextView) findViewById(R.id.tv_change_password);
        tv_user_loyality_discounts = (TextView) findViewById(R.id.tv_user_loyality_discounts);
        tv_contact_us = (TextView) findViewById(R.id.tv_contact_us);

//        Demo user data
        User testUser = new User();
        testUser.setFirstname("Mike");
        testUser.setLastname("Stifler");
        testUser.setPhone_number("1234567890");
        testUser.setEmail("mike@stifler.com");
        testUser.setId(10);
        testUser.setAltername_phone_number("0987654321");
        testUser.setLocation("Kolkata Sector-V");
        testUser.setLatitude("22.123456");
        testUser.setLocation("88.987654");
        testUser.setPassword("secret");
        testUser.setUuid("asdasdasdaqweqweqweq");

        Common.global_user = testUser;

        // creating the round image from username
        if (testUser.getFirstname() != null && testUser.getLastname() != null){
            user_image_string = testUser.getFirstname().substring(0,1) +testUser.getLastname().substring(0,1);
            Log.d("Lee", "user string : " + user_image_string);
        }
        TextDrawable drawable = TextDrawable.builder()
                .buildRound(user_image_string, Color.RED);
        image.setImageDrawable(drawable);

        tv_name.setText(testUser.getFirstname() +" "+testUser.getLastname());
        tv_email.setText(testUser.getEmail());
        tv_phone_number.setText(testUser.getPhone_number());

        tv_edit_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // start the edit profile activity
                Toast.makeText(getApplicationContext(), "edit profile", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(UserProfileActivity.this,ProfileEditActivity.class));
            }
        });

        tv_bookings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // show user booking list
                Toast.makeText(getApplicationContext(), "bookings", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(UserProfileActivity.this,BookingListActivity.class));
            }
        });

        tv_change_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // redirect user to change password activity
                Toast.makeText(getApplicationContext(), "change password", Toast.LENGTH_SHORT).show();
            }
        });

        tv_user_loyality_discounts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // show a list of shops where user is a loyal customer and a loyality discount is available
                Toast.makeText(getApplicationContext(), "loyal", Toast.LENGTH_SHORT).show();
            }
        });

        tv_contact_us.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Take User to about Us page
                Toast.makeText(getApplicationContext(), "conatact us", Toast.LENGTH_SHORT).show();
            }
        });


    }
}
