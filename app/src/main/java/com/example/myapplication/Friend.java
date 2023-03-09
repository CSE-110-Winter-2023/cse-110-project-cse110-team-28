package com.example.myapplication;

public class Friend {

    private float lat;
    private float longit;
    private String username;
    private String UUID;

    Friend( float lat, float longit, String username, String UUID) {
        this.lat = lat;
        this.longit = longit;
        this.username = username;
        this.UUID = UUID;
    }

    float getLat() {
        return lat;
    }

    float getLong() {
        return longit;
    }

    String getName() {
        return username;
    }

    String getUUID() {
        return UUID;
    }

}
