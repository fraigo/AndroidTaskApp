package me.franciscoigor.tasks.controllers;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import me.franciscoigor.tasks.R;
import me.franciscoigor.tasks.base.DataModel;
import me.franciscoigor.tasks.base.ItemHolder;
import me.franciscoigor.tasks.base.ListFragment;
import me.franciscoigor.tasks.models.TaskModel;

public class TaskListFragment extends ListFragment {

    private static final String ARG_PARAM1 = "param1";
    private String mParam1;

    public static ListFragment newInstance(String param1) {
        TaskListFragment fragment = new TaskListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    public void createFragment(Bundle savedInstanceState) {
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    protected void setupAdapter(ListFragment.ItemAdapter adapter) {
        adapter.addItem(new TaskModel("Task1","Desc1"));
        adapter.addItem(new TaskModel("Task2","Desc2"));
        adapter.addItem(new TaskModel("Task3","Desc3"));
    }

    @Override
    protected ItemHolder createItemHolder(View view) {
        return new TaskItemHolder(view);
    }

    private class TaskItemHolder extends ItemHolder{

        TextView mTextName, mTextDescription;

        public TaskItemHolder(View view){
            super(view);
            mTextName = view.findViewById(R.id.task_list_item_title);
            mTextDescription = view.findViewById(R.id.task_list_item_description);
        }

        @Override
        public void bind(DataModel item) {
            TaskModel model=(TaskModel)item;
            mTextName.setText(model.getStringValue(item.getStringValue(TaskModel.FIELD_TITLE)));
            mTextDescription.setText(model.getStringValue(item.getStringValue(TaskModel.FIELD_DESCRIPTION)));
        }
    }

    @Override
    protected int getItemHolderLayout() {
        return R.layout.task_list_item;
    }
}
