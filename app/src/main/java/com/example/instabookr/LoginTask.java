package com.example.instabookr;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.ActionBar;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;



/**
 * Created by Mukesh on 9/15/16.
 */
public class LoginTask extends AsyncTask<String, Void, String> {

    Activity activity;
    private String userName;
    private String password;
    Boolean success = false;
    private ProgressView pd;
    URL url;
    String response = "";
    private TaskCompleteListener<String> callback;
    int responseCode;


    public LoginTask(Activity activity, String userName,
                              String password, ProgressView pd, TaskCompleteListener<String> cb) {
        this.activity = activity;
        this.userName = userName;
        this.password = password;
        this.pd = pd;
        this.callback = cb;
    }

    public LoginTask(String userName,
                              String password) {

        this.userName = userName;
        this.password = password;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        if (pd != null) {
            pd.setCancelable(false);
            pd.setCanceledOnTouchOutside(false);
            pd.show();
        }

    }

    protected String doInBackground(String... strings) {


        HashMap<String, String> params = new HashMap<>();
        params.put("grant_type", "password");
        params.put("client_id", Common.CLIENT_ID);
        params.put("client_secret", Common.CLIENT_SECRET);
        params.put("username", userName);
        params.put("password", password);
        return performPostCall(Common.signInUrl, params);


    }

    @Override
    protected void onPostExecute(String s) {
        System.out.println("response : "+s);
        if (pd != null)
            pd.dismiss();

        HashMap<String,String> hashMap = new HashMap<>();
        hashMap.put("email",userName);
        hashMap.put("password",password);

        if (callback != null)
            callback.onTaskComplete(s,responseCode,hashMap);

    }



    public String  performPostCall(String requestURL,
                                   HashMap<String, String> postDataParams) {


        try {
            url = new URL(requestURL);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(15000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("POST");
            conn.setDoInput(true);
            conn.setDoOutput(true);


            OutputStream os = conn.getOutputStream();
            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(os, "UTF-8"));
            writer.write(getPostDataString(postDataParams));

            writer.flush();
            writer.close();
            os.close();
            responseCode=conn.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                String line;
                BufferedReader br=new BufferedReader(new InputStreamReader(conn.getInputStream()));
                while ((line=br.readLine()) != null) {
                    response+=line;
                    success = true;
                }
            }
            else {
                response="";

            }

            System.out.println("first response : "+response);

            if (response != ""){
                JSONObject jsonResponse = new JSONObject(response);
                Common.global_user.setEmail(userName);
                Common.global_user.setPassword(password);
                Common.global_user.setAccessToken(jsonResponse.optString("access_token"));
                response = makeSessionRequestcall(Common.getUserDetails,jsonResponse.optString("access_token"));
            }else{
                success = false;
                return response;
            }

        }catch (SocketTimeoutException ste) {
            Log.e("Authentication Task","Unable to Connect to server");
            Log.d("Authetication Task",ste.getMessage());
            response = "";
            success = false;
            ste.printStackTrace();
            return "";
        } catch (JSONException e) {
            Log.d("Login task", "caught a json exception while logging in");
            response = "";
            success = false;
            e.printStackTrace();
            return "";
        } catch (Exception e){
            response = "";
            success = false;
            e.printStackTrace();
            return "";
        }

        return response;
    }

    private String makeSessionRequestcall(String requestURL, String access_token) throws IOException {
        String response="";
        url = new URL(requestURL+"?&access_token="+access_token);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setReadTimeout(15000);
        conn.setConnectTimeout(15000);
        conn.setRequestMethod("GET");
        conn.connect();

        responseCode = conn.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            String line;
            BufferedReader br=new BufferedReader(new InputStreamReader(conn.getInputStream()));
            while ((line=br.readLine()) != null) {
                response+=line;
                success = true;
            }
        }else {
            response="";

        }


        return response;
    }

    private String getPostDataString(HashMap<String, String> params) throws UnsupportedEncodingException{
        StringBuilder result = new StringBuilder();
        boolean first = true;
        for(Map.Entry<String, String> entry : params.entrySet()){
            if (first)
                first = false;
            else
                result.append("&");

            result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
        }

        return result.toString();
    }


}
