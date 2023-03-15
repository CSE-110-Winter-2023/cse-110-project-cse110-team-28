package com.example.myapplication.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.myapplication.AlertUtil;
import com.example.myapplication.R;
import com.example.myapplication.location_data.LocationAPI;
import com.example.myapplication.location_data.LocationData;
import com.example.myapplication.location_data.LocationDataDao;
import com.example.myapplication.location_data.LocationDatabase;

import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;

public class AddFriendActivity extends AppCompatActivity {
    private LocationDatabase db = LocationDatabase.provide(this);
    private LocationDataDao dao = LocationDatabase.instance.getDao();
    private LocationAPI api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_friend);

        SharedPreferences preferences = getSharedPreferences("saved_data", MODE_PRIVATE);
        api = LocationAPI.provide(preferences.getString("custom_server", "https://socialcompass.goto.ucsd.edu/"));
    }

    public void onSubmitButtonClicked(View view) {
        // Data validation
        EditText uuid_text = findViewById(R.id.friend_UUID);
        EditText name_text = findViewById(R.id.friend_name);

        String name = name_text.getText().toString();
        String uuid = uuid_text.getText().toString();


        var executor = Executors.newSingleThreadExecutor();
        Future<LocationData> got = executor.submit(() -> {
            return api.get(uuid);
        });

        if(name.equals("")) {
            AlertUtil.showAlert(this, "Please enter a name!");
            return;
        }
        if (uuid.equals("")) {
            AlertUtil.showAlert(this, "Please enter a UUID!");
            return;
        }

        LocationData ld;

        try {
            ld = got.get();
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        if (ld == null) {
            AlertUtil.showAlert(this, "invalid UUID");
            return;
        }

        executor.shutdown();

        // Check if friend already exists...
        if (dao.exists(uuid)) {
            AlertUtil.showAlert(this, "You've already added this friend!");
            return;
        }


        LocationData newFriend = new LocationData(uuid, name, -361f, -361f, false);
        dao.upsert(newFriend);

        finish();
    }
}