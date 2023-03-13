package com.example.myapplication.location_data;

import android.util.Log;

import androidx.annotation.WorkerThread;

import java.lang.reflect.Type;

import com.example.myapplication.location_data.annotations.PatchExclude;
import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.time.Instant;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class LocationAPI{
    private volatile static LocationAPI instance = null;
    private OkHttpClient client;
    public String url_begin = "https://socialcompass.goto.ucsd.edu/";
    // TODO we should be able to change this for testing

    public LocationAPI(String url) {
        this.client = new OkHttpClient();
        this.url_begin = url;
    }

    // no argument constructor uses default server
    public static LocationAPI provide() {
        if (instance == null) {
            instance = new LocationAPI("https://socialcompass.goto.ucsd.edu/");
        }
        instance.url_begin = "https://socialcompass.goto.ucsd.edu/";
        return instance;
    }

    // overload: use provided server instead
    public static LocationAPI provide(String url) {
        if (instance == null) {
            instance = new LocationAPI(url);
        }
        instance.url_begin = url;
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
    public LocationData get(String public_code){
        Request request = new Request.Builder()
                .url(url_begin + "location/" + public_code)
                .method("GET", null)
                .addHeader("accept", "application/json")
                .build();

        try (Response response = client.newCall(request).execute()) {
            assert response.body() != null;
            String body = response.body().string();

            Log.i("GET", body);

            // Check return code
            if (response.code() != 200) {
                throw new RuntimeException("Response code not OK: " + response.code());
            }

            LocationData toReturn = LocationData.fromJSON(body);
            return toReturn;
        } catch (Exception e) {
            // e.printStackTrace();
            return null;
        }
    }

    @WorkerThread
    public String put(LocationData location) {
        // Create RequestBody
        Instant instant = Instant.now();
        location.updated_at = instant.getEpochSecond();
        String json = location.toJSON();

        RequestBody requestBody = RequestBody.create(json, MediaType.parse("application/json; charset=utf-8"));

        var request = new Request.Builder()
                .url(url_begin + "location/" + location.public_code)
                .put(requestBody)
                .build();

        try (var response = client.newCall(request).execute()) {
            assert response.body() != null;
            var body = response.body().string();
            Log.i("PUT", body);

            // Check return code
            if (response.code() != 200) {
                throw new RuntimeException("Response code not OK: " + response.code());
            }
            return body;
        } catch (Exception e) {
            // e.printStackTrace();
            return null;
        }
    }

    @WorkerThread
    public String delete(LocationData location){

        String json = location.toJSON();
        RequestBody requestBody = RequestBody.create(json, MediaType.parse("application/json; charset=utf-8"));
    
        var request = new Request.Builder()
                .url(url_begin + "location/" + location.public_code)
                .addHeader("Accept", "application/json")
                .addHeader("Content-Type", "application/json")
                .delete(requestBody)
                .build();

        try (var response = client.newCall(request).execute()) {
            assert response.body() != null;
            var body = response.body().string();
            Log.i("DELETE", body);

            // Check return code
            if (response.code() != 200) {
                throw new RuntimeException("Response code not OK: " + response.code());
            }
            return body;

        } catch (Exception e) {
            // e.printStackTrace();
            return null;
        }
    }

    @WorkerThread
    public String patch(LocationData location) {
        Instant instant = Instant.now();
        location.updated_at = instant.getEpochSecond();

        ExclusionStrategy strategy = new ExclusionStrategy() {
            @Override
            public boolean shouldSkipClass(Class<?> clazz) {
                return false;
            }

            // Skip any fields with the @PatchExclude annotation
            @Override
            public boolean shouldSkipField(FieldAttributes field) {
                return field.getAnnotation(PatchExclude.class) != null;
            }
        };
        Gson gson = new GsonBuilder()
                .addSerializationExclusionStrategy(strategy)
                .create();

        String json = gson.toJson(location);

        RequestBody requestBody = RequestBody.create(json, MediaType.parse("application/json; charset=utf-8"));

        var request = new Request.Builder()
                .url(url_begin + "location/" + location.public_code)
                .patch(requestBody)
                .build();

        try (var response = client.newCall(request).execute()) {
            assert response.body() != null;
            var body = response.body().string();
            Log.i("PATCH", body);

            // Check return code
            if (response.code() != 200) {
                throw new RuntimeException("Response code not OK: " + response.code());
            }
            return body;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
}
