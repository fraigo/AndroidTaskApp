package me.franciscoigor.tasks.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import me.franciscoigor.tasks.R;


public abstract class ListFragment extends Fragment {
    private RecyclerView recyclerView;
    private ItemAdapter adapter;

    public ListFragment() {
        // Required empty public constructor
    }


    protected abstract void createFragment(Bundle savedInstanceState);

    protected abstract void setupAdapter(ItemAdapter adapter);


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createFragment(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        recyclerView = (RecyclerView) view
                .findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateView();

        return view;
    }

    public void updateView(){

        if (adapter == null) {
            adapter = new ItemAdapter();
            setupAdapter(adapter);
            recyclerView.setAdapter(adapter);
        } else {
            adapter.notifyDataSetChanged();
        }
    }


    public class ListItemHolder extends ItemHolder {

        private TextView mTitle, mDetails;

        public ListItemHolder(View view){
            super(view);
            mTitle = view.findViewById(R.id.list_item_title);
            mDetails = view.findViewById(R.id.list_item_details);
        }

        @Override
        public void bind(DataModel item) {
            mTitle.setText(item.getUuid());
            mDetails.setText(item.toString());
        }

    }


    protected class ItemAdapter extends RecyclerView.Adapter<ItemHolder> {

        private ArrayList<DataModel> list;

        public ItemAdapter() {
            list = new ArrayList<DataModel>();
        }

        public void addItem(DataModel item){
            list.add(item);
        }

        @Override
        public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater
                    .inflate(getItemHolderLayout(), parent, false);
            return createItemHolder(view);
        }
        @Override
        public void onBindViewHolder(ItemHolder holder, int position) {
            DataModel item = list.get(position);
            holder.bind(item);
        }
        @Override
        public int getItemCount() {
            return list.size();
        }
    }

    protected int getItemHolderLayout(){
        return R.layout.list_item;
    }

    protected ItemHolder createItemHolder(View view){
        return new ListItemHolder(view);
    }


}
