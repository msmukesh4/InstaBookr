package com.example.instabookr;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

/**
 * Created by mukesh on 7/7/16.
 */
public class ProfileEditActivity extends AppCompatActivity{

    EditText et_firstname,et_lastname,et_email,et_mobile_no1, et_mobile_no2;
    Spinner location_spinner;
    Button btn_update,btn_cancel;
    String errorMsg = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        et_firstname = (EditText) findViewById(R.id.et_firstname);
        et_lastname = (EditText) findViewById(R.id.et_lastname);
        et_email = (EditText) findViewById(R.id.et_email);
        et_mobile_no1 = (EditText) findViewById(R.id.et_mobile_no1);
        et_mobile_no2 = (EditText) findViewById(R.id.et_mobile_no2);
        location_spinner = (Spinner) findViewById(R.id.location_spinner);
        btn_update = (Button) findViewById(R.id.btn_submit);
        btn_cancel = (Button) findViewById(R.id.btn_cancel);

        // populate user data in the fields
        et_firstname.setText(Common.global_user.getFirstname().toString());
        et_lastname.setText(Common.global_user.getLastname().toString());
        et_email.setText(Common.global_user.getEmail().toString());
        et_mobile_no1.setText(Common.global_user.getPhone_number().toString());
        et_mobile_no2.setText(Common.global_user.getAltername_phone_number());

        // extract the user information from the edit text field and update user values
        // only if the user pressed update button other wise discard and move to previous activity

        // move to the previous activity
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                btn_update.setClickable(false);

                // first validate the form
                boolean login_success = attempt_login();

                if(login_success) {

                    Common.IS_USER_LOGGED_IN = true;


//                     start otp activity
                    startActivity(new Intent(ProfileEditActivity.this, UserProfileActivity.class));
                    finish();
                }else{
                    btn_update.setClickable(true);
//                    Toast.makeText(ProfileEditActivity.this, errorMsg, Toast.LENGTH_LONG).show();
                }

            }
        });

    }

    public boolean attempt_login() {

        et_firstname.setError(null);
        et_lastname.setError(null);
        et_mobile_no1.setError(null);
        et_email.setError(null);
//        et_password.setError(null);

        String firstname = et_firstname.getText().toString();
        String lastname = et_lastname.getText().toString();

        String mobile_number = et_mobile_no1.getText().toString();
        String email = et_email.getText().toString();
//        String password = et_password.getText().toString();

        boolean cancel = false;
        View focusView = null;

        if (TextUtils.isEmpty(firstname)) {
            et_firstname.setError(getString(R.string.error_field_required));
            focusView = et_firstname;
            cancel = true;
        }
        if (TextUtils.isEmpty(lastname)) {
            et_lastname.setError(getString(R.string.error_field_required));
            focusView = et_lastname;
            cancel = true;
        }

        // Check for valid mobile number
        if (TextUtils.isEmpty(mobile_number)) {
            et_mobile_no1.setError(getString(R.string.error_field_required));
            focusView = et_mobile_no1;
            cancel = true;
        } else if (!isNumberValid(mobile_number)){
            et_mobile_no1.setError(getString(R.string.error_invalid_number));
            focusView = et_mobile_no1;
            cancel = true;
        }

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

        // Check for a valid password, if the user entered one.
//        if (!TextUtils.isEmpty(password)) {
//            et_password.setError(getString(R.string.error_field_required));
//            focusView = et_password;
//            cancel = true;
//        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
            btn_update.setClickable(true);
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
//            showProgress(true);
//            mAuthTask = new UserLoginTask(email, password);
//            mAuthTask.execute((Void) null);
        }

        Common.firstname = et_firstname.getText().toString();
        Common.lastname = et_lastname.getText().toString();
        Common.mobile_number = et_mobile_no1.getText().toString();
        Common.email = et_email.getText().toString();
//        Common.password = password;


        return !cancel;
    }

    private boolean isEmailValid(String email) {

        if(android.util.Patterns.EMAIL_ADDRESS.matcher(et_email.getText().toString()).matches() == false){
            return false;
        }
        return true;
    }

    private boolean isNumberValid(String number) {
        return number.length() == 10;
    }


}
