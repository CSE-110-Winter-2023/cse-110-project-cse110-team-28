package com.example.myapplication;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
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
    LocationAPI api = new LocationAPI().provide();
    LocationData test_loc = new LocationData("test_28", "Team 28 test", 10f, 10f, false);
    Instant created = Instant.now();

    @Before
    public void beforeTest(){
        test_loc.private_code = "123-321-456";
    }

    @Test
    public void testGet() {
        // Get a value that does not exist


        /* TODO EXCEPTION IS THROWN BUT NOT CAUGHT WHY
        assertThrows(RuntimeException.class, () -> {
            var got = api.get("This should not exist");
        });*/


        var got = api.get(test_loc.public_code);

        assertEquals(test_loc.public_code, got.public_code);
        assertEquals(test_loc.latitude, got.latitude, 0D);
        assertEquals(test_loc.longitude, got.longitude, 0D);
    }

    @Test
    public void testDel(){
        // Delete
        api.delete(test_loc);

        // Check that don't get remote
        try {
            api.get(test_loc.public_code);
            fail();
        } catch (RuntimeException e) {
            // Exception successfully caught
        }

        // Try to delete again (Shouldn't exist - throw exception)
        try {
            api.delete(test_loc);
            fail();
        } catch (RuntimeException e) {
            // Do nothing, pass test
        }
    }

    /*
    public void testPut(){
        // TODO Make sure it doesn't exist before

        // Put
        api.put(test_loc);
        Instant instant = Instant.now();

        // Not sure if timestamps will match lol
        // Check that remote has been updated after put
        try {
            var got = api.get(test_loc.public_code);
        } catch (RuntimeException e) {
            // Should not have exception. Fail.
            fail();
        }
    }*/


}
