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

    @Test
    public void oneMileAwayTest(){
        float lat1 = (float)10.0;
        float long1 = (float)11.0;
        float lat2 = (float)10.0144;
        float long2 = (float)11.0;
        assertEquals(1.0, CoordinateUtil.distanceBetweenPoints(lat1,lat2, long1, long2), 0.1);
        assertEquals(1.0, CoordinateUtil.distanceBetweenPoints(lat2,lat1, long2, long1), 0.1);
        assertEquals(1.0, CoordinateUtil.distanceBetweenPoints(-lat1,-lat2, -long1, -long2), 0.1);
        assertEquals(1.0, CoordinateUtil.distanceBetweenPoints(lat1,lat2, -long1, -long2), 0.1);
        assertEquals(1.0, CoordinateUtil.distanceBetweenPoints(-lat1,-lat2, long1, long2), 0.1);
    }

    @Test
    public void tenMilesAwayTest(){
        float lat1 = (float)10.0;
        float long1 = (float)11.0;
        float lat2 = (float)10.1448;
        float long2 = (float)11.0;
        assertEquals(10.0, CoordinateUtil.distanceBetweenPoints(lat1,lat2, long1, long2), 0.1);
        assertEquals(10.0, CoordinateUtil.distanceBetweenPoints(lat2,lat1, long2, long1), 0.1);
        assertEquals(10.0, CoordinateUtil.distanceBetweenPoints(-lat1,-lat2, -long1, -long2), 0.1);
        assertEquals(10.0, CoordinateUtil.distanceBetweenPoints(lat1,lat2, -long1, -long2), 0.1);
        assertEquals(10.0, CoordinateUtil.distanceBetweenPoints(-lat1,-lat2, long1, long2), 0.1);
    }

    @Test
    public void fiveHundredMilesAwayTest(){
        float lat1 = (float)10.0;
        float long1 = (float)11.0;
        float lat2 = (float)15.0;
        float long2 = (float)16.368;
        assertEquals(500.0, CoordinateUtil.distanceBetweenPoints(lat1,lat2, long1, long2), 0.1);
        assertEquals(500.0, CoordinateUtil.distanceBetweenPoints(lat2,lat1, long2, long1), 0.1);
        assertEquals(500.0, CoordinateUtil.distanceBetweenPoints(-lat1,-lat2, -long1, -long2), 0.1);
        assertEquals(500.0, CoordinateUtil.distanceBetweenPoints(lat1,lat2, -long1, -long2), 0.1);
        assertEquals(500.0, CoordinateUtil.distanceBetweenPoints(-lat1,-lat2, long1, long2), 0.1);
    }

    @Test
    public void randomTest1(){
        float lat1 = (float)-57.39485;
        float long1 = (float)47.0;
        float lat2 = (float)23.3524;
        float long2 = (float)-84.203;
        assertEquals(9064.5, CoordinateUtil.distanceBetweenPoints(lat1,lat2, long1, long2), 0.1);
        assertEquals(9064.5, CoordinateUtil.distanceBetweenPoints(lat2,lat1, long2, long1), 0.1);
        assertEquals(9064.5, CoordinateUtil.distanceBetweenPoints(-lat1,-lat2, -long1, -long2), 0.1);
        assertEquals(9064.5, CoordinateUtil.distanceBetweenPoints(lat1,lat2, -long1, -long2), 0.1);
        assertEquals(9064.5, CoordinateUtil.distanceBetweenPoints(-lat1,-lat2, long1, long2), 0.1);
    }

    @Test
    public void allNegsTest(){
        float lat1 = (float)-47.294;
        float long1 = (float)-48.293857574;
        float lat2 = (float)-28.384748;
        float long2 = (float)-89.98472398;
        assertEquals(2579.0, CoordinateUtil.distanceBetweenPoints(lat1,lat2, long1, long2), 0.1);
        assertEquals(2579.0, CoordinateUtil.distanceBetweenPoints(lat2,lat1, long2, long1), 0.1);
        assertEquals(2579.0, CoordinateUtil.distanceBetweenPoints(-lat1,-lat2, -long1, -long2), 0.1);
        assertEquals(2579.0, CoordinateUtil.distanceBetweenPoints(lat1,lat2, -long1, -long2), 0.1);
        assertEquals(2579.0, CoordinateUtil.distanceBetweenPoints(-lat1,-lat2, long1, long2), 0.1);
    }

    @Test
    public void randomTest2(){
        float lat1 = (float)90;
        float long1 = (float)90;
        float lat2 = (float)-90;
        float long2 = (float)-90;
        assertEquals(12428.1, CoordinateUtil.distanceBetweenPoints(lat1,lat2, long1, long2), 0.1);
        assertEquals(12428.1, CoordinateUtil.distanceBetweenPoints(lat2,lat1, long2, long1), 0.1);
        assertEquals(12428.1, CoordinateUtil.distanceBetweenPoints(-lat1,-lat2, -long1, -long2), 0.1);
        assertEquals(12428.1, CoordinateUtil.distanceBetweenPoints(lat1,lat2, -long1, -long2), 0.1);
        assertEquals(12428.1, CoordinateUtil.distanceBetweenPoints(-lat1,-lat2, long1, long2), 0.1);
    }

    @Test
    public void randomTest3(){
        float lat1 = (float)68.57687;
        float long1 = (float)63.98786675675;
        float lat2 = (float)78.753655;
        float long2 = (float)36.5698987;
        assertEquals(863.6, CoordinateUtil.distanceBetweenPoints(lat1,lat2, long1, long2), 0.1);
        assertEquals(863.6, CoordinateUtil.distanceBetweenPoints(lat2,lat1, long2, long1), 0.1);
        assertEquals(863.6, CoordinateUtil.distanceBetweenPoints(-lat1,-lat2, -long1, -long2), 0.1);
        assertEquals(863.6, CoordinateUtil.distanceBetweenPoints(lat1,lat2, -long1, -long2), 0.1);
        assertEquals(863.6, CoordinateUtil.distanceBetweenPoints(-lat1,-lat2, long1, long2), 0.1);
    }

    //no need to test for invalid coordinates, as that is checked before this function is ever called


}
