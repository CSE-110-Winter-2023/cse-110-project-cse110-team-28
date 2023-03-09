package com.example.myapplication;

import android.util.Pair;

public class LayoutHandler {

    int pixelWidth = 411;
    int pixelHeight = 731;
    int middle_X = 430;
    int middle_Y = 810;

    int radius = 222;

     public int x_coordinate(float angle) {

        return (int)(Math.cos(((angle-90)*Math.PI)/180) * radius + middle_X);

    }
    public int y_coordinate(float angle) {

        return (int)(Math.sin(((angle-90)*Math.PI)/180) * radius + middle_Y);

    }

}
