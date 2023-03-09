package com.example.myapplication;

import com.example.myapplication.activity.MainActivity;

public class SetOrientation implements OrientationGetter{
    private float curr_orient;
    MainActivity activity;

    SetOrientation(MainActivity activity, float orientation) {
        this.activity = activity;
        this.curr_orient = orientation;
        this.activity.updateOrientation(curr_orient);
    }

    @Override
    public float getOrientation() {
        return curr_orient;
    }

    @Override
    public void halt() {

    }
}
