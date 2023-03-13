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
    private final LocationDataDao dao;
    private LocationAPI locationAPI;
    private ScheduledFuture<?> poller;

    public LocationRepository(LocationDataDao dao) {
        this.dao = dao;
        this.locationAPI.provide();
    }

    // Synced Methods
    /* This method will return a LiveData object that will be
     * updated when the note is updated either locally or remotely on the server. Our activities
     * however will only need to observe this one LiveData object, and don't need to care where
     * it comes from!
     */
    public LiveData<LocationData> getSynced(String public_code) {
        var location = new MediatorLiveData<LocationData>();

        Observer<LocationData> updateFromRemote = theirLocation -> {
            var ourLocation = location.getValue();
            if (theirLocation == null) return; // do nothing
            if (ourLocation == null || ourLocation.updated_at < theirLocation.updated_at) {
                upsertLocal(theirLocation);
            }
        };
        
        // If we get a local update, pass it on.
        location.addSource(getLocal(public_code), location::postValue);
        // If we get a remote update, update the local version (triggering the above observer)
        location.addSource(getRemote(public_code), updateFromRemote);

        return location;
    }

    public void upsertSynced(LocationData data) {
//        upsertLocal(data);
//        upsertRemote(data);
    }

    // Local Methods
    public LiveData<LocationData> getLocal(String public_code) { return dao.get(public_code); }

    public LiveData<List<LocationData>> getAllLocal() { return dao.getAll(); }
    public void upsertLocal(LocationData location) {
        dao.upsert(location);
        Instant instant = Instant.now();
        location.updated_at = instant.getEpochSecond();
    }

    public void deleteLocal(LocationData location) { dao.delete(location); }

    public boolean existsLocal(String UUID) { return dao.exists(UUID); }

    // Remote Methods
    public LiveData<LocationData> getRemote(String public_code) {
        // Cancel any previous poller if it exists
        if (this.poller != null && !this.poller.isCancelled()) {
            poller.cancel(true);
        }

        // Background thread, poll server every 3 seconds
        MutableLiveData<LocationData> livedata = new MutableLiveData<LocationData>();

        var executor = Executors.newSingleThreadScheduledExecutor();
        poller = executor.scheduleAtFixedRate(() -> {
            livedata.postValue(locationAPI.get(public_code));
        }, 0, 3, java.util.concurrent.TimeUnit.SECONDS);

        return livedata;
    }
}
