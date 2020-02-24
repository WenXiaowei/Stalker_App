package com.vartmp7.stalker;

import android.location.Location;

interface StalkerCallBack {
    boolean onLocationsChanged(Location l);
}
