package com.example.myapplication;

import android.app.Activity;
import android.graphics.Insets;
import android.graphics.Point;
import android.util.DisplayMetrics;
import android.util.Pair;
import android.view.Display;
import android.view.WindowInsets;
import android.view.WindowManager;
import android.view.WindowMetrics;

public class LayoutHandler {



    int pixelWidth = 411;
    int pixelHeight = 731;
    int middle_X = 430;
    int middle_Y = 810;

    int radius = 222;


    public LayoutHandler(){

//        var test = activity.getWindowManager();
//        final WindowMetrics metrics = activity.getWindowManager().getCurrentWindowMetrics();
//        // Gets all excluding insets
//        final WindowInsets windowInsets = metrics.getWindowInsets();
//        Insets insets = windowInsets.getInsetsIgnoringVisibility(WindowInsets.Type.navigationBars()
//                | WindowInsets.Type.displayCutout());
//
//        int Width = insets.right + insets.left;
//        int Height = insets.top + insets.bottom;

    }

     public int x_coordinate(float angle) {



        return (int)(Math.cos(((angle-90)*Math.PI)/180) * radius + middle_X);

    }
    public int y_coordinate(float angle) {

        return (int)(Math.sin(((angle-90)*Math.PI)/180) * radius + middle_Y);

    }

}
