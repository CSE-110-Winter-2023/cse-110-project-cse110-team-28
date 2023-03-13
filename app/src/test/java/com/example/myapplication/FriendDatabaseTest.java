package com.example.myapplication;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import android.content.Context;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.myapplication.friends.Friend;
import com.example.myapplication.friends.FriendDatabase;
import com.example.myapplication.friends.FriendListDao;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;

@RunWith(AndroidJUnit4.class)
public class FriendDatabaseTest {
    private FriendListDao dao;
    private FriendDatabase db;

    @Before
    public void createDb() {
        Context context = ApplicationProvider.getApplicationContext();
        db = Room.inMemoryDatabaseBuilder(context, FriendDatabase.class)
                .allowMainThreadQueries()
                .build();

        dao = db.friendListDao();
    }

    @After
    public void closeDb() throws IOException {
        db.close();
    }

    @Test
    public void testInsert() {
        Friend item1 = new Friend(10.2f, 10.2f, "Bob", "AOSDUOIAUOD");
        Friend item2 = new Friend(5.0f, 5.0f, "Tim", "OULA");

        long id1 = dao.insert(item1);
        long id2 = dao.insert(item2);

        // Check inserted with unique IDs
        assertNotEquals(id1, id2);
    }

    @Test
    public void testGet() {
        Friend insertedItem = new Friend(10.2f, 10.2f, "Bob", "AOSDUOIAUOD");
        long id = dao.insert(insertedItem);

        Friend item = dao.get(insertedItem.getUUID());

        assertEquals(id, item.id);
        assertEquals(insertedItem.getLat(),item.getLat(), 0);
        assertEquals(insertedItem.getLongit(), item.getLongit(), 0);
        assertEquals(insertedItem.getUsername(), item.getUsername());
        assertEquals(insertedItem.getUUID(), item.getUUID());
    }

    @Test
    public void testUpdate() {
        Friend friend = new Friend(10.2f, 10.2f, "Bob", "AOSDUOIAUOD");
        String UUID = friend.getUUID();
        long id = dao.insert(friend);

        friend = dao.get(UUID);
        friend.setLat(80f);
        int itemsUpdated = dao.update(friend);
        assertEquals(1, itemsUpdated);

        friend = dao.get(UUID);
        assertNotNull(friend);
        assertEquals(friend.getLat(), 80f, 0);
    }

    @Test
    public void testDelete(){
        Friend item = new Friend(5.0f, 5.0f, "Tim", "OULA");
        String UUID = "OULA";
        long id = dao.insert(item);

        item = dao.get(UUID);
        int itemsDeleted = dao.delete(item);
        assertEquals(1, itemsDeleted);
        assertNull(dao.get(UUID));
    }

}
