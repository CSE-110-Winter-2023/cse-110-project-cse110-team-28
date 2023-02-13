package com.example.myapplication;

import org.junit.Test;

import static org.junit.Assert.*;

public class DirectionBetweenPointsTest {

    @Test
    public void northTest() {
        float lat1 = (float)30;
        float long1 = (float)50;
        float lat2 = (float)52;
        float long2 = (float)48;
        assertEquals("N", Utilities.directionBetweenPoints(lat1,lat2,long1,long2));
        assertNotEquals("N", Utilities.directionBetweenPoints(lat2,lat1,long2,long1));
    }

    @Test
    public void southTest() {
        float lat1 = (float)30;
        float long1 = (float)55;
        float lat2 = (float)-10;
        float long2 = (float)55;
        assertEquals("S", Utilities.directionBetweenPoints(lat1,lat2,long1,long2));
        assertNotEquals("S", Utilities.directionBetweenPoints(lat2,lat1,long2,long1));
    }

    @Test
    public void westTest() {
        float lat1 = (float)30;
        float long1 = (float)9;
        float lat2 = (float)20;
        float long2 = (float)108;
        assertEquals("W", Utilities.directionBetweenPoints(lat1,lat2,long1,long2));
        assertNotEquals("W", Utilities.directionBetweenPoints(lat2,lat1,long2,long1));
    }

    @Test
    public void EastTest() {
        float lat1 = (float)0;
        float long1 = (float)50;
        float lat2 = (float)-2;
        float long2 = (float)30;
        assertEquals("E", Utilities.directionBetweenPoints(lat1,lat2,long1,long2));
        assertNotEquals("E", Utilities.directionBetweenPoints(lat2,lat1,long2,long1));
    }

    @Test
    public void northWestTest() {
        float lat1 = (float)30;
        float long1 = (float)50;
        float lat2 = (float)52;
        float long2 = (float)65;
        assertEquals("NW", Utilities.directionBetweenPoints(lat1,lat2,long1,long2));
        assertNotEquals("NW", Utilities.directionBetweenPoints(lat2,lat1,long2,long1));
    }

    @Test
    public void northEastTest() {
        float lat1 = (float)30;
        float long1 = (float)50;
        float lat2 = (float)52;
        float long2 = (float)25;
        assertEquals("NE", Utilities.directionBetweenPoints(lat1,lat2,long1,long2));
        assertNotEquals("NE", Utilities.directionBetweenPoints(lat2,lat1,long2,long1));
    }

    @Test
    public void southEastTest() {
        float lat1 = (float)30;
        float long1 = (float)55;
        float lat2 = (float)-10;
        float long2 = (float)15;
        assertEquals("SE", Utilities.directionBetweenPoints(lat1,lat2,long1,long2));
        assertNotEquals("SE", Utilities.directionBetweenPoints(lat2,lat1,long2,long1));
    }

    @Test
    public void southWestTest() {
        float lat1 = (float)30;
        float long1 = (float)55;
        float lat2 = (float)-10;
        float long2 = (float)85;
        assertEquals("SW", Utilities.directionBetweenPoints(lat1,lat2,long1,long2));
        assertNotEquals("SW", Utilities.directionBetweenPoints(lat2,lat1,long2,long1));
    }

    @Test
    public void SameSpotTest() {
        float lat1 = (float)30;
        float long1 = (float)55;
        float lat2 = lat1;
        float long2 = long1;
        assertEquals("", Utilities.directionBetweenPoints(lat1,lat2,long1,long2));
        assertEquals("", Utilities.directionBetweenPoints(lat2,lat1,long2,long1));
    }
}
