package com.example.myapplication.location_data;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class LocationViewModel extends AndroidViewModel {
    private LiveData<List<LocationData>> data;
    private final LocationRepository repo;

    public LocationViewModel(@NonNull Application application) {
        super(application);
        var context = application.getApplicationContext();
        var db = LocationDatabase.provide(context);
        var dao = db.getDao();
        this.repo = new LocationRepository(dao);
    }

    /**
     * Load all from the database.
     * @return a LiveData object that will be updated when any locations change.
     */
    public LiveData<List<LocationData>> getData() {
        if (data == null) {
            data = repo.getAllLocal();
        }
        return data;
    }

    /**
     * Open a note in the database. If the note does not exist, create it.
     * @param title the title of the note
     * @return a LiveData object that will be updated when this note changes.
     * TODO
     */
    public LiveData<LocationData> getOrCreateNote(String title) {
//        if (!repo.existsLocal(title)) {
//            var note = new LocationData(title, "");
//            repo.upsertLocal(note);
//        }
//
//        return repo.getLocal(title);
        return null;
    }

    public void delete(LocationData data) { repo.deleteLocal(data); }

}
