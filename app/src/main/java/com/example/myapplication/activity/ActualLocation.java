package com.example.myapplication.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.GnssStatus;
import android.location.Location;
import android.location.LocationManager;
import android.util.Pair;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;

import com.example.myapplication.LocationService;


public class ActualLocation implements LocationGetter {

    private LocationService locationService;
    MainActivity activity;
    private Pair<Double, Double> location;
    private double latitude;
    private double longitude;

    private LocationManager locationManager;
    private BroadcastReceiver mGpsSwitchStateReceiver;

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

//    @SuppressLint("MissingPermission")
    public boolean checkIfGPSOnline() {
//        final boolean[] retval = {false};
//        BroadcastReceiver mGpsSwitchStateReceiver = new BroadcastReceiver() {
//            @Override
//            public void onReceive(Context context, Intent intent) {
//
//                if (intent.getAction().matches("android.location.PROVIDERS_CHANGED")) {
//                    // Make an action or refresh an already managed state.
//                    retval[0] = true;
//                }
//                else{
//                    retval[0] = false;
//                }
//            }
//        };

        GnssStatus.Callback mGnssStatusCallback = new GnssStatus.Callback() {
            @Override
            public void onSatelliteStatusChanged(@NonNull GnssStatus status) {
                super.onSatelliteStatusChanged(status);

            }
        };
        boolean mGPS = this.locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);

        return mGPS;
//        return retval[0];
    }

    //returns last known location as a pair of latitude,longitude
    public Pair<Double, Double> getLastKnownLocation() {
        Criteria criteria = new Criteria();
        if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_COARSE_LOCATION);
        }

        Location location = this.locationManager.getLastKnownLocation(locationManager.getBestProvider(criteria, false));
        Pair<Double, Double> knownLoc = new Pair<> (.0,.0);
        if(location !=null){
            double lat = location.getLatitude();
            double longi = location.getLongitude();

            this.latitude = lat;
            this.longitude = longi;

            knownLoc = new Pair<Double, Double> (this.latitude, this.longitude);
        }
        return knownLoc;
    }



}
