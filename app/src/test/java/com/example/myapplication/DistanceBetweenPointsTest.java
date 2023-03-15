package com.example.myapplication;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

public class DistanceBetweenPointsTest {

    /**
     * Testing to make sure that the distance between points is correct using j-unit tests
     */
    @Test
    public void samePointTest(){
        float lat1 = (float)30;
        float long1 = (float)50;
        float lat2 = (float)30;
        float long2 = (float)50;
        assertEquals(0.0, CoordinateUtil.distanceBetweenPoints(lat1,lat2, long1, long2), 0.0f);
        assertEquals(0.0, CoordinateUtil.distanceBetweenPoints(lat2,lat1, long2, long1), 0.0f);
        assertEquals(0.0, CoordinateUtil.distanceBetweenPoints(-lat1,-lat2, -long1, -long2), 0.0f);
        assertEquals(0.0, CoordinateUtil.distanceBetweenPoints(lat1,lat2, -long1, -long2), 0.0f);
        assertEquals(0.0, CoordinateUtil.distanceBetweenPoints(-lat1,-lat2, long1, long2), 0.0f);
    }

    @Test
    public void farthestPointsTest(){
        float lat1 = (float)90;
        float long1 = (float)0.0;
        float lat2 = (float)-90;
        float long2 = (float)0.0;
        assertEquals(12436.8, CoordinateUtil.distanceBetweenPoints(lat1,lat2, long1, long2), 10);
        assertEquals(12436.8, CoordinateUtil.distanceBetweenPoints(lat2,lat1, long2, long1), 10);
        assertEquals(12436.8, CoordinateUtil.distanceBetweenPoints(-lat1,-lat2, -long1, -long2), 10);
        assertEquals(12436.8, CoordinateUtil.distanceBetweenPoints(lat1,lat2, -long1, -long2), 10);
        assertEquals(12436.8, CoordinateUtil.distanceBetweenPoints(-lat1,-lat2, long1, long2), 10);
    }

    @Test
    public void normalPointsTest(){
        float lat1 = (float)10.0;
        float long1 = (float)10.0;
        float lat2 = (float)11.0;
        float long2 = (float)11.0;
        assertEquals(96.89, CoordinateUtil.distanceBetweenPoints(lat1,lat2, long1, long2), 0.1);
        assertEquals(96.89, CoordinateUtil.distanceBetweenPoints(lat2,lat1, long2, long1), 0.1);
        assertEquals(96.89, CoordinateUtil.distanceBetweenPoints(-lat1,-lat2, -long1, -long2), 0.1);
        assertEquals(96.89, CoordinateUtil.distanceBetweenPoints(lat1,lat2, -long1, -long2), 0.1);
        assertEquals(96.89, CoordinateUtil.distanceBetweenPoints(-lat1,-lat2, long1, long2), 0.1);
    }


}
