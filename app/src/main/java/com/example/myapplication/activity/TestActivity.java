package com.example.myapplication.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.location_data.LocationDatabase;
import com.example.myapplication.location_data.LocationRepository;

public class TestActivity extends AppCompatActivity {

    private LocationRepository repo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        var context = getApplicationContext();
        var db = LocationDatabase.provide(context);
        var dao = db.getDao();
        this.repo = new LocationRepository(dao);

        TextView get_text = findViewById(R.id.get_text);
        var get = repo.getRemote("482bb86e-7ede-48b7-a04a-5fd4da224efb");
        var get_value = get.getValue();
        // get_text.setText(get_value.toJSON());

    }
}