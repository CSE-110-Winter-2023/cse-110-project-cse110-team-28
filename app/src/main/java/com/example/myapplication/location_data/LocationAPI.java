package com.example.myapplication.location_data;

import androidx.annotation.WorkerThread;

import okhttp3.OkHttpClient;

public class LocationAPI {
    private volatile static LocationAPI instance = null;
    private OkHttpClient client;

    public LocationAPI() { this.client = new OkHttpClient(); }

    public static LocationAPI provide() {
        if (instance == null) {
            instance = new LocationAPI();
        }
        return instance;
    }

    /**
     * DON'T FORGET: Java disallows network requests on the main thread.
     */
    @WorkerThread
    public List<LocationAPI>
}
