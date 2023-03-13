package com.example.myapplication;

public class Friend {

    private float lat;
    private float longit;
    private String username;
    private String UUID;

    private LocationService locationService;

    public Friend(float lat, float longit, String username, String UUID) {
        this.lat = lat;
        this.longit = longit;
        this.username = username;
        this.UUID = UUID;
    }

    public float getLat() {
        return lat;
    }

    public float getLong() {
        return longit;
    }

    String getName() {
        return username;
    }

    String getUUID() {
        return UUID;
    }

}
