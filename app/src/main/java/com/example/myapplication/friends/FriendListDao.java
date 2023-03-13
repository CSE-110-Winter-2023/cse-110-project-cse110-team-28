package com.example.myapplication.friends;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface FriendListDao {

    @Insert
    long insert(Friend friend);

    @Query("SELECT * FROM `friends` WHERE `UUID` = :uuid")
    Friend get(String uuid);

    @Query("SELECT * FROM `friends` ORDER BY `id`")
    List<Friend> getAll();

    @Update
    int update(Friend friend);

    @Delete
    int delete(Friend friend);
}
