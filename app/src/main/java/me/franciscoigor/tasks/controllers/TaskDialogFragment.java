package me.franciscoigor.tasks.controllers;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.TextView;

import me.franciscoigor.tasks.R;
import me.franciscoigor.tasks.base.DataModel;
import me.franciscoigor.tasks.base.ItemDialogFragment;
import me.franciscoigor.tasks.base.ListFragment;
import me.franciscoigor.tasks.models.TaskModel;

public class TaskDialogFragment extends ItemDialogFragment {

    public static final String DIALOG_ITEM = "item";

    public static TaskDialogFragment newInstance(DataModel item) {
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
    protected void bindDialog(final DataModel item, View v) {
        TextView itemTitle=v.findViewById(R.id.task_dialog_title);
        System.out.println(item);
        itemTitle.setText(item.getStringValue(TaskModel.FIELD_TITLE));
        itemTitle.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                item.setValue(TaskModel.FIELD_TITLE,s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        TextView itemDesc=v.findViewById(R.id.task_dialog_description);
        itemDesc.setText(item.getStringValue(TaskModel.FIELD_DESCRIPTION));
        itemDesc.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                item.setValue(TaskModel.FIELD_DESCRIPTION,s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    protected String getDialogTitle(DataModel item) {
        return "Task ";
    }

    @Override
    public void onDialogResult(DialogInterface dialog, DataModel item) {
        super.onDialogResult(dialog, item);
        ListFragment.ItemAdapter adapter= MainActivity.getFragment().getAdapter();
        if (!adapter.findItem(item)){
            adapter.addItem(item);
            adapter.notifyItemInserted(adapter.getItemCount()-1);
        }else{
            adapter.updateItem(item);
        }

    }
}
