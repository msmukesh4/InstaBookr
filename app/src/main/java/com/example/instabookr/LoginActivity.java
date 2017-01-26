package com.example.instabookr;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.HttpURLConnection;
import java.text.SimpleDateFormat;
import java.util.HashMap;


public class LoginActivity extends AppCompatActivity implements TaskCompleteListener<String>{

    Button btn_login,btn_register;
    EditText et_email,et_password;
    String errorMsg = "",log="Login Activity";
    private ProgressView pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btn_login = (Button) findViewById(R.id.btn_login);
        btn_register = (Button) findViewById(R.id.btn_register);
        et_email = (EditText) findViewById(R.id.et_email);
        et_password = (EditText) findViewById(R.id.et_password);
        pd = new ProgressView(LoginActivity.this,R.drawable.spinner);
        pd.setCancelable(false);
        pd.setCanceledOnTouchOutside(false);

        btn_login.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean login_success = attempt_login();

                // validating the form
                if(login_success) {


                }else{
                    btn_login.setClickable(true);
                    Toast.makeText(LoginActivity.this, errorMsg, Toast.LENGTH_LONG).show();
                }
            }
        });

        btn_register.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegistrationActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public boolean attempt_login() {

        et_email.setError(null);
        et_password.setError(null);

        String email = et_email.getText().toString();
        String password = et_password.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            et_email.setError(getString(R.string.error_field_required));
            focusView = et_email;
            cancel = true;
        } else if (!isEmailValid(email)) {
            et_email.setError(getString(R.string.error_invalid_email));
            focusView = et_email;
            cancel = true;
        }

        if (!isPasswordValid(password)){
            et_password.setError(getString(R.string.error_invalid_password));
            focusView = et_password;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
            btn_login.setClickable(true);
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.

            if (Common.isInternetAvailable(LoginActivity.this)) {
                // make ajax call and request for login attempt to server
                LoginTask authenticationTask = new LoginTask(
                        LoginActivity.this, et_email.getText().toString(), et_password.getText().toString(),pd,LoginActivity.this);
                authenticationTask.execute("Authentication");

            }else {
                Common.showNoNetworkNotification(getApplicationContext());
            }

        }

        // Saving the data in the app
        Common.IS_USER_LOGGED_IN = true;
        Common.email = et_email.getText().toString();
        Common.password = et_password.getText().toString();
        Common.uuid = "12312312310980980980";

        return !cancel;
    }

    private boolean isEmailValid(String email) {

        if(android.util.Patterns.EMAIL_ADDRESS.matcher(et_email.getText().toString()).matches() == false){
            return false;
        }
        return true;
    }

    private boolean isPasswordValid(String password) {
        return password.length() > 4;
    }

    @Override
    public void onTaskComplete(String response, int statusCode, HashMap<String, String> hashMap) {
        try {
            if (statusCode == HttpURLConnection.HTTP_OK && !response.isEmpty() && response != null) {
//                System.out.println("Activity receives response : " + response);
                JSONObject jsonResponse = new JSONObject(response);
                if (jsonResponse.optBoolean("success")){
                    JSONObject jsonUser = jsonResponse.optJSONObject("data");
                    Common.global_user.setId(Integer.parseInt(jsonUser.optString("id")));
                    Common.global_user.setFirstname(jsonUser.optString("first_name"));
                    Common.global_user.setLastname(jsonUser.optString("last_name"));
                    Common.global_user.setProfile_pic(jsonUser.optString("img_uri"));

                    Common.global_user.setGender(jsonUser.optString("gender"));
                    Common.global_user.setPhone_number(jsonUser.optString("phone_number"));
                    Common.global_user.setStatus(jsonUser.optString("status"));
//                    CbtConstants.currentUser.setSessionToken(jsonUser.optString("authentication_token"));
                    Common.global_user.setAppUser(jsonUser.optBoolean("is_admin"));
                    activityTransition();
                }else {
                    Toast.makeText(LoginActivity.this,jsonResponse.optString("message"),Toast.LENGTH_LONG).show();
                }
            }else if (statusCode == HttpURLConnection.HTTP_UNAUTHORIZED){
                Log.e(log,"Unauthorized User");
                Toast.makeText(LoginActivity.this,"Invalid username or password",Toast.LENGTH_LONG).show();
            }else if(statusCode == 0) {
                Log.e(log,"Socket Timeout Exception");
                showAlert(getString(R.string.request_timeout_header),getString(R.string.request_timeout_content));
            }else {
                System.out.println("Activity receives bad response :" + statusCode + " " + response);
                showAlert(getString(R.string.request_timeout_header),getString(R.string.request_timeout_content));
            }
        }catch (JSONException ee){
            Toast.makeText(LoginActivity.this,R.string.something_went_wrong,Toast.LENGTH_LONG).show();
            ee.printStackTrace();
        }
    }

    private void activityTransition() {
        if (Common.location == null){
            startActivity(new Intent(LoginActivity.this, AdditionalInfoActivity.class));
            finish();
        }else {
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
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
}

