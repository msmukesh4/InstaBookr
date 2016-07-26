package com.example.instabookr;

import com.example.instabookr.models.User;

import java.util.ArrayList;

/**
 * Created by mukesh on 4/6/16.
 */
public class Common {

    public static boolean IS_USER_LOGGED_IN = true;

    public static User global_user = new User();
//    user info
    public static String uuid = null;
    public static String location = null;
    public static String firstname = null;
    public static String lastname = null;
    public static String mobile_number = null;
    public static int id = 0;
    public static String email = null;
    public static String password = null;
    public static boolean registered = false;

    public static int cart_items = 0;

    public static ArrayList<String> categories = new ArrayList<String>();

}
