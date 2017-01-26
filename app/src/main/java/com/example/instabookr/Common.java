package com.example.instabookr;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.example.instabookr.models.Cart;
import com.example.instabookr.models.Offer;
import com.example.instabookr.models.Service;
import com.example.instabookr.models.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mukesh on 4/6/16.
 */
public class Common {

    public static boolean IS_USER_LOGGED_IN = false;

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

    public static String daySelected = null;
    public static String dateSelected = null;
    public static String monthSelected = null;

    public static ArrayList<Service> gymServices = new ArrayList<>();
    public static ArrayList<Service> spaServices = new ArrayList<>();
    public static ArrayList<Service> sportsServices = new ArrayList<>();
    public static ArrayList<Service> salonServices = new ArrayList<>();

    public static ArrayList<Offer> offerList = new ArrayList<>();


    public static List<Cart> cartArrayList;


    public static int cart_items = 0;


    public static ArrayList<String> categories = new ArrayList<String>();

    public static String getDayName(int day){
        switch(day){
            case 1:
                return "SUN";
            case 2:
                return "MON";
            case 3:
                return "TUE";
            case 4:
                return "WED";
            case 5:
                return "THUR";
            case 6:
                return  "FRI";
            case 7:
                return "SAT";
        }

        return "Worng Day";
    }

    public static void initCart(){
        cartArrayList = new ArrayList<Cart>();
        Cart tempCartObj = new Cart();
        tempCartObj.setShop_name("Raju spa");
        tempCartObj.setService_name("full body spa");
        tempCartObj.setService_uuid("123123123123123123123");
        tempCartObj.setTime_slots("12:00-13:00");
        tempCartObj.setCost(1000);
        tempCartObj.setPeriod(120);
        cartArrayList.add(tempCartObj);

        tempCartObj = new Cart();
        tempCartObj.setShop_name("Raj salon");
        tempCartObj.setService_name("haircut");
        tempCartObj.setService_uuid("123123123123123123123");
        tempCartObj.setTime_slots("12:00-13:00");
        tempCartObj.setCost(500);
        tempCartObj.setOffer_cost(200);
        cartArrayList.add(tempCartObj);

        tempCartObj = new Cart();
        tempCartObj.setShop_name("Shyamu Sports");
        tempCartObj.setService_name("Cricket");
        tempCartObj.setService_uuid("1231231sdcmm23123123123");
        tempCartObj.setTime_slots("15:00-16:00");
        tempCartObj.setCost(200);
        tempCartObj.setPeriod(1120);
        cartArrayList.add(tempCartObj);
    }

    public static void initServices(){
        Service s = new Service("name",12);
        Service s2 = new Service("nnnn",2);
        Service s3 = new Service("mmmm",112);
        Service s4 = new Service("kkkk",122);

        gymServices.add(s);
        spaServices.add(s2);
        salonServices.add(s3);
        sportsServices.add(s4);

    }



    public static boolean isInternetAvailable(Context context){
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    public static void showNoNetworkNotification(final Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("No Network Connection");
        builder.setCancelable(false);
        builder.setNegativeButton("Dismiss", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });
        builder.setMessage("Please check your network connection.");
        builder.show();
    }


    public static final String CLIENT_ID = "";
    public static final String CLIENT_SECRET = "";

    // urls
    public static final String baseUrl = "";
    public static final String signInUrl = baseUrl+"oauth/token.json";
    public static final String getUserDetails = baseUrl+"api/v1/users/get_user_data";





}
