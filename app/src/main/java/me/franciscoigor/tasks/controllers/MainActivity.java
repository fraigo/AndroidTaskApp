package me.franciscoigor.tasks.controllers;

import android.support.v4.app.Fragment;

import me.franciscoigor.tasks.base.DatabaseHelper;
import me.franciscoigor.tasks.base.ListFragment;
import me.franciscoigor.tasks.base.SingleFragmentActivity;
import me.franciscoigor.tasks.models.TaskModel;

public class MainActivity extends SingleFragmentActivity {



    @Override
    protected void addSchemas() {
        DatabaseHelper.addSchema(new TaskModel());
    }

    @Override
    protected Fragment createFragment() {
        return TaskListFragment.newInstance(null);
    }
}
