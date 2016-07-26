package com.example.instabookr;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class LoginActivity extends AppCompatActivity {

    Button btn_login,btn_register;
    EditText et_email,et_password;
    String errorMsg = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btn_login = (Button) findViewById(R.id.btn_login);
        btn_register = (Button) findViewById(R.id.btn_register);
        et_email = (EditText) findViewById(R.id.et_email);
        et_password = (EditText) findViewById(R.id.et_password);

        btn_login.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean login_success = attempt_login();

                // validating the form
                if(login_success) {

                    if (Common.location == null){
                        startActivity(new Intent(LoginActivity.this, AdditionalInfoActivity.class));
                        finish();
                    }else {
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                        finish();
                    }
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
//            showProgress(true);
//            mAuthTask = new UserLoginTask(email, password);
//            mAuthTask.execute((Void) null);
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

}

