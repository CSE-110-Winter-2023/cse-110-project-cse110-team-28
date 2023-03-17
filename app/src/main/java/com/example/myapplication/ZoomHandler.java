package com.example.myapplication;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

enum zoomStates {
        zoomOutMax,
        zoomMid1,
        zoomMid2,
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
        currentZoom = zoomStates.zoomMid1;
        onZoomIn();

    }


    /*
    * 1. 0-1 mile
    * 2. 1-10 miles
    * 3. 10-500 miles
    * 4. 500+ miles
    * */
    public float getRadius(float dist) {
        int[] maxes = {0, 1, 10, 500};
        double radius;
        int divisions;
        float percentage_in_division;
        float max_rad = 450; // don't know what it is rn
        float division_rad;
        int max_dist;
        switch(currentZoom) {
            case zoomInMax:
                divisions = 1;
                max_dist = 1;
                // conversion max dist --> radius is

                percentage_in_division = dist / max_dist;

                if (dist >= max_dist) {
                    return max_rad;
                }

                return max_rad * percentage_in_division;
            case zoomMid2:
                divisions = 2;
                max_dist = 10;

                if (dist >= max_dist) {
                    return max_rad;
                }

                division_rad = max_rad / divisions;

                if (dist <= 1) {
                    percentage_in_division = dist;
                    return division_rad * percentage_in_division;
                }

                percentage_in_division = (dist - 1) / (max_dist - 1);
                return division_rad * (1 + percentage_in_division);
              //  break;
            case zoomMid1:
                divisions = 3;
                max_dist = 500;


                if (dist >= max_dist) {
                    return max_rad;
                }

                division_rad = max_rad / divisions;

                if (dist <= 1) {
                    percentage_in_division = dist;
                    return division_rad * percentage_in_division;
                }
                if (dist <= 10) {
                    percentage_in_division = (dist - 1) / (10 - 1);
                    return division_rad * (1 + percentage_in_division);
                }
                percentage_in_division = (dist - 10) / (max_dist - 10);
                return division_rad * (2 + percentage_in_division);
              //  break;
            case zoomOutMax:
                divisions = 4;
                max_dist = 12500;

                if (dist >= max_dist) {
                    return max_rad;
                }

                division_rad = max_rad / divisions;

                if (dist <= 1) {
                    percentage_in_division = dist;
                    return division_rad * percentage_in_division;
                }


                if (dist <= 10) {
                    percentage_in_division = (dist - 1) / (10 - 1);
                    return division_rad * (1 + percentage_in_division);
                }

                if (dist <= 500) {
                    percentage_in_division = (dist - 10) / (500 - 10);
                    return division_rad * (2 + percentage_in_division);
                }

                percentage_in_division = (dist - 500) / (max_dist - 500);

                return division_rad * (3 + percentage_in_division);

                //break;
            default:
                max_dist = 0;
                divisions = 0;
        }

        if (dist >= max_dist) {
            return max_rad;
        }



        division_rad = max_rad / divisions;

//        for (int i = 1; i< divisions; i++) {
//            if (dist < maxes[i]) {
//                float amountInInterval = dist - maxes[i-1];
//                percentage_in_division =  amountInInterval / (maxes[i] - maxes[i-1]);
//                return division_rad * (i + percentage_in_division);
//            }
//        }

        return 0f;
    }

    public void onZoomIn() {

        switch(currentZoom) {
            case zoomInMax:
                break;
            case zoomMid2:
                // change visibility
                c_out.setVisibility(View.VISIBLE);
                c2.setVisibility(View.INVISIBLE);
                c3.setVisibility(View.INVISIBLE);
                c_in.setVisibility(View.INVISIBLE);
                c_mid.setVisibility(View.INVISIBLE);
                // for
                currentZoom = zoomStates.zoomInMax;
                // disable zoom in
                z_in.setEnabled(false);

                break;
            case zoomMid1:
                // change visibility
                c_out.setVisibility(View.VISIBLE);
                c2.setVisibility(View.INVISIBLE);
                c3.setVisibility(View.VISIBLE);
                c_in.setVisibility(View.INVISIBLE);
                c_mid.setVisibility(View.INVISIBLE);
                // for
                currentZoom = zoomStates.zoomMid2;
                break;
            case zoomOutMax:
                // change visibility
                c_out.setVisibility(View.VISIBLE);
                c2.setVisibility(View.INVISIBLE);
                c3.setVisibility(View.INVISIBLE);
                c_in.setVisibility(View.VISIBLE);
                c_mid.setVisibility(View.VISIBLE);
                //
                currentZoom = zoomStates.zoomMid1;
                // reenable zoom out
                z_out.setEnabled(true);
                break;
        }
    }

    public void onZoomOut() {
        switch(currentZoom) {
            case zoomOutMax:
                break;
            case zoomMid1:
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
            case zoomMid2:
                // change visibility
                c_out.setVisibility(View.VISIBLE);
                c2.setVisibility(View.INVISIBLE);
                c3.setVisibility(View.INVISIBLE);
                c_in.setVisibility(View.VISIBLE);
                c_mid.setVisibility(View.VISIBLE);
                // for
                currentZoom = zoomStates.zoomMid1;
                break;
            case zoomInMax:
                // change visibility
                c_out.setVisibility(View.VISIBLE);
                c2.setVisibility(View.INVISIBLE);
                c3.setVisibility(View.VISIBLE);
                c_in.setVisibility(View.INVISIBLE);
                c_mid.setVisibility(View.INVISIBLE);
                //
                currentZoom = zoomStates.zoomMid2;
                // reenable zoom in
                z_in.setEnabled(true);
                break;
        }
    }

}
