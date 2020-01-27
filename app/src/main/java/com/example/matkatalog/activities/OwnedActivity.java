package com.example.matkatalog.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.matkatalog.R;
import com.example.matkatalog.adapters.OwnedAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OwnedActivity extends AppCompatActivity {

    private List<String> things = new ArrayList<>(Arrays.asList("book", "computer", "dator", "mus"));
    private ListView listview;
    private FloatingActionButton FAB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owned);
        bindViews();

        listview.setAdapter((ArrayAdapter)getAdapter());
        AddListeners();
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

        listview = findViewById(R.id.listview);
        FAB = findViewById(R.id.fab);
    }

    private Adapter getAdapter(){
        return new OwnedAdapter(getApplicationContext(), things);
    }






}
