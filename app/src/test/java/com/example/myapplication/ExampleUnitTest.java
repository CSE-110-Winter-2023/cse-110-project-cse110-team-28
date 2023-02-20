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
        assertNotEquals("N", coordinateUtil.cardDirection((float)25));
        assertEquals("N", coordinateUtil.cardDirection((float)-22.5));
        assertEquals("N", coordinateUtil.cardDirection(0));
        assertEquals("N", coordinateUtil.cardDirection(10));
    }
    @Test
    public void cardDirection_South() {
        assertNotEquals("S", coordinateUtil.cardDirection((float)0));
        assertEquals("S", coordinateUtil.cardDirection((float)-170));
        assertEquals("S", coordinateUtil.cardDirection((float)190));
        assertEquals("S", coordinateUtil.cardDirection((float)-195));
    }

    @Test
    public void cardDirection_West() {
        assertNotEquals("W", coordinateUtil.cardDirection((float)0));
        assertEquals("W", coordinateUtil.cardDirection((float)270));
        assertEquals("W", coordinateUtil.cardDirection((float)-90));
        assertEquals("W", coordinateUtil.cardDirection((float)-105));
    }

    @Test
    public void cardDirection_East() {
        assertNotEquals("E", coordinateUtil.cardDirection((float)0));
        assertEquals("E", coordinateUtil.cardDirection((float)-270));
        assertEquals("E", coordinateUtil.cardDirection((float)90));
        assertEquals("E", coordinateUtil.cardDirection((float)105));
    }

    @Test
    public void cardDirection_NorthWest() {
        assertNotEquals("NW", coordinateUtil.cardDirection((float)0));
        assertEquals("NW", coordinateUtil.cardDirection((float)-30));
        assertEquals("NW", coordinateUtil.cardDirection((float)330));
        assertEquals("NW", coordinateUtil.cardDirection((float)-25));
    }

    @Test
    public void cardDirection_NorthEast() {
        assertNotEquals("NE", coordinateUtil.cardDirection((float)0));
        assertEquals("NE", coordinateUtil.cardDirection((float)30));
        assertEquals("NE", coordinateUtil.cardDirection((float)-330));
        assertEquals("NE", coordinateUtil.cardDirection((float)25));
    }

    @Test
    public void cardDirection_SouthEast() {
        assertNotEquals("SE", coordinateUtil.cardDirection((float)0));
        assertEquals("SE", coordinateUtil.cardDirection((float)150));
        assertEquals("SE", coordinateUtil.cardDirection((float)-220));
        assertEquals("SE", coordinateUtil.cardDirection((float)135));
    }

    @Test
    public void cardDirection_SouthWest() {
        assertNotEquals("SW", coordinateUtil.cardDirection((float) 0));
        assertEquals("SW", coordinateUtil.cardDirection((float) -150));
        assertEquals("SW", coordinateUtil.cardDirection((float) 220));
        assertEquals("SW", coordinateUtil.cardDirection((float) -135));
    }

}