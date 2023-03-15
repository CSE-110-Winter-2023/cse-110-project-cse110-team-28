package com.example.myapplication.location_data;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import java.time.Instant;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledFuture;

public class LocationRepository {

    // TODO: FRIEND DATA ONLY COMES IN
    // TODO: SELF DATA ONLY GOES OUT
    private final LocationDataDao dao;
    private LocationAPI locationAPI;
    private ScheduledFuture<?> poller;

    public LocationRepository(LocationDataDao dao) {
        this.dao = dao;
        this.locationAPI.provide();
    }

    // Local Methods
    public LiveData<List<LocationData>> getAllLocal() { return dao.getAll(); }
    public void upsertLocal(LocationData location) {
        dao.upsert(location);
        Instant instant = Instant.now();
        location.updated_at = instant.getEpochSecond();
    }
}
