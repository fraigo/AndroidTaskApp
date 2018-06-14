package me.franciscoigor.tasks.controllers;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.HashMap;

import me.franciscoigor.tasks.R;
import me.franciscoigor.tasks.base.DatabaseHelper;
import me.franciscoigor.tasks.base.Storage;
import me.franciscoigor.tasks.models.TaskSchema;

public class MainActivity extends AppCompatActivity {



    public MainActivity(){

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);
        // Restore instance state
        Storage.loadStorage(savedInstanceState, this);
        Storage.addInt("TEST",1);
        // Setup database
        DatabaseHelper.addSchema(new TaskSchema());
        DatabaseHelper.getDatabase(this.getApplicationContext());
    }


    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        Storage.addInt("TEST",1);
        // Save instance
        Storage.saveStorage(savedInstanceState);
    }

    @Override
    protected void onStop() {
        Storage.saveStorage(null);
        super.onStop();
    }
}
