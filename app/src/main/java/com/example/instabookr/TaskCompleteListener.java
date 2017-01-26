package com.example.instabookr;

import java.util.HashMap;

/**
 * Created by msmuk on 23-10-2016.
 */
public interface TaskCompleteListener<String> {
    public void onTaskComplete(String result, int statusCode, HashMap<String,String> hashMap);
}
