package com.example.myapplication.location_data;

import android.util.Log;

import androidx.annotation.WorkerThread;

import java.lang.reflect.Type;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class LocationAPI {
    private volatile static LocationAPI instance = null;
    private OkHttpClient client;
    private String url_begin = "https://socialcompass.goto.ucsd.edu/";
    // TODO we should be able to change this for testing

    public LocationAPI() {
        this.client = new OkHttpClient();
    }

    public static LocationAPI provide() {
        if (instance == null) {
            instance = new LocationAPI();
        }
        return instance;
    }

    // Check return code

    /**
     * DON'T FORGET: Java disallows network requests on the main thread.
     */
    @WorkerThread
    public List<LocationData> getAll() {
        var request = new Request.Builder()
                .url(url_begin + "locations")
                .method("GET", null)
                .build();
        try (Response response = client.newCall(request).execute()) {
            assert response.body() != null;
            var body = response.body().string();
            Log.i("GET", body);

            Type listType = new TypeToken<List<LocationData>>(){}.getType();

            List<LocationData> toReturn = new Gson().fromJson(body, listType);
            return toReturn;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @WorkerThread
    public LocationData get(String UUID) {
        Request request = new Request.Builder()
                .url(url_begin + "location/" + UUID)
                .method("GET", null)
                .build();

        try (Response response = client.newCall(request).execute()) {
            assert response.body() != null;
            String body = response.body().string();

            Log.i("GET", body);

            // Check return code
            if (response.code() != 200) {
                // TODO: Throw exception? Or return null or something.
                return null;
            }

            LocationData toReturn = LocationData.fromJSON(body);
            return toReturn;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


}
