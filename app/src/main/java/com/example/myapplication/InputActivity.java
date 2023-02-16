package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.text.NumberFormat;

public class InputActivity extends AppCompatActivity {

    private String label_parents;
    private float lat_parents;
    private float long_parents;
    private float set_orientation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);
    }

    public void onSubmitClicked(View view) {
        // Data validation - maybe we want this in Utilities instead?
        TextView label_parents_view = findViewById(R.id.label_parents);
        TextView latlong_parents_view = findViewById(R.id.latlong_parents);
        EditText set_orient_view = findViewById(R.id.set_orient);

        String set_orient_string = set_orient_view.getText().toString();

        label_parents = label_parents_view.getText().toString();
        String latlong_parents = latlong_parents_view.getText().toString();
        if (label_parents.equals("")) {
            Utilities.showAlert(this, "Please enter a label for your parents' house!");
            return;
        }

        if (latlong_parents.equals("")) {
            Utilities.showAlert(this, "Please enter your parents' house's coordinates!");
            return;
        }

        String[] latlong_parents_split = latlong_parents.split(",");
        if (latlong_parents_split.length != 2) {
            // TODO: Currently "1,1,,,,,," (however many ','s) are accepted as valid coordinates... do we want to keep this behavior?
            Utilities.showAlert(this, "Please enter coordinates as 'lat,long'");
            return;
        }

        try {
            lat_parents = Float.parseFloat(latlong_parents_split[0]);
            long_parents = Float.parseFloat(latlong_parents_split[1]);
        } catch (NumberFormatException e) {
            Utilities.showAlert(this, "Please enter valid coordinates!");
            return;
        }

        try {
            set_orientation = Float.parseFloat(set_orient_string);
        } catch (NumberFormatException e) {
            Utilities.showAlert(this, "Please enter valid orientation!");
            return;
        }

        if (lat_parents < -90 || lat_parents > 90 || long_parents < -180 || long_parents > 180) {
            // Might want less technical error messages in the final build
            Utilities.showAlert(this, "Please ensure -90 < lat < 90 and -180 < long < 180");
            return;
        }

        if (set_orientation < 0 || set_orientation >= 360) {
            // Might want less technical error messages in the final build
            Utilities.showAlert(this, "Please ensure -0 <= Orientation < 360");
            return;
        }
        if (!set_orient_string.equals(""))
            saveSetOrientation();

        saveProfile();
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        saveProfile();
    }

    private void saveProfile(){
        SharedPreferences preferences = getSharedPreferences("saved_data", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();


        editor.putString("label_parents", this.label_parents);
        editor.putFloat("lat_parents", this.lat_parents);
        editor.putFloat("long_parents", this.long_parents);
        editor.apply();
    }

    private void saveSetOrientation(){
        SharedPreferences preferences = getSharedPreferences("saved_data", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        editor.putFloat("set_orientation", this.set_orientation);
        editor.apply();
    }


}