package com.example.myapplication;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.fail;

import android.util.Log;

import com.example.myapplication.location_data.LocationAPI;
import com.example.myapplication.location_data.LocationData;

import org.junit.Before;
import org.junit.Test;

import java.time.Instant;
import java.util.Random;

public class LocationAPITest {
    LocationAPI api = LocationAPI.provide();
    LocationData test_loc = new LocationData("test_28", "Team 28 test", 10f, 10f, false);
    Instant created = Instant.now();

    @Before
    public void beforeTest(){
        test_loc.private_code = "123-321-456";

        api.put(test_loc);
    }

    @Test
    public void testPut(){
        // Put
        assertNotNull(api.put(test_loc));
        Instant instant = Instant.now();

        // Check that remote has been updated after put
        var got = api.get(test_loc.public_code);
        assertNotNull(got);

        assertEquals(test_loc.public_code, got.public_code);
        assertEquals(test_loc.is_listed_publicly, got.is_listed_publicly);
        assertEquals(test_loc.latitude, got.latitude, 0D);
        assertEquals(test_loc.longitude, got.longitude, 0D);
        assertEquals(test_loc.label, got.label);
        // Do we want to check timestamps??

        // Delete
        assertNotNull(api.delete(test_loc));

        // It is gone.
        assertNull(api.get(test_loc.public_code));

        // Put again
        assertNotNull(api.put(test_loc));

        // Check that remote has been updated after put
        got = api.get(test_loc.public_code);
        assertNotNull(got);

        assertEquals(test_loc.public_code, got.public_code);
        assertEquals(test_loc.is_listed_publicly, got.is_listed_publicly);
        assertEquals(test_loc.latitude, got.latitude, 0D);
        assertEquals(test_loc.longitude, got.longitude, 0D);
        assertEquals(test_loc.label, got.label);

        // Test a put that changes values? or is that just a patch lol.
    }
    @Test
    public void testGet() {
        // Get a value that does not exist - will throw and catch an exception, and return null.
        assertNull(api.get("This should not exist"));

        var got = api.get(test_loc.public_code);

        assertEquals(test_loc.public_code, got.public_code);
        assertEquals(test_loc.latitude, got.latitude, 0D);
        assertEquals(test_loc.longitude, got.longitude, 0D);
    }

    @Test
    public void testDel(){
        // Delete
        String back = api.delete(test_loc);

        // Check that get returns null
        assertNull(api.get(test_loc.public_code));

        // Try to delete again (Shouldn't exist - return null

        assertNull(api.delete(test_loc));
    }

    @Test
    public void testPatch(){
        test_loc.longitude = 50f;
        test_loc.latitude = 50f;

        // Try to patch
        assertNotNull(api.patch(test_loc));
        var got = api.get(test_loc.public_code);
        assertNotNull(got);

        // Check values are equal - other than updated long and lat
        assertEquals(test_loc.public_code, got.public_code);
        assertEquals(test_loc.is_listed_publicly, got.is_listed_publicly);
        assertEquals(50f, got.latitude, 0D);
        assertEquals(50f, got.longitude, 0D);
        assertEquals(test_loc.label, got.label);

    }


}
