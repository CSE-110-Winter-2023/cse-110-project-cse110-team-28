package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private OrientationService orientationService;
    private LocationService locationService;

    private float lat_parents;
    private float long_parents;
    private String label_parents;
    private float orientation_current;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadProfile();
        // If nothing saved, launch InputActivity
        if (lat_parents == 91f || long_parents == 181f || label_parents == "")
        {
            Intent intent = new Intent(this, InputActivity.class);
            startActivity(intent);
        }

        // Ask for location permissions
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    200);
        }

        orientationService = OrientationService.singleton(this);
        TextView orientation_text = findViewById(R.id.orientation_text);
        TextView cardinal_text = findViewById(R.id.CardinalDirection);
        orientationService.getOrientation().observe(this, orientation -> {
            // Update textview with latest value
            orientation_text.setText(Float.toString(orientation));
            cardinal_text.setText(Utilities.cardDirection(orientation));
            orientation_current = orientation;


        });

        locationService = LocationService.singleton(this);
        TextView location_text = findViewById(R.id.location_text);
        TextView parent_orientation = findViewById(R.id.parentOrientation);

        locationService.getLocation().observe(this, loc -> {
            location_text.setText(Double.toString(loc.first) + " , " + Double.toString(loc.second));
            parent_orientation.setText(Utilities.directionBetweenPoints(loc.first, lat_parents, loc.second, long_parents));


        });

    }

    @Override
    protected void onPause() {
        super.onPause();
        orientationService.unregisterSensorListeners();
    }

    @Override
    protected void onResume() {
        super.onResume();
        // When returning from InputActivity, update the values so we can see
        loadProfile();
        TextView debug_parents = findViewById(R.id.debug_parents);
        debug_parents.setText(label_parents + ": lat = " + lat_parents + ", long = " + long_parents);

    }


    private void loadProfile() {
        SharedPreferences preferences = getSharedPreferences("saved_data", MODE_PRIVATE);

        this.label_parents = preferences.getString("label_parents", "");
        this.lat_parents = preferences.getFloat("lat_parents", 91f);
        this.long_parents = preferences.getFloat("long_parents", 181f);

        // TODO Do the same for the other 2 values...
    }
}