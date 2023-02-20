package com.example.myapplication;

public class CoordinateUtil {

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
        if (orientation >= -247.5)
            return "SE";
        if (orientation >= -292.5)
            return "E";
        if (orientation >= -337.5)
            return "NE";

        return "ERROR";

    }
    public static float directionBetweenPoints(double point1lat, float point2lat, double point1long, float point2long) {
        if (point1lat == point2lat && point1long == point2long)
            return (float) 0.0;
        //using arctan2 function to find angle between parent and you, then converting it to degrees
        float angle = (float) ((float) Math.atan2(point2lat-point1lat, point2long-point1long)*180/Math.PI);
        //Subtracting 90 to account for offset with 0 being north
        angle = 90 - angle;
        //call cardDirection to return direction as string
        return angle;
    }
}
