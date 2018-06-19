package me.franciscoigor.tasks.controllers;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

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
    public ItemAdapter newAdapter() {
        return new ListFragment.ItemAdapter("tasks");
    }

    @Override
    protected void setupAdapter(ListFragment.ItemAdapter adapter) {
        ArrayList<DataModel> list=adapter.loadItems("tasks");
        if (list.size()==0){
            adapter.addItem(new TaskModel("Task1","Desc1"));
            adapter.addItem(new TaskModel("Task2","Desc2"));
        }

    }

    @Override
    protected ItemHolder createItemHolder(View view) {
        return new TaskItemHolder(view);
    }

    private class TaskItemHolder extends ItemHolder{

        TextView mTextName, mTextDescription;
        ImageView mDelete;
        DataModel model;
        private static final int REQUEST_DATE = 0;

        public TaskItemHolder(View view){
            super(view);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FragmentManager manager = getFragmentManager();
                    System.out.println("DIALOG "+model);
                    TaskDialogFragment dialog = TaskDialogFragment.newInstance(model);
                    dialog.setTargetFragment(TaskListFragment.this, REQUEST_DATE);
                    dialog.show(manager, TaskDialogFragment.DIALOG_ITEM);
                }
            });
            mTextName = view.findViewById(R.id.task_list_item_title);
            mTextDescription = view.findViewById(R.id.task_list_item_description);
            mDelete = view.findViewById(R.id.task_list_item_delete);
            mDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getAdapter().deleteItem(model);
                }
            });
        }

        @Override
        public void bind(DataModel model) {
            this.model=model;
            mTextName.setText(model.getStringValue(TaskModel.FIELD_TITLE));
            mTextDescription.setText(model.getStringValue(TaskModel.FIELD_DESCRIPTION));
        }
    }

    @Override
    protected int getItemHolderLayout() {
        return R.layout.task_list_item;
    }
}
