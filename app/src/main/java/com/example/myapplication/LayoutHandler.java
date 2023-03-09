package com.example.myapplication;

import android.util.Pair;

public class LayoutHandler {

    int pixelWidth = 411;
    int pixelHeight = 731;
    int middle_X = pixelWidth/2;
    int middle_Y = pixelHeight/2;

    int radius = 205;

     Pair<Integer, Integer> angleToCoordinate(float angle) {

        return new Pair<>((int) (Math.cos(angle) * radius + middle_X),
                (int) (Math.sin(angle) * radius + middle_Y));

    }

}
