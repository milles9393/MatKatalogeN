package com.example.matkatalog.models;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ThingDoa {

    @Query("SELECT * FROM things")
    LiveData<List<Thing>> getAll();
    //List<Thing> getAll();


    @Query("SELECT * FROM things WHERE uid IN (:userIds)")
    List<Thing> loadAllByIds(int[] userIds);

    @Query("SELECT * FROM things WHERE name LIKE :name LIMIT 1")
    Thing findByName(String name);

    @Insert
    void insertAll(Thing... users);

    @Insert
    long insert(Thing thing);

    @Delete
    void delete(Thing thing);
}
