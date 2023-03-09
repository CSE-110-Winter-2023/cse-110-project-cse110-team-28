package com.example.myapplication.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Pair;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.ActualLocation;
import com.example.myapplication.ActualOrientation;
import com.example.myapplication.CoordinateUtil;
import com.example.myapplication.LocationGetter;
import com.example.myapplication.OrientationGetter;
import com.example.myapplication.R;
import com.example.myapplication.SetOrientation;

public class MainActivity extends AppCompatActivity {

    private LocationGetter locGetter;
    private OrientationGetter orientGetter;
    private float orientation_current;
    private float set_orientation;
    private String user_UUID;
    float currentDegree = 0.0f;



    LayoutHandler lh = new LayoutHandler();

    Friend myFriend = new Friend(55f, -100f, "Calvin", "myUUID");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadSetOrientation();
        loadProfile();

        // If nothing saved, launch InputActivity ( Do we want to check UUID or name?)
        if (user_UUID.equals("")) {
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

        orientation_text.setText(Float.toString(orientation));
        cardinal_text.setText(CoordinateUtil.cardDirection(orientation));
        updateParentRelDirection();

        RotateAnimation rotateAnimation =
                new RotateAnimation(currentDegree ,-1 *(this.orientation_current), Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotateAnimation.setDuration(250);
        rotateAnimation.setFillAfter(true);

        this.currentDegree = -1*(this.orientation_current);
        ImageView imageView = findViewById(R.id.compassImg);
        imageView.startAnimation(rotateAnimation);


    }

    // TODO adapt this for friends instead
    public void updateParentRelDirection() {

    }

    public void updateLocation(Pair<Double, Double> loc) {
        TextView location_text = findViewById(R.id.location_text);

        ImageView point = findViewById(R.id.point);
        //point.setX(CoordinateUtil.directionBetweenPoints(loc.first,myFriend.getLat(),loc.second,myFriend.getLong()));
        //point.setY(CoordinateUtil.directionBetweenPoints(loc.first,myFriend.getLat(),loc.second,myFriend.getLong()));

        //getResources().getDisplayMetrics().density;

        //int px = (int) (150*getResources().getDisplayMetrics().density/160);
        float angle = CoordinateUtil.directionBetweenPoints(loc.first,myFriend.getLat(),loc.second,myFriend.getLong());
        point.setX(lh.x_coordinate(angle));
        point.setY(lh.y_coordinate(angle));

        // TODO Update all friend locations too
        location_text.setText(Double.toString(loc.first) + " , " + Double.toString(loc.second));
        updateParentRelDirection();
        point.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onPause() {
        super.onPause();
        // this.orientGetter.halt();
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadProfile();
        ImageView point = findViewById(R.id.point);
        point.setVisibility(View.INVISIBLE);
        // When returning from InputActivity, check for mocked orientation
        loadSetOrientation();
        /* TODO dont forget about this
        if (this.set_orientation == 360) {
            orientGetter = new ActualOrientation(this);
        }
        else {
            orientGetter = new SetOrientation(this, set_orientation);
        }

        locGetter = new ActualLocation(this);
*/

        // Display user's UUID
        TextView uuid_view = findViewById(R.id.uuid_view);
        uuid_view.setText("Your UUID: " + user_UUID);


    }

    private void loadProfile() {
        SharedPreferences preferences = getSharedPreferences("saved_data", MODE_PRIVATE);

        this.user_UUID = preferences.getString("user_UUID", "");

    }
    private void loadSetOrientation() {
        SharedPreferences preferences = getSharedPreferences("saved_data", MODE_PRIVATE);

        this.set_orientation = preferences.getFloat("set_orientation", 360);

    }

    public void onLaunchInputClicked(View view) {
        Intent intent = new Intent(this, InputActivity.class);
        startActivity(intent);
    }

    // Temp test
    public void launchTest(View view) {
        Intent intent = new Intent(this, TestActivity.class);
        startActivity(intent);
    }
}