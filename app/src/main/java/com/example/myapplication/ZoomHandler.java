package com.example.myapplication;

import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

enum zoomStates {
        zoomOutMax,
        zoomMid,
        zoomInMax
        }



public class ZoomHandler {

    zoomStates currentZoom;

    ImageView c_out, c2, c3, c_in, c_mid;

    Button z_in, z_out;
    Activity mainActivity;

    double[] radiiMultiples = {1, 2, 3};

    public ZoomHandler(Activity activity) {
        mainActivity = activity;
        c_out = mainActivity.findViewById(R.id.Circle1);
        c2 = mainActivity.findViewById(R.id.Circle2);
        c3 = mainActivity.findViewById(R.id.Circle3);
        c_in = mainActivity.findViewById(R.id.Circle4);
        c_mid = mainActivity.findViewById(R.id.Circle_MIddle);
        //
        z_in = mainActivity.findViewById(R.id.zoomInButton);
        z_out = mainActivity.findViewById(R.id.zoomOutButton);
        //
        currentZoom = zoomStates.zoomMid;
        onZoomIn();

    }


    /*
    * 1. 0-1 mile
    * 2. 1-10 miles
    * 3. 10-500 miles
    * 4. 500+ miles
    * */
    public void updateRadii(int[] arr) {


    }

    public void onZoomIn() {

        switch(currentZoom) {
            case zoomInMax:
                break;
            case zoomMid:
                // change visibility
                c_out.setVisibility(View.VISIBLE);
                c2.setVisibility(View.INVISIBLE);
                c3.setVisibility(View.INVISIBLE);
                c_in.setVisibility(View.VISIBLE);
                c_mid.setVisibility(View.INVISIBLE);
                // for
                currentZoom = zoomStates.zoomInMax;
                // disable zoom in
                z_in.setEnabled(false);

                break;
            case zoomOutMax:
                // change visibility
                c_out.setVisibility(View.VISIBLE);
                c2.setVisibility(View.INVISIBLE);
                c3.setVisibility(View.INVISIBLE);
                c_in.setVisibility(View.VISIBLE);
                c_mid.setVisibility(View.VISIBLE);
                //
                currentZoom = zoomStates.zoomMid;
                // reenable zoom out
                z_out.setEnabled(true);
                break;
        }
    }

    public void onZoomOut() {
        switch(currentZoom) {
            case zoomOutMax:
                break;
            case zoomMid:
                // change visibility
                c_out.setVisibility(View.VISIBLE);
                c2.setVisibility(View.VISIBLE);
                c3.setVisibility(View.VISIBLE);
                c_in.setVisibility(View.VISIBLE);
                c_mid.setVisibility(View.INVISIBLE);
                // for
                currentZoom = zoomStates.zoomOutMax;
                // disable zoom out
                z_out.setEnabled(false);
                break;
            case zoomInMax:
                // change visibility
                c_out.setVisibility(View.VISIBLE);
                c2.setVisibility(View.INVISIBLE);
                c3.setVisibility(View.INVISIBLE);
                c_in.setVisibility(View.VISIBLE);
                c_mid.setVisibility(View.VISIBLE);
                //
                currentZoom = zoomStates.zoomMid;
                // reenable zoom in
                z_in.setEnabled(true);
                break;
        }
    }

}
