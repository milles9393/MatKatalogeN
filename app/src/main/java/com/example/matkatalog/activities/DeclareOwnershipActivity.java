package com.example.matkatalog.activities;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.matkatalog.R;
import com.example.matkatalog.db.AppDatabase;
import com.example.matkatalog.models.Thing;
import com.example.matkatalog.utils.ImageHelpers;

import java.io.File;
import java.io.IOException;

public class DeclareOwnershipActivity extends AppCompatActivity {

    private static final String TAG = "DeclareOwnershipActivit";
    private static final String CURRENT_PHOTO = "currentPhoto";
    static final int REQUEST_IMAGE_CAPTURE = 1;

    private EditText nameField;
    private ImageButton imageButton;
    private Button saveButton;
    private Button cancelButton;
    private String currentPhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_declare_ownership);

        bindViews();
        addListeners();
    }

    private void cancel(){
        //Todo


        Toast hej = Toast.makeText(getApplicationContext(), "saveaSDADSADSADASDASDAS: ", Toast.LENGTH_SHORT);
        hej.show();
        Intent intent = new Intent(getApplicationContext(), OwnedActivity.class);
        startActivity(intent);


    }

    public void saveThing(){

        //Todo check if thing and image are there

        AppDatabase DB = AppDatabase.getAppDatabase(getApplicationContext());
        Thing thing = new Thing(nameField.getText().toString(), currentPhoto);

        long id = DB.thingDoa().insert(thing);

        Toast toast  = Toast.makeText(getApplicationContext(), "save to DB with ID: " + id, Toast.LENGTH_LONG);
        toast.show();
    }

    private void takePhoto(){
        dispatchTakePictureIntent();
    }

    private void bindViews() {
        nameField = findViewById(R.id.editTextNameOfThing);
        imageButton = findViewById(R.id.imageButtonAddPhoto);
        cancelButton = findViewById(R.id.buttonCancel);
        saveButton = findViewById(R.id.buttonSave);
    }

    private void addListeners() {
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takePhoto();
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveThing();
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancel();
            }
        });
    }



    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = ImageHelpers.createImageFile(getApplicationContext());
            } catch (IOException ex) {}
            // Continue only if the File was successfully created
            if (photoFile != null) {
                currentPhoto = photoFile.getAbsolutePath();
                Uri photoURI = FileProvider.getUriForFile(getApplicationContext(),
                        "com.example.matkatalog.activities.fileprovider",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
            }

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            setPic();
        }
    }

    // invoked when the activity may be temporarily destroyed, save the instance state here
    public void onSaveInstanceState(Bundle savedInstanceState) {
        // Save the user's current game state
        savedInstanceState.putString(CURRENT_PHOTO, currentPhoto);
        // Always call the superclass so it can save the view hierarchy state
        super.onSaveInstanceState(savedInstanceState);
    }

    public void onRestoreInstanceState(Bundle savedInstanceState) {
        // Always call the superclass so it can restore the view hierarchy
        super.onRestoreInstanceState(savedInstanceState);
        // Restore state members from saved instance
        currentPhoto = savedInstanceState.getString(CURRENT_PHOTO);
        imageButton.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onGlobalLayout() {
                imageButton.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                setPic();
            }
        });
    }


    private void setPic(){

        int width = imageButton.getWidth();
        int height = imageButton.getHeight();

        imageButton.setImageBitmap(ImageHelpers.scaleImage(imageButton.getWidth(),
                imageButton.getHeight(),currentPhoto));
    }
}





