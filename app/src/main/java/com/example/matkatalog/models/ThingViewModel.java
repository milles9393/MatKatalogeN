package com.example.matkatalog.models;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.example.matkatalog.db.AppDatabase;
import java.util.List;



public class ThingViewModel extends AndroidViewModel {

    private final LiveData<List<Thing>> thingsList;
    private AppDatabase DB;

    public ThingViewModel(Application application){
        super(application);
        DB = AppDatabase.getAppDatabase(this.getApplication());
        thingsList = DB.thingDoa().getAll();
    }

    public LiveData<List<Thing>> getThingsList(){
        return thingsList;
    }



}
