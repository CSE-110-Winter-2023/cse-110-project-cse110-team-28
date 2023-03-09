package com.example.myapplication;

import android.util.Pair;

import com.example.myapplication.activity.MainActivity;


public class ActualLocation implements LocationGetter{

    private LocationService locationService;
    MainActivity activity;
    private Pair<Double, Double> location;
    private double latitude;
    private double longitude;


    ActualLocation(MainActivity activity) {

        this.activity = activity;

        locationService = LocationService.singleton(this.activity);


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

}
