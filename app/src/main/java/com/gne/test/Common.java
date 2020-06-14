package com.gne.test;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class Common {

    public static boolean isNetworkAvailable(Context context){
        boolean haveConnectedWifi = false;
        boolean haveConnectedMobile = false;
        ConnectivityManager connectivityManager=(ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager == null)
            return false;
        NetworkInfo[] netInfo = connectivityManager.getAllNetworkInfo();
        for (NetworkInfo ni : netInfo) {
            if (ni.getTypeName().equalsIgnoreCase("WIFI"))
                if (ni.isConnected())
                    haveConnectedWifi = true;
            if (ni.getTypeName().equalsIgnoreCase("MOBILE"))
                if (ni.isConnected())
                    haveConnectedMobile = true;
        }
        return haveConnectedWifi || haveConnectedMobile;
    }
}
