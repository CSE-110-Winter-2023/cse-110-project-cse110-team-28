package com.example.myapplication.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.myapplication.AlertUtil;
import com.example.myapplication.R;

import java.util.UUID;

public class InputActivity extends AppCompatActivity {
    private float set_orientation;
    private String user_name_string;
    private String user_UUID;
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);

        // Load shared preferences
        this.preferences = getSharedPreferences("saved_data", MODE_PRIVATE);
        this.user_name_string = preferences.getString("user_name", "");
        this.user_UUID = preferences.getString("user_UUID", "");
        this.set_orientation = preferences.getFloat("set_orientation", 360);

        EditText set_orient_text = findViewById(R.id.set_orient);
        EditText user_name_text = findViewById(R.id.user_name);

        // Load saved data into the textview
        user_name_text.setText(user_name_string);

        if (set_orientation != 360) {
            // Same, only populate if set
            set_orient_text.setText(Float.toString(set_orientation));
        }

    }

    public void onSubmitClicked(View view) {
        // Data validation - maybe we want this in Utilities instead?
        EditText set_orient_view = findViewById(R.id.set_orient);
        EditText user_name = findViewById(R.id.user_name);

        String set_orient_string = set_orient_view.getText().toString();
        this.user_name_string = user_name.getText().toString();

        try {
            if (!set_orient_string.equals(""))
                set_orientation = Float.parseFloat(set_orient_string);
        } catch (NumberFormatException e) {
            AlertUtil.showAlert(this, "Please enter valid orientation!");
            return;
        }

        if (!set_orient_string.equals("") && (set_orientation < 0 || set_orientation >= 360)) {
            AlertUtil.showAlert(this, "Please ensure -0 <= Orientation < 360");
            return;
        }

        if (user_name_string.equals("")) {
            AlertUtil.showAlert(this, "Please enter a name!");
            return;
        }
        // If no UUID present, generate a new one.
        if (user_UUID.equals("")) {
            user_UUID = UUID.randomUUID().toString();
        }

        saveSetOrientation(!set_orient_string.equals(""));

        saveProfile();
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        saveProfile();
    }

    private void saveProfile(){
        SharedPreferences.Editor editor = this.preferences.edit();

        editor.putString("user_name", user_name_string);
        editor.putString("user_UUID", user_UUID);
        editor.apply();
    }

    private void saveSetOrientation(boolean set){
        SharedPreferences.Editor editor = this.preferences.edit();

        if (!set)
            editor.putFloat("set_orientation", 360);
        else
            editor.putFloat("set_orientation", this.set_orientation);
        editor.apply();
    }

    // FOR TESTING ONLY. Clear the UUID.
    public void clearUUID() {
        this.user_UUID = "";
        SharedPreferences.Editor editor = this.preferences.edit();
        editor.putString("user_UUID", "");
        editor.apply();
    }


}