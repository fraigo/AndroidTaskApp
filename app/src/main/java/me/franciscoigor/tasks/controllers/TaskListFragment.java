package me.franciscoigor.tasks.controllers;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.TextView;

import me.franciscoigor.tasks.R;
import me.franciscoigor.tasks.base.DataModel;
import me.franciscoigor.tasks.base.ItemDialogFragment;
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
        //adapter.addItem(new TaskModel("Task3","Desc3"));
    }

    @Override
    protected ItemHolder createItemHolder(View view) {
        return new TaskItemHolder(view);
    }

    private class TaskItemHolder extends ItemHolder{

        TextView mTextName, mTextDescription;
        TaskModel model;
        private static final int REQUEST_DATE = 0;
        private static final String DIALOG_DATE = "date";

        public TaskItemHolder(View view){
            super(view);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FragmentManager manager = getFragmentManager();
                    TaskDialogFragment dialog = TaskDialogFragment.newInstance(model);
                    dialog.setTargetFragment(TaskListFragment.this, REQUEST_DATE);
                    dialog.show(manager, DIALOG_DATE);
                }
            });
            mTextName = view.findViewById(R.id.task_list_item_title);
            mTextDescription = view.findViewById(R.id.task_list_item_description);
        }

        @Override
        public void bind(DataModel item) {
            this.model = (TaskModel) item;
            mTextName.setText(item.getStringValue(TaskModel.FIELD_TITLE));
            mTextDescription.setText(item.getStringValue(TaskModel.FIELD_DESCRIPTION));
        }
    }

    @Override
    protected int getItemHolderLayout() {
        return R.layout.task_list_item;
    }
}
