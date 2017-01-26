package com.example.instabookr;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.HttpURLConnection;
import java.util.HashMap;

/**
 * Created by mukesh on 30/7/16.
 */
public class SplashActivity extends AppCompatActivity implements TaskCompleteListener<String>{

    private static final int AUTO_HIDE_DELAY_MILLIS = 6000;
    CountDownTimer cdt;
    private ProgressBar progressBar;
    private String log = "Splash Activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // hide status bar must be called before adding content
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);

        // hiding ActionBar
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        setContentView(R.layout.activity_splash);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.getIndeterminateDrawable().setColorFilter(getResources().getColor(R.color.danger), PorterDuff.Mode.MULTIPLY);

//        try {
//
//            // send an Ajax call to submit user course
//            HashMap<String,String> params = new HashMap<>();
//            params.put("client_id",Common.CLIENT_ID);
//            params.put("client_secret",Common.CLIENT_SECRET);
//
//            if (Common.isInternetAvailable(getApplicationContext())) {
//                // make ajax call and request for login attempt to server
//                NetworkCalls authenticationTask = new NetworkCalls(
//                        SplashActivity.this,null,SplashActivity.this,Common.baseUrl,"GET",params);
//                authenticationTask.execute("App Data");
//
//            }else {
//                Common.showNoNetworkNotification(getApplicationContext());
//            }
//
//        }catch (Exception ee){
//            ee.printStackTrace();
//        }

         cdt = new CountDownTimer(AUTO_HIDE_DELAY_MILLIS, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onFinish() {
                // TODO Auto-generated method stub
                if (Common.IS_USER_LOGGED_IN) {

                } else {
                    activityTransition();
                }
            }
        };
        cdt.start();
    }

    @Override
    public void onTaskComplete(String response, int statusCode, HashMap<String, String> hashMap) {
        try {
            if (statusCode == HttpURLConnection.HTTP_OK && !response.isEmpty() && response != null) {
//                System.out.println("Activity receives response : " + response);
                JSONObject jsonResponse = new JSONObject(response);
                if (jsonResponse.optBoolean("success")){
//                    JSONObject jsonUser = jsonResponse.optJSONObject("data");
//                    Common.global_user.setId(Integer.parseInt(jsonUser.optString("id")));
//                    Common.global_user.setFirstname(jsonUser.optString("first_name"));
//                    Common.global_user.setLastname(jsonUser.optString("last_name"));
//                    Common.global_user.setProfile_pic(jsonUser.optString("img_uri"));
//
//                    Common.global_user.setGender(jsonUser.optString("gender"));
//                    Common.global_user.setPhone_number(jsonUser.optString("phone_number"));
//                    Common.global_user.setStatus(jsonUser.optString("status"));
////                    CbtConstants.currentUser.setSessionToken(jsonUser.optString("authentication_token"));
//                    Common.global_user.setAppUser(jsonUser.optBoolean("is_admin"));
                    activityTransition();
                    progressBar.setVisibility(View.GONE);
                }else {
                    Toast.makeText(SplashActivity.this,jsonResponse.optString("message"),Toast.LENGTH_LONG).show();
                }
            }else if (statusCode == HttpURLConnection.HTTP_UNAUTHORIZED){
                Log.e(log,"Unauthorized User");
                Toast.makeText(SplashActivity.this,"Invalid username or password",Toast.LENGTH_LONG).show();
            }else if(statusCode == 0) {
                Log.e(log,"Socket Timeout Exception");
                showAlert(getString(R.string.request_timeout_header),getString(R.string.request_timeout_content));
            }else {
                System.out.println("Activity receives bad response :" + statusCode + " " + response);
                showAlert(getString(R.string.request_timeout_header),getString(R.string.request_timeout_content));
            }
        }catch (JSONException ee){
            Toast.makeText(SplashActivity.this,R.string.something_went_wrong,Toast.LENGTH_LONG).show();
            ee.printStackTrace();
        }
    }

    private void activityTransition() {
        if (Common.IS_USER_LOGGED_IN) {
//            btn_register.setVisibility(View.VISIBLE);
        } else {
            Intent intent = new Intent(SplashActivity.this, HomePage.class);
            startActivity(intent);
            finish();
        }
    }

    public void showAlert(String title,String msg){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setCancelable(false);
        builder.setNegativeButton("Dismiss", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                finish();
            }
        });
        builder.setMessage(msg);
        builder.show();
    }

//    @Override
//    protected void onStop() {
//        super.onStop();
//        cdt.cancel();
//    }

}
