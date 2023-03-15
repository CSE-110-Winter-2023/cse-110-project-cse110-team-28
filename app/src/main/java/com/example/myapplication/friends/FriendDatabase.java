//package com.example.myapplication.friends;
//
//import android.content.Context;
//
//import androidx.annotation.NonNull;
//import androidx.room.Database;
//import androidx.room.Room;
//import androidx.room.RoomDatabase;
//import androidx.sqlite.db.SupportSQLiteDatabase;
//
//import java.util.List;
//import java.util.concurrent.Executors;
//
//@Database(entities = {Friend.class}, version = 1)
//public abstract class FriendDatabase extends RoomDatabase {
//    private static FriendDatabase singleton = null;
//    public abstract FriendListDao friendListDao();
//
//    public synchronized static FriendDatabase getSingleton(Context context) {
//        if (singleton == null) {
//            singleton = FriendDatabase.makeDatabase(context);
//        }
//        return singleton;
//    }
//
//    private static FriendDatabase makeDatabase(Context context) {
//        return Room.databaseBuilder(context, FriendDatabase.class, "friends.db")
//                .allowMainThreadQueries()
//                .addCallback(new Callback() {
//                    @Override
//                    public void onCreate(@NonNull SupportSQLiteDatabase db) {
//                        super.onCreate(db);
//                        Executors.newSingleThreadScheduledExecutor().execute(() -> {
//                            getSingleton(context);
//                        });
//                    }
//                })
//                .build();
//    }
//
//}
