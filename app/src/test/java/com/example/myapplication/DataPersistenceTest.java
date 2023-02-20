package com.example.myapplication;

import static org.junit.Assert.assertEquals;

import android.widget.EditText;
import android.widget.TextView;

import androidx.lifecycle.Lifecycle;
import androidx.test.core.app.ActivityScenario;

import com.example.myapplication.MainActivity;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

@RunWith(RobolectricTestRunner.class)
public class DataPersistenceTest{
    @Test
    public void test_one_plus_one_equals_two()
    {
        var scenario = ActivityScenario.launch(MainActivity.class);
        scenario.onActivity(activity -> {
            EditText parentLabel = activity.findViewById(R.id.label_parents);


        });
    }
}