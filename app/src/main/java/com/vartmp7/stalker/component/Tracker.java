package com.vartmp7.stalker.component;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;

/**
 * @author Xiaowei Wen, Lorenzo Taschin
 */
public class Tracker implements LocationListener {

    private Context mContext;
    private StalkerTrackingCallBack callBack;
    private  boolean inPlace;
    public  Tracker(Context context, StalkerTrackingCallBack s){
        mContext= context;
        callBack= s;
    }



    @Override
    public void onLocationChanged(Location location) {
        if (callBack != null){
            callBack.onLocationsChanged(location );
        }
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
