package com.example.myapplication;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void cardDirection_North() {
        assertNotEquals("N", CoordinateUtil.cardDirection((float)25));
        assertEquals("N", CoordinateUtil.cardDirection((float)-22.5));
        assertEquals("N", CoordinateUtil.cardDirection(0));
        assertEquals("N", CoordinateUtil.cardDirection(10));
    }
    @Test
    public void cardDirection_South() {
        assertNotEquals("S", CoordinateUtil.cardDirection((float)0));
        assertEquals("S", CoordinateUtil.cardDirection((float)-170));
        assertEquals("S", CoordinateUtil.cardDirection((float)190));
        assertEquals("S", CoordinateUtil.cardDirection((float)-195));
    }

    @Test
    public void cardDirection_West() {
        assertNotEquals("W", CoordinateUtil.cardDirection((float)0));
        assertEquals("W", CoordinateUtil.cardDirection((float)270));
        assertEquals("W", CoordinateUtil.cardDirection((float)-90));
        assertEquals("W", CoordinateUtil.cardDirection((float)-105));
    }

    @Test
    public void cardDirection_East() {
        assertNotEquals("E", CoordinateUtil.cardDirection((float)0));
        assertEquals("E", CoordinateUtil.cardDirection((float)-270));
        assertEquals("E", CoordinateUtil.cardDirection((float)90));
        assertEquals("E", CoordinateUtil.cardDirection((float)105));
    }

    @Test
    public void cardDirection_NorthWest() {
        assertNotEquals("NW", CoordinateUtil.cardDirection((float)0));
        assertEquals("NW", CoordinateUtil.cardDirection((float)-30));
        assertEquals("NW", CoordinateUtil.cardDirection((float)330));
        assertEquals("NW", CoordinateUtil.cardDirection((float)-25));
    }

    @Test
    public void cardDirection_NorthEast() {
        assertNotEquals("NE", CoordinateUtil.cardDirection((float)0));
        assertEquals("NE", CoordinateUtil.cardDirection((float)30));
        assertEquals("NE", CoordinateUtil.cardDirection((float)-330));
        assertEquals("NE", CoordinateUtil.cardDirection((float)25));
    }

    @Test
    public void cardDirection_SouthEast() {
        assertNotEquals("SE", CoordinateUtil.cardDirection((float)0));
        assertEquals("SE", CoordinateUtil.cardDirection((float)150));
        assertEquals("SE", CoordinateUtil.cardDirection((float)-220));
        assertEquals("SE", CoordinateUtil.cardDirection((float)135));
    }

    @Test
    public void cardDirection_SouthWest() {
        assertNotEquals("SW", CoordinateUtil.cardDirection((float) 0));
        assertEquals("SW", CoordinateUtil.cardDirection((float) -150));
        assertEquals("SW", CoordinateUtil.cardDirection((float) 220));
        assertEquals("SW", CoordinateUtil.cardDirection((float) -135));
    }

}