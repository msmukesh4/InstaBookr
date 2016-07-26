package com.example.instabookr;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.instabookr.models.User;

public class OtpActivity extends AppCompatActivity {

    Button btn_submit_otp,btn_resend_otp;
    boolean correct_otp = false;
    boolean userDataSaved = false;
    EditText et_otp;
    int TIME_AFTER_RESEND_APPEAR = 20000;
    String errorMsg = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);

        et_otp = (EditText) findViewById(R.id.et_otp);
        // Saving the data with user registered false

//        UserTable userObj = new UserTable();
//        userObj.setUser_id(1);
//        userObj.setFirstname(Common.firstname);
//        userObj.setLastname(Common.lastname);
//        userObj.setMobile_number(Common.mobile_number);
//        userObj.setEmail(Common.email);
//        userObj.isRegistered(Common.registered);

//        DBHelper helperObject =  DBHelper.getInstance(this);
//        userDataSaved = helperObject.Saveuser(userObj);

        btn_resend_otp = (Button) findViewById(R.id.btn_resend_otp);
        btn_resend_otp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplication(),"Resending otp",Toast.LENGTH_LONG).show();
            }
        });
        btn_resend_otp.setVisibility(View.INVISIBLE);

        CountDownTimer cdt = new CountDownTimer(TIME_AFTER_RESEND_APPEAR, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onFinish() {
                btn_resend_otp.setVisibility(View.VISIBLE);
            }
        };
        cdt.start();



        btn_submit_otp = (Button) findViewById(R.id.btn_otp);
        btn_submit_otp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_submit_otp.setClickable(false);
                correct_otp = validateOtp();
                if(correct_otp) {
                    Common.registered = true;

                    // Saving the data with user registered as true
                    User userObj = new User();
                    userObj.setId(1);
                    userObj.setFirstname(Common.firstname);
                    userObj.setLastname(Common.lastname);
                    userObj.setPhone_number(Common.mobile_number);
                    userObj.setEmail(Common.email);

//                    DBHelper helperObject =  DBHelper.getInstance(OtpActivity.this);
//                    userDataSaved = helperObject.Saveuser(userObj);

                    // Ad Activity on succesful Registration
                    Intent intent_main = new Intent(OtpActivity.this,MainActivity.class);
                    intent_main.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent_main);
                    finish();

                }else{
                    btn_submit_otp.setClickable(true);
                    Toast.makeText(OtpActivity.this, errorMsg, Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        btn_submit_otp.setClickable(true);
    }

    // validate that correct otp is send or not
    public boolean validateOtp(){
        if(et_otp.getText().toString()==""){
            errorMsg = "Please enter OTP";
            return false;
        }else if (et_otp.getText().toString().length()<4){
            errorMsg = "OTP should consists of 4 digits";
            return  false;
        }else {
            return true;
        }
    }
}
