package com.example.myapplication.location_data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Query;
import androidx.room.Upsert;

import java.util.List;

// Data access object for LocationData class
@Dao
public abstract class LocationDataDao {

    @Upsert
    public abstract long upsert(LocationData data);

    @Query("SELECT EXISTS(SELECT 1 FROM location_data WHERE public_code = :UUID)")
    public abstract boolean exists(String UUID);

    @Query("SELECT * FROM location_data WHERE public_code = :UUID")
    public abstract LiveData<LocationData> get(String UUID);

    @Query("SELECT * FROM location_data ORDER BY public_code")
    public abstract LiveData<List<LocationData>> getAll();

    @Delete
    public abstract int delete(LocationData data);
}
