package com.example.myapplication.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.myapplication.AlertUtil;
import com.example.myapplication.R;
import com.example.myapplication.location_data.LocationData;
import com.example.myapplication.location_data.LocationDataDao;
import com.example.myapplication.location_data.LocationDatabase;

public class AddFriendActivity extends AppCompatActivity {
    private LocationDatabase db = LocationDatabase.provide(this);
    private LocationDataDao dao = LocationDatabase.instance.getDao();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_friend);
    }

    public void onSubmitButtonClicked(View view) {
        // Data validation
        EditText uuid_text = findViewById(R.id.friend_UUID);
        EditText name_text = findViewById(R.id.friend_name);

        String name = name_text.getText().toString();
        String uuid = uuid_text.getText().toString();

        if(name.equals("")) {
            AlertUtil.showAlert(this, "Please enter a name!");
            return;
        }
        if (uuid.equals("")) {
            AlertUtil.showAlert(this, "Please enter a UUID!");
            return;
        }

        // Add a friend and leave
        LocationData newFriend = new LocationData(uuid, name, -361f, -361f, false);
        dao.upsert(newFriend);

        finish();
    }
}