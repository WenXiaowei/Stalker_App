package com.vartmp7.stalker.component;

import android.location.Location;

/**
 * @author Xiaowei Wen, Lorenzo Taschin
 */
public interface StalkerTrackingCallBack {
    void onLocationsChanged(Location l);
}
