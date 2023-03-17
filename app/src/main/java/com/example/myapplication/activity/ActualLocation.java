package com.example.myapplication.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.util.Pair;
import java.util.Calendar;
import java.util.Date;

import androidx.core.app.ActivityCompat;

import com.example.myapplication.LocationService;


public class ActualLocation implements LocationGetter {

    private LocationService locationService;
    MainActivity activity;
    private Pair<Double, Double> location;
    private double latitude;
    private double longitude;

    private LocationManager locationManager;

    ActualLocation(MainActivity activity) {

        this.activity = activity;

        locationService = LocationService.singleton(this.activity);

        this.locationManager = (LocationManager) this.activity.getSystemService(Context.LOCATION_SERVICE);
        locationService.getLocation().observe(this.activity, loc -> {
            this.latitude = loc.first;
            this.longitude = loc.second;
            this.activity.updateLocation(loc);
            this.location = loc;

        });

    }

    @Override
    public Pair<Double, Double> getLocation() {
        return this.location;
    }

    public boolean checkIfGPSOnline() {
        final int delay = 5000;
        long currentTime = Calendar.getInstance().getTimeInMillis();
        return this.locationService.getUpdatedAt() > currentTime - delay;
    }

    public long GPSOfflineTime(){
        long currentTime = Calendar.getInstance().getTimeInMillis();
        return currentTime - this.locationService.getUpdatedAt();
    }



}
