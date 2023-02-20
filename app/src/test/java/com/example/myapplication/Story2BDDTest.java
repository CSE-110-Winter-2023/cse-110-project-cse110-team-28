package com.example.myapplication;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class Story2BDDTest {
    /**
     * GIVEN My parent's house is at 40, -122
     * AND I am at google HQ(37, -122)
     * WHEN I load the main screen
     * THEN my parent's house should be North of me
     * WHEN I move 3 degrees east to 37, -119
     * THEN my parent's house should change to Northwest of me
     */
    @Test
    public void BDD2Test() {
        float parentsLat = 40, parentsLong = -122;
        float myLat = 37, myLong = -122;
        assertEquals("N", coordinateUtil.directionBetweenPoints(myLat,parentsLat,myLong,parentsLong));
        myLong = -119;
        assertEquals("NW", coordinateUtil.directionBetweenPoints(myLat,parentsLat,myLong,parentsLong));
    }
}
