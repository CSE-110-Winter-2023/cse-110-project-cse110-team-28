package com.example.myapplication.location_data;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class LocationViewModel extends AndroidViewModel {
    private LiveData<List<LocationData>> data;
    private final LocationDataDao dao;
//    private final LocationRepository repo;

    public LocationViewModel(@NonNull Application application) {
        super(application);
        var context = application.getApplicationContext();
        var db = LocationDatabase.provide(context);
        this.dao = db.getDao();
    }

    /**
     * Load all from the database.
     * @return a LiveData object that will be updated when any locations change.
     */
    public LiveData<List<LocationData>> getData() {
        if (data == null) {
            data = dao.getAll();
        }
        return data;
    }

    // We probably don't need to delete friends
//    public void delete(LocationData data) { repo.deleteLocal(data); }

}
