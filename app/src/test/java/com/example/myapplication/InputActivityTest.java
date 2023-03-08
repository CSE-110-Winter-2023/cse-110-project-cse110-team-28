package com.example.myapplication;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Button;
import android.widget.TextView;

import androidx.lifecycle.Lifecycle;
import androidx.test.core.app.ActivityScenario;

import com.example.myapplication.activity.InputActivity;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

@RunWith(RobolectricTestRunner.class)
public class InputActivityTest {

    @Test
    public void test_name_saved() {
        var scenario = ActivityScenario.launch(InputActivity.class);
        scenario.moveToState(Lifecycle.State.CREATED);
        scenario.moveToState(Lifecycle.State.STARTED);

        String input_name = "Marcus";
        scenario.onActivity(activity -> {

            TextView user_name_view = activity.findViewById(R.id.user_name);
            user_name_view.setText(input_name);

            Button submit_btn = activity.findViewById(R.id.submit_button);
            submit_btn.performClick();

            SharedPreferences pref = activity.getSharedPreferences("saved_data", Context.MODE_PRIVATE);
            String saved_name = pref.getString("user_name", "");

            assertEquals(saved_name, input_name);
        });
    }

    @Test
    public void test_UUID_creation() {
        var scenario = ActivityScenario.launch(InputActivity.class);
        scenario.moveToState(Lifecycle.State.CREATED);
        scenario.moveToState(Lifecycle.State.STARTED);

        String input_name = "Julia";
        scenario.onActivity(activity -> {
            SharedPreferences pref = activity.getSharedPreferences("saved_data", Context.MODE_PRIVATE);
            // First clear UUID and make sure that it has been cleared
            activity.clearUUID();
            String saved_UUID = pref.getString("user_UUID", "");
            assertEquals("", saved_UUID);

            TextView user_name_view = activity.findViewById(R.id.user_name);
            user_name_view.setText(input_name);

            Button submit_btn = activity.findViewById(R.id.submit_button);
            submit_btn.performClick();

            // Check that a UUID has been generated.
            saved_UUID = pref.getString("user_UUID", "");
            assertNotEquals("", saved_UUID);
        });

        // Close activity, and reopen.
        scenario.moveToState(Lifecycle.State.DESTROYED);

        var scenario2 = ActivityScenario.launch(InputActivity.class);
        scenario2.moveToState(Lifecycle.State.CREATED);
        scenario2.moveToState(Lifecycle.State.STARTED);
        scenario2.onActivity(activity -> {
            // Retrieve the original UUID
            SharedPreferences pref = activity.getSharedPreferences("saved_data", Context.MODE_PRIVATE);
            String old_UUID = pref.getString("user_UUID", "");
            assertNotEquals("", old_UUID); // Check something has been saved

            // Input name again.
            TextView user_name_view = activity.findViewById(R.id.user_name);
            user_name_view.setText(input_name);

            Button submit_btn = activity.findViewById(R.id.submit_button);
            submit_btn.performClick();

            // Check that UUID was not changed
            String new_UUID = pref.getString("user_UUID", "");
            assertEquals(old_UUID, new_UUID);
        });
    }
}
