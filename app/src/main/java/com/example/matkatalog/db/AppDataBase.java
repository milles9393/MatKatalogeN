package com.example.matkatalog.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.matkatalog.models.Thing;
import com.example.matkatalog.models.ThingDoa;

@Database(entities = {Thing.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase INSTANCE;

    public abstract ThingDoa thingDoa();
}

