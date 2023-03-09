package com.example.myapplication;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class UIDAPI {

    private volatile static UIDAPI instance = null;

    private OkHttpClient client;

    public UIDAPI() {
        this.client = new OkHttpClient();
    }

    public static UIDAPI provide() {
        if (instance == null) {
            instance = new UIDAPI();
        }
        return instance;
    }

    static final String prefix = "https://socialcompass.goto.ucsd.edu/locations/";
    static final MediaType JSON = MediaType.get("application/json; charset=utf-8");
    public String upsertFriendUIDs(String myUID, List<String> UIDs) throws IOException {
        RequestBody body = RequestBody.create(new Gson().toJson(UIDs).getBytes(StandardCharsets.UTF_8));
        Request request = new Request.Builder()
                .url(prefix+myUID)
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }

    public List<String> getFriendUIDs(String myUID) throws IOException {
        Request request = new Request.Builder()
                .url(prefix+myUID)
                .build();

        Type type = new TypeToken<List<String>>(){}.getType();
        try (Response response = client.newCall(request).execute()) {
            return new Gson().fromJson(response.body().string(), type);
        }
    }
}
