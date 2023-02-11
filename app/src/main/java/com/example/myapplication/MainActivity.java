package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private OrientationService orientationService;
    private LocationService locationService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
            != PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
            != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    200);
        }


        orientationService = OrientationService.singleton(this);
        TextView orientation_text = findViewById(R.id.orientation_text);
        orientationService.getOrientation().observe(this, orientation -> {
            // Update textview with latest value
            orientation_text.setText(Float.toString(orientation));
        });

        locationService = LocationService.singleton(this);
        TextView location_text = findViewById(R.id.location_text);
        locationService.getLocation().observe(this, loc -> {
            location_text.setText(Double.toString(loc.first) + " , " + Double.toString(loc.second));
        });

    }

    @Override
    protected void onPause() {
        super.onPause();
        orientationService.unregisterSensorListeners();
    }
}