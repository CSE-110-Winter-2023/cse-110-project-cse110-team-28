package com.example.myapplication.friends;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "friends")
public class Friend {

    @PrimaryKey(autoGenerate = true)
    public long id;

    private float lat;
    private float longit;
    private String username;
    private String UUID;

    public Friend(float lat, float longit, String username, String UUID) {
        this.lat = lat;
        this.longit = longit;
        this.username = username;
        this.UUID = UUID;
    }

    public float getLat() {
        return lat;
    }

    public float getLongit() {
        return longit;
    }

    public String getUsername() {
        return username;
    }

    public String getUUID() {
        return UUID;
    }

    public void setLat(float lat) {
        this.lat = lat;
    }

    public void setLongit(float longit) {
        this.longit = longit;
    }

    @Override
    public String toString() {
        return "Friend{" +
                "lat=" + lat +
                ", longit=" + longit +
                ", username='" + username + '\'' +
                ", UUID='" + UUID + '\'' +
                '}';
    }
}
