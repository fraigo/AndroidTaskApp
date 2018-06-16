package me.franciscoigor.tasks.controllers;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import me.franciscoigor.tasks.R;
import me.franciscoigor.tasks.base.DataModel;
import me.franciscoigor.tasks.base.ItemDialogFragment;
import me.franciscoigor.tasks.models.TaskModel;

public class TaskDialogFragment extends ItemDialogFragment {

    public static TaskDialogFragment newInstance(TaskModel item) {
        Bundle args = new Bundle();
        TaskDialogFragment fragment = new TaskDialogFragment();
        fragment.setItem(item);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getDialogLayout() {
        return R.layout.task_dialog;
    }

    @Override
    protected void bindDialog(DataModel item, View v) {
        TextView itemTitle=v.findViewById(R.id.task_dialog_title);
        itemTitle.setText(item.getStringValue(TaskModel.FIELD_TITLE));
        TextView itemDesc=v.findViewById(R.id.task_dialog_description);
        itemDesc.setText(item.getStringValue(TaskModel.FIELD_DESCRIPTION));
    }

    @Override
    protected String getDialogTitle(DataModel item) {
        return "Task ";
    }
}
