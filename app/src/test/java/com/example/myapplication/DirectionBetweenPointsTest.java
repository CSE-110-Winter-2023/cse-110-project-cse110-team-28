package com.example.myapplication;

import org.junit.Test;

import static org.junit.Assert.*;
//Testing to make sure
public class DirectionBetweenPointsTest {
    /**
     * Testing to make sure that the direction is correct using j-unit tests
     */
    @Test
    public void northTest() {
        float lat1 = (float)30;
        float long1 = (float)50;
        float lat2 = (float)52;
        float long2 = (float)48;
        assertEquals("N", CoordinateUtil.cardDirection(CoordinateUtil.directionBetweenPoints(lat1,lat2,long1,long2)));
        assertNotEquals("N", CoordinateUtil.cardDirection(CoordinateUtil.directionBetweenPoints(lat2,lat1,long2,long1)));
    }

    @Test
    public void southTest() {
        float lat1 = (float)30;
        float long1 = (float)55;
        float lat2 = (float)-10;
        float long2 = (float)55;
        assertEquals("S", CoordinateUtil.cardDirection(CoordinateUtil.directionBetweenPoints(lat1,lat2,long1,long2)));
        assertNotEquals("S", CoordinateUtil.cardDirection(CoordinateUtil.directionBetweenPoints(lat2,lat1,long2,long1)));
    }

    @Test
    public void westTest() {
        float lat1 = (float)20;
        float long1 = (float)108;
        float lat2 = (float)30;
        float long2 = (float)9;
        assertEquals("W", CoordinateUtil.cardDirection(CoordinateUtil.directionBetweenPoints(lat1,lat2,long1,long2)));
        assertNotEquals("W", CoordinateUtil.cardDirection(CoordinateUtil.directionBetweenPoints(lat2,lat1,long2,long1)));
    }

    @Test
    public void EastTest() {
        float lat1 = (float)-2;
        float long1 = (float)30;
        float lat2 = (float)0;
        float long2 = (float)50;
        assertEquals("E", CoordinateUtil.cardDirection(CoordinateUtil.directionBetweenPoints(lat1,lat2,long1,long2)));
        assertNotEquals("E", CoordinateUtil.cardDirection(CoordinateUtil.directionBetweenPoints(lat2,lat1,long2,long1)));
    }

    @Test
    public void northWestTest() {
        float lat1 = (float)30;
        float long1 = (float)65;
        float lat2 = (float)52;
        float long2 = (float)50;
        assertEquals("NW", CoordinateUtil.cardDirection(CoordinateUtil.directionBetweenPoints(lat1,lat2,long1,long2)));
        assertNotEquals("NW", CoordinateUtil.cardDirection(CoordinateUtil.directionBetweenPoints(lat2,lat1,long2,long1)));
    }

    @Test
    public void northEastTest() {
        float lat1 = (float)30;
        float long1 = (float)25;
        float lat2 = (float)52;
        float long2 = (float)50;
        assertEquals("NE", CoordinateUtil.cardDirection(CoordinateUtil.directionBetweenPoints(lat1,lat2,long1,long2)));
        assertNotEquals("NE", CoordinateUtil.cardDirection(CoordinateUtil.directionBetweenPoints(lat2,lat1,long2,long1)));
    }

    @Test
    public void southEastTest() {
        float lat1 = (float)30;
        float long1 = (float)15;
        float lat2 = (float)-10;
        float long2 = (float)55;
        assertEquals("SE", CoordinateUtil.cardDirection(CoordinateUtil.directionBetweenPoints(lat1,lat2,long1,long2)));
        assertNotEquals("SE", CoordinateUtil.cardDirection(CoordinateUtil.directionBetweenPoints(lat2,lat1,long2,long1)));
    }

    @Test
    public void southWestTest() {
        float lat1 = (float)30;
        float long1 = (float)85;
        float lat2 = (float)-10;
        float long2 = (float)55;
        assertEquals("SW", CoordinateUtil.cardDirection(CoordinateUtil.directionBetweenPoints(lat1,lat2,long1,long2)));
        assertNotEquals("SW", CoordinateUtil.cardDirection(CoordinateUtil.directionBetweenPoints(lat2,lat1,long2,long1)));
    }

    @Test
    public void SameSpotTest() {
        float lat1 = (float)30;
        float long1 = (float)55;
        float lat2 = lat1;
        float long2 = long1;
        assertEquals("N", CoordinateUtil.cardDirection(CoordinateUtil.directionBetweenPoints(lat1,lat2,long1,long2)));
        assertEquals("N", CoordinateUtil.cardDirection(CoordinateUtil.directionBetweenPoints(lat2,lat1,long2,long1)));
    }
}
