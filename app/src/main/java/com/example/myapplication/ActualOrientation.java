package com.example.myapplication;

import android.app.Activity;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class ActualOrientation implements OrientationGetter{

    private OrientationService orientationService;
    private float curr_orient;
    MainActivity activity;
    ActualOrientation(MainActivity activity) {
        this.activity = activity;
        this.orientationService = OrientationService.singleton(this.activity);
//

        this.orientationService.getOrientation().observe(this.activity, orientation -> {
            // Update textview with latest value

            this.curr_orient = orientation;
            this.activity.updateOrientation(this.curr_orient);
        });
    }

    @Override
    public float getOrientation() {
        return curr_orient;
    }

    @Override
    public void halt() {
        this.orientationService.unregisterSensorListeners();
    }
}
