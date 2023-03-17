package com.example.myapplication.activity;

import android.util.Pair;

public interface LocationGetter {

    public Pair<Double,Double> getLocation();
    public boolean checkIfGPSOnline();
    public long GPSOfflineTime();
}
