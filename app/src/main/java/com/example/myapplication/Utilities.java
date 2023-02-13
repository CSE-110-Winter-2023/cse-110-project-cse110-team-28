package com.example.myapplication;

import android.app.Activity;
import android.app.AlertDialog;

public class Utilities {
    public static void showAlert(Activity activity, String message) {
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(activity);

        alertBuilder
                .setTitle("Alert!")
                .setMessage(message)
                .setPositiveButton( "Ok", (dialog, id) -> {
                    dialog.cancel();
                })
                .setCancelable(true);

        AlertDialog alertDialog = alertBuilder.create();
        alertDialog.show();
    }

    public static String cardDirection(float orientation) {

        if (orientation >= 0) {
            if (orientation > 337.5 || orientation <= 22.5)
                return "N";
            if (orientation <= 67.5)
                return "NE";
            if (orientation <= 112.5)
                return "E";
            if (orientation <= 157.5)
                return "SE";
            if (orientation <= 202.5)
                return "S";
            if (orientation <= 247.5)
                return "SW";
            if (orientation <= 292.5)
                return "W";
            if (orientation <= 337.5)
                return "NW";
        }

        if (orientation < -337.5 || orientation >= -22.5)
            return "N";
        if (orientation >= -67.5)
            return "NW";
        if (orientation >= -112.5)
            return "W";
        if (orientation >= -157.5)
            return "SW";
        if (orientation >= -202.5)
            return "S";
        if (orientation <= -247.5)
            return "SE";
        if (orientation <= -292.5)
            return "E";
        if (orientation <= -337.5)
            return "NE";

        return "ERROR";

    }

}
