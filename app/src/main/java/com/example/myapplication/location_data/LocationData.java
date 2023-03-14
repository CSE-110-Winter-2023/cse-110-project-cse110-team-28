package com.example.myapplication.location_data;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.myapplication.location_data.annotations.PatchExclude;
import com.google.gson.Gson;
import com.google.gson.annotations.JsonAdapter;

import java.time.Instant;
import java.util.UUID;


/*
 * Used to package our friend's location information into a nice object.
 */
@Entity(tableName = "location_data")
public class LocationData {
    // UUID is used as the public code on the server.
    @PrimaryKey
    @PatchExclude
    @NonNull
    public String public_code; // aka UUID

    public String private_code;

    @PatchExclude
    public String label;


    public float latitude;


    public float longitude;

    @PatchExclude
    public boolean is_listed_publicly;

    @JsonAdapter(TimestampAdapter.class)
    @PatchExclude
    public long created_at;

    @JsonAdapter(TimestampAdapter.class)
    @PatchExclude
    // Defaults to 0, so that if Location Data already exists remotely, its content is always preferred
    public long updated_at = 0;

    // Constructor
    public LocationData(@NonNull String public_code, String label, float latitude, float longitude, boolean is_listed_publicly) {
        this.public_code = public_code;
        this.label = label;
        this.latitude = latitude;
        this.longitude = longitude;
        this.is_listed_publicly = is_listed_publicly;
        Instant instant = Instant.now();
        this.created_at = instant.getEpochSecond();
        this.updated_at = this.created_at;
    }

    public static LocationData fromJSON(String json) { return new Gson().fromJson(json, LocationData.class); }

    public String toJSON() { return new Gson().toJson(this); }
}
