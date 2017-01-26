package com.example.instabookr;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegistrationActivity extends AppCompatActivity {

    EditText fir;
    EditText et_firstname,et_lastname,et_mobile_no,et_email,et_password;
    Button btn_submit,btn_cancel;
    String errorMsg = "",log="Registration";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        et_firstname = (EditText) findViewById(R.id.et_firstname);
        et_lastname = (EditText) findViewById(R.id.et_lastname);
        et_email = (EditText) findViewById(R.id.et_email);
        et_mobile_no = (EditText) findViewById(R.id.et_mobile_no);
        et_password  = (EditText) findViewById(R.id.et_password);
        btn_submit = (Button) findViewById(R.id.btn_register);
        btn_cancel = (Button) findViewById(R.id.btn_cancel);


        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                btn_submit.setClickable(false);

                // validating the form
                boolean login_success = attempt_login();

                if(login_success) {

                    Common.IS_USER_LOGGED_IN = true;


//                     start otp activity
                    startActivity(new Intent(RegistrationActivity.this, OtpActivity.class));
                    finish();
                }else{
                    btn_submit.setClickable(true);
//                    Toast.makeText(RegistrationActivity.this,errorMsg,Toast.LENGTH_LONG).show();
                }
            }
        });
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public boolean attempt_login() {

        et_firstname.setError(null);
        et_lastname.setError(null);
        et_mobile_no.setError(null);
        et_email.setError(null);
        et_password.setError(null);

        String firstname = et_firstname.getText().toString();
        String lastname = et_lastname.getText().toString();

        String mobile_number = et_mobile_no.getText().toString();
        String email = et_email.getText().toString();
        String password = et_password.getText().toString();

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
            et_mobile_no.setError(getString(R.string.error_field_required));
            focusView = et_mobile_no;
            cancel = true;
        } else if (!isNumberValid(mobile_number)){
            et_mobile_no.setError(getString(R.string.error_invalid_number));
            focusView = et_mobile_no;
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
        if (!isPasswordValid(password)){
            et_password.setError(getString(R.string.error_invalid_password));
            focusView = et_password;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
            btn_submit.setClickable(true);
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
//            showProgress(true);
//            mAuthTask = new UserLoginTask(email, password);
//            mAuthTask.execute((Void) null);
        }

        // Saving the data in the app
        Common.firstname = et_firstname.getText().toString();
        Common.lastname = et_lastname.getText().toString();
        Common.mobile_number = et_mobile_no.getText().toString();
        Common.email = et_email.getText().toString();
        Common.password = password;


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

    private boolean isPasswordValid(String password) {
        return password.length() > 4;
    }

    @Override
    protected void onResume() {
        super.onResume();
        btn_submit.setClickable(true);
    }
}
