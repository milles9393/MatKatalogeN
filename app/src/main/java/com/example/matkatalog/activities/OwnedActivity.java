package com.example.matkatalog.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.matkatalog.R;
import com.example.matkatalog.adapters.OwnedAdapter;
import com.example.matkatalog.db.AppDatabase;
import com.example.matkatalog.models.Thing;
import com.example.matkatalog.models.ThingViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OwnedActivity extends AppCompatActivity {

    private List<String> things = new ArrayList<>(Arrays.asList("book", "computer", "dator", "mus"));
    private RecyclerView recyclerView;
    private FloatingActionButton FAB;
    private ThingViewModel thingViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owned);
        bindViews();
        AddListeners();

        final OwnedAdapter adapter = new OwnedAdapter(new ArrayList<Thing>());

        thingViewModel = new ViewModelProvider(this).get(ThingViewModel.class);

        thingViewModel.getThingsList().observe(this, new Observer<List<Thing>>() {
            @Override
            public void onChanged(List<Thing> things) {
                adapter.addItem(things);
            }
        });

        recyclerView.setAdapter(adapter);
    }

    private void AddListeners() {
        FAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateAfterFABClick();
            }
        });
    }

    private void  navigateAfterFABClick(){
        startActivity(new Intent(getApplicationContext(), DeclareOwnershipActivity.class));
    }

    private void bindViews() {

        recyclerView = findViewById(R.id.recycler_view);
        RecyclerView.LayoutManager layoutMange = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutMange);
        FAB = findViewById(R.id.fab);
    }
}