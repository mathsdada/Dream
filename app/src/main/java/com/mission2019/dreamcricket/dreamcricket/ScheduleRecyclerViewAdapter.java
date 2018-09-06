package com.mission2019.dreamcricket.dreamcricket;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import org.json.JSONObject;

import java.util.ArrayList;

public class ScheduleRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ArrayList<Object> mDataset;
    private static final int ITEM_TYPE_SERIES = 0;
    private static final int ITEM_TYPE_MATCH = 1;

    public ScheduleRecyclerViewAdapter(ArrayList<Object> mDataset) {
        this.mDataset = mDataset;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemViewType(int position) {
        if(mDataset.get(position) instanceof String) {
            return ITEM_TYPE_SERIES;
        } else if (mDataset.get(position) instanceof JSONObject) {
            return ITEM_TYPE_MATCH;
        }
        return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
