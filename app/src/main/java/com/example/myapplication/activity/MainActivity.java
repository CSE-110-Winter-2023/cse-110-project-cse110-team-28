package com.example.myapplication.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.myapplication.CoordinateUtil;
import com.example.myapplication.friends.Friend;
import com.example.myapplication.LayoutHandler;
import com.example.myapplication.R;
import com.example.myapplication.location_data.LocationAPI;
import com.example.myapplication.location_data.LocationAdapter;
import com.example.myapplication.location_data.LocationData;
import com.example.myapplication.location_data.LocationDataDao;
import com.example.myapplication.location_data.LocationDatabase;
import com.example.myapplication.location_data.LocationViewModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    public RecyclerView recyclerView;

    private LocationGetter locGetter;
    private OrientationGetter orientGetter;
    private float orientation_current;

    private String user_UUID;
    private String user_name;
    private String private_code;
    private LocationAPI api;
    private LocationDataDao dao;
    private LocationViewModel viewModel;


    private ScheduledExecutorService executor;
    float currentDegree = 0.0f;
    private String custom_server = "https://socialcompass.goto.ucsd.edu/";



    LayoutHandler lh = new LayoutHandler();

    Friend myFriend = new Friend(55f, -100f, "Calvin", "myUUID");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadProfile();

        dao = LocationDatabase.provide(this).getDao();
        api = LocationAPI.provide(custom_server);

        // dao.delete_all();
        // THESE TWO GUYS ARE CURRENTLY IN THE LOCAL DATABASE AND ARE BEING DISPLAYED ON THE HOME SCREEN
        LocationData data2 = new LocationData("efb71004-d7b5-4067-b3b2-59904b7cda87", "Bob", 10f, 10f, true);
        // Bob's private code: 82b5ac85-6d9b-4084-8ebd-564363dacccb
        var data = new LocationData("c4a86ce2-fed4-4f98-bb1c-b5d83c968d93", "Calvin", 55f, -100f, true);
        // Calvin's private code: 8c89b79c-a03c-4580-b785-9be388e97f66

        // This user has...
        // public code: 68591d92-f36a-4b8a-89f1-702236f92848
        // private code: bacae4bd-6a4c-48f5-b3fc-2df94cb37f24

        // TODO do I want a custom cicrular layout manager...
        // TODO do I need to remove scrolling?
        // TODO extract these methods too once you've got MainActivity figured out

        viewModel = new ViewModelProvider(this).get(LocationViewModel.class);
        var adapter = new LocationAdapter();
        adapter.setHasStableIds(true);
        viewModel.getData().observe(this, adapter::setLocationData);
        viewModel.getData().observe(this, this::updateCompass);

        // If nothing saved, launch InputActivity ( Do we want to check UUID or name?)
        if (user_UUID.equals("UUID NOT FOUND") || private_code.equals("PRIVATE CODE NOT FOUND") || user_name.equals("USER NAME NOT FOUND")) {
            Intent intent = new Intent(this, InputActivity.class);
            startActivity(intent);
        }
    }

    private void updateCompass(List<LocationData> friends){
        Log.e("COMPASS", "UPDATED");
        if (friends == null) { return; };

        var friend_list = (ConstraintLayout) findViewById(R.id.friend_list);
        for (int i = 0; i < friends.size(); i ++ ) {
            View inflatedView = LayoutInflater.from(this).inflate(R.layout.friend_tag, friend_list, false);
            TextView friend = inflatedView.findViewById(R.id.name_tag);
            friend.setText(friends.get(i).label);
            ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams) inflatedView.getLayoutParams();
            params.circleAngle = 30 * i;
            params.circleRadius = 200;
            params.circleConstraint = R.id.friend_list;

            inflatedView.setLayoutParams(params);
            friend_list.addView(inflatedView);
        }
    }

    public void updateOrientation(float orientation) {
        this.orientation_current = orientation;

        TextView orientation_text = findViewById(R.id.orientation_text);

        orientation_text.setText(Float.toString(orientation));
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
        float angle = CoordinateUtil.directionBetweenPoints(loc.first,myFriend.getLat(),loc.second,myFriend.getLongit());
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
        executor.shutdown();
        // this.orientGetter.halt();
    }

    @Override
    protected void onResume() {
        super.onResume();

        checkLocationPermissions();
        loadProfile();

        ImageView point = findViewById(R.id.point);
        point.setVisibility(View.INVISIBLE);

        orientGetter = new ActualOrientation(this);
        locGetter = new ActualLocation(this);

        // Display user's UUID
        TextView uuid_view = findViewById(R.id.uuid_view);
        uuid_view.setText("Your UUID: " + user_UUID);

        boolean gpsstatus = locGetter.checkIfGPSOnline();
        ImageView red_dot = findViewById(R.id.reddot);
        ImageView green_dot = findViewById(R.id.greendot);
        red_dot.setVisibility(View.INVISIBLE);
        green_dot.setVisibility(View.INVISIBLE);
        if(gpsstatus == true){
            //green dot, gps active
            green_dot.setVisibility(View.VISIBLE);
        }
        else{
            red_dot.setVisibility(View.VISIBLE);
            //red dot, not active
        }


//        TextView gpsOnline = findViewById(R.id.gpsStatus);
//        gpsOnline.setText(String.valueOf(gpsstatus));

        setUpLocationExecutor();

    }

    private void checkLocationPermissions() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    200);
        }
    }

    private void setUpLocationExecutor() {
        // Every 3 seconds...
        executor = Executors.newSingleThreadScheduledExecutor();
        var poller = executor.scheduleAtFixedRate(() -> {
            var temp = viewModel.getData().getValue();
            List<LocationData> friends = new ArrayList<>(temp);

            Log.e("POLLER", "BEGIN");

            // Upload your current location to the server
            var location = locGetter.getLocation();
            var to_upload = new LocationData(this.user_UUID, this.user_name, location.first, location.second, false);
            to_upload.private_code = this.private_code;
            api.put(to_upload);

            // Pull the updated location of all your friends from the server
            for (var f : friends)
            {
                var got = api.get(f.public_code);
                dao.upsert(got);
                Log.e("FRIEND: ", got.toString());
            }



        }, 9, 3, TimeUnit.SECONDS);
    }

    private void loadProfile() {
        SharedPreferences preferences = getSharedPreferences("saved_data", MODE_PRIVATE);

        this.user_name = preferences.getString("user_name", "USERNAME NOT FOUND");
        this.user_UUID = preferences.getString("user_UUID", "UUID NOT FOUND");
        this.custom_server = preferences.getString("custom_server", "https://socialcompass.goto.ucsd.edu/");
        this.private_code = preferences.getString("private_code", "PRIVATE CODE NOT FOUND");

    }

    public void onLaunchInputClicked(View view) {
        Intent intent = new Intent(this, InputActivity.class);
        startActivity(intent);
    }

    public void onAddFriendClicked(View view) {
        Intent intent = new Intent(this, AddFriendActivity.class);
        startActivity(intent);
    }

}