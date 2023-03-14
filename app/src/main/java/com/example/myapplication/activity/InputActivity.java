package com.example.myapplication.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.example.myapplication.AlertUtil;
import com.example.myapplication.R;

import java.util.UUID;

public class InputActivity extends AppCompatActivity {
    private String user_name_string;
    private String user_UUID;
    private String private_code;
    private String custom_server;
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);

        // Load shared preferences
        this.preferences = getSharedPreferences("saved_data", MODE_PRIVATE);
        this.user_name_string = preferences.getString("user_name", "");
        this.user_UUID = preferences.getString("user_UUID", "");
        this.private_code = preferences.getString("private_code", "");
        this.custom_server = preferences.getString("custom_server", "https://socialcompass.goto.ucsd.edu/");

        EditText user_name_text = findViewById(R.id.user_name);
        EditText custom_server_text = findViewById(R.id.custom_server);

        // Load saved data into the textview
        user_name_text.setText(user_name_string);
        custom_server_text.setText(custom_server);


    }

    public void onSubmitClicked(View view) {
        // Data validation - maybe we want this in Utilities instead?
        EditText user_name = findViewById(R.id.user_name);
        EditText custom_server_text = findViewById(R.id.custom_server);

        this.custom_server = custom_server_text.getText().toString();
        this.user_name_string = user_name.getText().toString();

        if (user_name_string.equals("")) {
            AlertUtil.showAlert(this, "Please enter a name!");
            return;
        }
        // If no UUID present, generate a new one.
        if (user_UUID.equals("")) {
            user_UUID = UUID.randomUUID().toString();
        }
        // Same for private code
        if (private_code.equals("")) {
            private_code = UUID.randomUUID().toString(); // TODO TEST THIS - make sure it's not equal to public code
        }

        saveCustomServer();

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

    private void saveCustomServer(){
        SharedPreferences.Editor editor = this.preferences.edit();

        if (this.custom_server.equals("")){
            editor.putString("custom_server", "https://socialcompass.goto.ucsd.edu/");
            Log.e("Saved", "https://socialcompass.goto.ucsd.edu/");
        } else {
            editor.putString("custom_server", this.custom_server);
            Log.e("Saved", this.custom_server);
        }

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