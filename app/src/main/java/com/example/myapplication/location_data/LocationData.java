package com.example.myapplication.location_data;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

/*
 * Used to package our friend's location information into a nice object.
 */
@Entity(tableName = "location_data")
public class LocationData {
    // UUID is used as the public code on the server.
    @PrimaryKey
    @SerializedName("public_code")
    @NonNull
    public String public_code;

    @SerializedName("private_code") public String private_code;

    @SerializedName("label") public String label;

    @SerializedName("latitude") public float latitude;

    @SerializedName("longitude") public float longitude;

    @SerializedName("created_at") public long created_at;

    // Defaults to 0, so that if Location Data already exists remotely, its content is always preferred
    @SerializedName("updated_at") public long updated_at = 0;

    // Constructor
    public LocationData(@NonNull String public_code, String private_code, String label, float latitude, float longitude, long created_at) {
        this.public_code = public_code;
        this.private_code = private_code;
        this.label = label;
        this.latitude = latitude;
        this.longitude = longitude;
        this.created_at = created_at;
        this.updated_at = 0;
    }

    public static LocationData fromJSON(String json) { return new Gson().fromJson(json, LocationData.class); }

    public String toJSON() { return new Gson().toJson(this); }
}
