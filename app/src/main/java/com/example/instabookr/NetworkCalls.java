package com.example.instabookr;

/**
 * Created by msmuk on 23-10-2016.
 */
import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Switch;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Mukesh on 9/19/16.
 */
public class NetworkCalls extends AsyncTask<String, Void, String> {

    private Activity activity;
    private ProgressView pd;
    private TaskCompleteListener<String> callback;
    private String url;
    private String methodType;
    private int responseCode;
    private String response = "";
    private boolean success = false;
    private HashMap<String, String> params;
    private JSONObject jsonParams;
    HashMap<String,String> hashMap;

    public NetworkCalls(Activity activity, ProgressView pd, TaskCompleteListener<String> callback, String url, String methodType, HashMap<String, String> params){
        this.activity = activity;
        this.pd = pd;
        this.callback = callback;
        this.url = url;
        this.methodType = methodType;
        this.params = params;
        hashMap = new HashMap<>();
    }

    NetworkCalls(Activity activity,ProgressView pd, TaskCompleteListener<String> callback, String url,String methodType,String demo,String jsonParams) throws JSONException {
        this.activity = activity;
        this.pd = pd;
        this.callback = callback;
        this.url = url;
        this.methodType = methodType;
        this.jsonParams = new JSONObject(jsonParams);
        hashMap = new HashMap<>();
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        pd.setCancelable(false);
        pd.setCanceledOnTouchOutside(false);
        pd.show();
    }

    @Override
    protected String doInBackground(String... strings) {
        try {

            switch (methodType) {
                case "post":
                case "POST":
                case "Post":
                    return postCall(url, params);

                case "get":
                case "Get":
                case "GET":
                    return getCall(url, params);

                case "RAWPOST":
                case "RawPost":
                    return postRawData(url,jsonParams);

                default:
                    return "";

            }
        }catch (Exception ee){
            ee.printStackTrace();
        }

        return "";
    }

    @Override
    protected void onPostExecute(String s) {
        pd.dismiss();



        if (callback != null)
            callback.onTaskComplete(s,responseCode,hashMap);
    }


    public String  postCall(String requestURL,
                            HashMap<String, String> postDataParams) {


        try {
            URL u = new URL(requestURL);

            HttpURLConnection conn = (HttpURLConnection) u.openConnection();
            conn.setReadTimeout(15000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("POST");
            conn.setDoInput(true);
            conn.setDoOutput(true);


            OutputStream os = conn.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));

            writer.write(getDataString(postDataParams));

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
            }else if(responseCode == HttpURLConnection.HTTP_INTERNAL_ERROR){

                Log.e("Error ","received an internal server error while posting : "+getDataString(postDataParams));
                Log.e("Response code : ",""+responseCode);
                response="";

            } else if(responseCode == HttpURLConnection.HTTP_FORBIDDEN || responseCode == HttpURLConnection.HTTP_UNAUTHORIZED){

                Log.e("Error ","received forbidden/unauthorized error while posting : "+getDataString(postDataParams));
                Log.e("Response code : ",""+responseCode);
                response="";

            }else if(responseCode == HttpURLConnection.HTTP_BAD_REQUEST){

                Log.e("Error ","received a bad request error : "+getDataString(postDataParams));
                Log.e("Response code : ",""+responseCode);
                response="";

            }else {
                response="";

            }

            System.out.println("first response : "+response);

        }catch (Exception e){
            pd.dismiss();
            response = generateErrorJsonResponce();
            success = false;
            e.printStackTrace();
        }

        return response;
    }

    private String postRawData(String requestURL,
                               JSONObject jsonParams) {


        try {
            URL u = new URL(requestURL);

            HttpURLConnection conn = (HttpURLConnection) u.openConnection();
            conn.setReadTimeout(15000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type","application/json");
            conn.setDoInput(true);
            conn.setDoOutput(true);


            OutputStream os = conn.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));

            writer.write(jsonParams.toString());

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
            }else if(responseCode == HttpURLConnection.HTTP_INTERNAL_ERROR){

                Log.e("Error ","received an internal server error while posting raw data "+jsonParams.toString());
                Log.e("Response code : ",""+responseCode);
                response="";

            } else if(responseCode == HttpURLConnection.HTTP_FORBIDDEN || responseCode == HttpURLConnection.HTTP_UNAUTHORIZED){

                Log.e("Error ","received forbidden/unauthorized error while posting raw data "+jsonParams.toString());
                Log.e("Response code : ",""+responseCode);
                response="";

            }else if(responseCode == HttpURLConnection.HTTP_BAD_REQUEST){

                Log.e("Error ","received a bad request error while posting raw data "+jsonParams.toString());
                Log.e("Response code : ",""+responseCode);
                response="";

            } else if(responseCode == 422){ // unprocessable entity

                Log.e("Error ","received a bad request error while posting raw data "+jsonParams.toString());
                Log.e("Response code : ",""+responseCode);

            }else {
                response="";
                Log.e("Response code : ",""+responseCode);

            }

            System.out.println("first response : "+response);

        }catch (Exception e){
            pd.dismiss();
            response = generateErrorJsonResponce();
            success = false;
            e.printStackTrace();
        }

        return response;
    }

    private String getCall(String requestURL,HashMap<String, String> getDataParams) throws IOException {
        try {
            URL u = new URL(requestURL +"?"+getDataString(getDataParams));
            HttpURLConnection conn = (HttpURLConnection) u.openConnection();
            conn.setReadTimeout(15000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("GET");
            conn.connect();

            responseCode = conn.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                String line;
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                while ((line = br.readLine()) != null) {
                    response += line;
                    success = true;
                }
            }else if(responseCode == HttpURLConnection.HTTP_INTERNAL_ERROR){

                Log.e("Error ","received an internal server error while requesting get : "+requestURL +"?"+getDataString(getDataParams));
                Log.e("Response code : ",""+responseCode);
                response="";

            } else if(responseCode == HttpURLConnection.HTTP_FORBIDDEN || responseCode == HttpURLConnection.HTTP_UNAUTHORIZED){

                Log.e("Error ","received forbidden/unauthorized error while requesting get : "+requestURL +"?"+getDataString(getDataParams));
                Log.e("Response code : ",""+responseCode);
                response="";

            }else if(responseCode == HttpURLConnection.HTTP_BAD_REQUEST){

                Log.e("Error ","received a bad request error while requesting get : "+requestURL +"?"+getDataString(getDataParams));
                Log.e("Response code : ",""+responseCode);
                response="";

            } else {
                Log.e("Error ","received a error while requesting get : "+requestURL +"?"+getDataString(getDataParams));
                Log.e("Response code : ",""+responseCode);
                response = "";

            }
        }catch (Exception ee){
            pd.dismiss();
            response = generateErrorJsonResponce();
            success = false;
            ee.printStackTrace();
            Log.e("Exception","Error in get call");

        }

        return response;
    }

    private String getDataString(HashMap<String, String> params) throws UnsupportedEncodingException {
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

    private String generateErrorJsonResponce(){

//        org.json.simple.JSONObject jsonObject = new org.json.simple.JSONObject();
//        jsonObject.put("success",false);
//        jsonObject.put("message",R.string.server_error_msg);
//
//        return jsonObject.toString();
        return "";
    }
}