package com.example.myapplication;

import static com.example.myapplication.CoordinateUtil.cardDirection;
import static com.example.myapplication.CoordinateUtil.directionBetweenPoints;

import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class Friend extends AppCompatActivity {
    private float lat, lon;
    private String username;
    private String user_UUID;

    private float currentDegree;

    Friend(){
        this.lat = 0;
        this.lon = 0;
        this.username = "";
        this.user_UUID = "";
        this.currentDegree = 0.0f;

    }

    Friend(float lat, float lon, String username, String user_UUID, float currentDegree) {
        this.lat = lat;
        this.lon = lon;
        this.username = username;
        this.user_UUID = user_UUID;
        this.currentDegree = currentDegree;
    }

    void friendLocation(float friendLat, float friendLong, float currOrientation){
        //find distance between us and friend
        //find relative orientation between us and friend

      float angle = directionBetweenPoints(this.lat, friendLat, this.lon, friendLong);

      String direction = cardDirection(angle);
      ImageView img = findViewById(R.id.point);

//      RotateAnimation rotateAnimation =
//                new RotateAnimation(currentDegree ,-1 *(angle), Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
//      rotateAnimation.setDuration(250);
//      rotateAnimation.setFillAfter(true);
//      img.startAnimation(rotateAnimation);
    }

}
