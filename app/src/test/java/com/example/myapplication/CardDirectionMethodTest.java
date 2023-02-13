package com.example.myapplication;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

public class CardDirectionMethodTest {
    @Test
    public void cardDirection_North() {
        assertNotEquals("N", Utilities.cardDirection((float)25));
        assertEquals("N", Utilities.cardDirection((float)-22.5));
        assertEquals("N", Utilities.cardDirection(0));
        assertEquals("N", Utilities.cardDirection(10));
    }

    public void cardDirection_South() {
        assertNotEquals("S", Utilities.cardDirection((float)0));
        assertEquals("S", Utilities.cardDirection((float)-170));
        assertEquals("S", Utilities.cardDirection((float)190));
        assertEquals("S", Utilities.cardDirection((float)-195));
    }

    @Test
    public void cardDirection_West() {
        assertNotEquals("W", Utilities.cardDirection((float)0));
        assertEquals("W", Utilities.cardDirection((float)270));
        assertEquals("W", Utilities.cardDirection((float)-90));
        assertEquals("W", Utilities.cardDirection((float)-105));
    }

    @Test
    public void cardDirection_East() {
        assertNotEquals("E", Utilities.cardDirection((float)0));
        assertEquals("E", Utilities.cardDirection((float)-270));
        assertEquals("E", Utilities.cardDirection((float)90));
        assertEquals("E", Utilities.cardDirection((float)105));
    }

    @Test
    public void cardDirection_NorthWest() {
        assertNotEquals("NW", Utilities.cardDirection((float)0));
        assertEquals("NW", Utilities.cardDirection((float)-30));
        assertEquals("NW", Utilities.cardDirection((float)330));
        assertEquals("NW", Utilities.cardDirection((float)-25));
    }

    @Test
    public void cardDirection_NorthEast() {
        assertNotEquals("NE", Utilities.cardDirection((float)0));
        assertEquals("NE", Utilities.cardDirection((float)30));
        assertEquals("NE", Utilities.cardDirection((float)-330));
        assertEquals("NE", Utilities.cardDirection((float)25));
    }

    @Test
    public void cardDirection_SouthEast() {
        assertNotEquals("SE", Utilities.cardDirection((float)0));
        assertEquals("SE", Utilities.cardDirection((float)150));
        assertEquals("SE", Utilities.cardDirection((float)-220));
        assertEquals("SE", Utilities.cardDirection((float)135));
    }

    @Test
    public void cardDirection_SouthWest() {
        assertNotEquals("SW", Utilities.cardDirection((float) 0));
        assertEquals("SW", Utilities.cardDirection((float) -150));
        assertEquals("SW", Utilities.cardDirection((float) 220));
        assertEquals("SW", Utilities.cardDirection((float) -135));
    }
}
