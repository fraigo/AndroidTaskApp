package me.franciscoigor.tasks.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.ImageView;

import me.franciscoigor.tasks.R;
import me.franciscoigor.tasks.base.DatabaseHelper;
import me.franciscoigor.tasks.base.SingleFragmentActivity;
import me.franciscoigor.tasks.models.TaskModel;

public class MainActivity extends SingleFragmentActivity {

    ImageView icon;
    static TaskListFragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        icon = findViewById(R.id.action_icon);
        icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getSupportFragmentManager();
                TaskDialogFragment dialog = TaskDialogFragment.newInstance(new TaskModel());
                dialog.show(manager,  TaskDialogFragment.DIALOG_ITEM);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void addSchemas() {
        DatabaseHelper.addSchema(new TaskModel());
    }

    @Override
    protected Fragment createFragment() {
        fragment = (TaskListFragment)TaskListFragment.newInstance(null);
        return fragment;
    }

    public static TaskListFragment getFragment() {
        return fragment;
    }

}
