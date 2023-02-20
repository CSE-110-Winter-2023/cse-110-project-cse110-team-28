package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private LocationGetter locGetter;
    private OrientationGetter orientGetter;

    private float lat_parents;
    private float long_parents;
    private String label_parents;
    private float orientation_current;
    private float set_orientation;
    private float directionToParents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadSetOrientation();
        loadProfile();

        if (this.set_orientation == 360) {
            orientGetter = new ActualOrientation(this);
        }
        else {
            orientGetter = new SetOrientation(this, set_orientation);
        }
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

    }

    public void updateOrientation(float orientation) {
        this.orientation_current = orientation;

        TextView orientation_text = findViewById(R.id.orientation_text);
        TextView cardinal_text = findViewById(R.id.CardinalDirection);
        TextView parent_comp_text = findViewById(R.id.ParentCompassDirection);

        orientation_text.setText(Float.toString(orientation));
        cardinal_text.setText(Utilities.cardDirection(orientation));
        updateParentRelDirection();
    }

    public void updateParentRelDirection() {
        TextView par_comp_dir = findViewById(R.id.ParentCompassDirection);
        par_comp_dir.setText(Double.toString(this.directionToParents - this.orientation_current));
    }

    public void updateLocation(Pair<Double, Double> loc) {
        this.directionToParents = Utilities.directionBetweenPoints(loc.first, lat_parents, loc.second, long_parents);

        TextView location_text = findViewById(R.id.location_text);
        TextView parent_orientation = findViewById(R.id.parentOrientation);


        location_text.setText(Double.toString(loc.first) + " , " + Double.toString(loc.second));
        parent_orientation.setText(Utilities.cardDirection(this.directionToParents));
        updateParentRelDirection();
    }

    @Override
    protected void onPause() {
        super.onPause();
        this.orientGetter.halt();
    }

    @Override
    protected void onResume() {
        super.onResume();
        // When returning from InputActivity, update the values so we can see
        loadProfile();
        loadSetOrientation();
//        TextView setOrientView = findViewById(R.id.SetOrientation);
//        TextView orientTextView = findViewById(R.id.orientation_text);
        if (this.set_orientation == 360) {
            orientGetter = new ActualOrientation(this);
        }
        else {
            orientGetter = new SetOrientation(this, set_orientation);
        }

        locGetter = new ActualLocation(this);

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

    private void loadSetOrientation() {
        SharedPreferences preferences = getSharedPreferences("saved_data", MODE_PRIVATE);

        this.set_orientation = preferences.getFloat("set_orientation", 360);
    }

    public void onLaunchInputClicked(View view) {
        Intent intent = new Intent(this, InputActivity.class);
        startActivity(intent);
    }
}