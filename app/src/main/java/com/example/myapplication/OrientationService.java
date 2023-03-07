package com.example.myapplication;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class OrientationService implements SensorEventListener {
    private static OrientationService instance;

    private final SensorManager sensorManager;
    private float[] accelerometerReading;
    private float[] magnetometerReading;

    float currentDegree = 0.0f;
    private MutableLiveData<Float> azimuth;

    /**
     * Constructor for OrientationService
     * @param activity Context needed to initiate SensorManager
     */
    protected OrientationService(Activity activity){
        this.azimuth = new MutableLiveData<>();
        this.sensorManager = (SensorManager) activity.getSystemService(Context.SENSOR_SERVICE);
        // Register sensor listeners
        this.registerSensorListeners();
    }

    private void registerSensorListeners() {
        // Register our listener to the accelerometer and magnetometer.
        // We need both pieces of data to compute the orientation!
        sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD),
                SensorManager.SENSOR_DELAY_NORMAL);
    }

    public static OrientationService singleton(Activity activity) {
        if (instance == null) {
            instance = new OrientationService(activity);
        }
        return instance;
    }

    /**
     * This method is called when the sensor detects a change in value.
     * @param event the event containing the values we need.
     */
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            accelerometerReading = event.values;
        }
        if (event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD) {
            magnetometerReading = event.values;
        }

        if (accelerometerReading != null && magnetometerReading != null) {
            // We have both sensors, so we can compute the orientation
            onBothSensorDataAvailable();
        }
    }

    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Do nothing
        return;
    }

    private void onBothSensorDataAvailable() {
        final double PI = 3.14159265358979323846;
        // Discount contract checking
        if (accelerometerReading == null || magnetometerReading == null) {
            throw new IllegalStateException("Both sensors must be available to compute orientation");
        }

        var r = new float[9];
        var i = new float[9];
        // Linear algebra magic
        var success = SensorManager.getRotationMatrix(r, i, accelerometerReading, magnetometerReading);
        // Did it work?
        if (success) {
            var orientation = new float[3];
            SensorManager.getOrientation(r, orientation);

            //converts orientation from radians to degrees
            orientation[0] = (float) ((orientation[0] * 180) / PI);

            // Orientation in order: azimuth, pitch, roll. We only care about azimuth
            // (Around the z-axis from -pi to pi)
            // 0 means north, pi means south. pi/2 is east, 3pi/2 is west.
            this.azimuth.postValue(orientation[0]);



        }
    }

    public void unregisterSensorListeners() { sensorManager.unregisterListener(this); }

    public LiveData<Float> getOrientation() { return this.azimuth; }

    public void setMockOrientationSource(MutableLiveData<Float> mockDataSource) {
        unregisterSensorListeners();
        this.azimuth = mockDataSource;
    }
}
