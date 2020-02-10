package com.example.matkatalog.db;

import android.content.Context;
import android.content.IntentFilter;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.matkatalog.models.Thing;
import com.example.matkatalog.models.ThingDoa;

@Database(entities = {Thing.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase INSTANCE;


    public abstract ThingDoa thingDoa();

    public static AppDatabase getAppDatabase(Context context){

        if(INSTANCE==null){
            INSTANCE = Room.databaseBuilder(context,
                    AppDatabase.class, "database-name").allowMainThreadQueries().build();
        }
        return INSTANCE;
    }
}



