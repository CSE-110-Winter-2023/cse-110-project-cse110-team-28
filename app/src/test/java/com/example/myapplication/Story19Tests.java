package com.example.myapplication;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import com.example.myapplication.location_data.LocationAPI;
import com.example.myapplication.location_data.LocationData;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import java.time.Instant;

public class Story19Tests {
    String default_url = "https://socialcompass.goto.ucsd.edu/";
    String new_url = "https://sc2.ucsd.edu";

    @Before
    public void beforeTest() {

    }

    @Test
    public void testDefault() {
        LocationAPI api = LocationAPI.provide();
        assertEquals(api.url_begin, default_url);
    }

    @Test
    public void testCustom() {
        LocationAPI api = LocationAPI.provide(new_url);
        assertEquals(api.url_begin, new_url);
    }

    @Test
    public void testChanged() {
        // default
        LocationAPI api = LocationAPI.provide();
        assertEquals(api.url_begin, default_url);

        // change url
        LocationAPI.provide(new_url);
        assertEquals(api.url_begin, new_url);
    }
};