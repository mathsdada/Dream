package com.mission2019.dreamcricket.dreamcricket.Adapter;

import android.support.annotation.NonNull;
import android.support.design.button.MaterialButton;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mission2019.dreamcricket.dreamcricket.Custom.StickyHeaderItemDecoration;
import com.mission2019.dreamcricket.dreamcricket.Model.Schedule.SchedulePlayer;
import com.mission2019.dreamcricket.dreamcricket.R;

import java.util.ArrayList;

public class PlayerStatsSquadRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements StickyHeaderItemDecoration.StickyHeaderInterface{
    private ArrayList<Object> mSquadDataset;
    private onPlayerClickListener mClickListener;
    private static final int ITEM_TYPE_ROLE = 0;
    private static final int ITEM_TYPE_PLAYER = 1;

    public interface onPlayerClickListener {
        void onPlayerClick(int pos);
    }

    public PlayerStatsSquadRecyclerViewAdapter(ArrayList<Object> squadDataset, onPlayerClickListener clickListener) {
        mSquadDataset = squadDataset;
        mClickListener = clickListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if (viewType == ITEM_TYPE_PLAYER) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.team_stats_fragment_category, parent, false);
            return new PlayerViewHolder(view);
        } else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.team_stats_fragment_category_header, parent, false);
            return new RoleViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (ITEM_TYPE_ROLE == getItemViewType(position)) {
            ((RoleViewHolder)holder).bindViews((String) mSquadDataset.get(position));
        } else {
            ((PlayerViewHolder) holder).bindViews((SchedulePlayer) mSquadDataset.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return mSquadDataset.size();
    }

    @Override
    public int getItemViewType(int position) {
        Object object = mSquadDataset.get(position);
        if(object instanceof String) {
            return ITEM_TYPE_ROLE;
        } else {
            return ITEM_TYPE_PLAYER;
        }
    }

    private class PlayerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView mCategoryTextView;

        PlayerViewHolder(View view) {
            super(view);
            mCategoryTextView = view.findViewById(R.id.category_tv);
            mCategoryTextView.setOnClickListener(this);
        }

        void bindViews(SchedulePlayer player) {
            mCategoryTextView.setText(player.getName());
        }

        @Override
        public void onClick(View v) {
            mClickListener.onPlayerClick(getAdapterPosition());
        }
    }

    private class RoleViewHolder extends RecyclerView.ViewHolder {
        private TextView mHeaderTextView;

        RoleViewHolder(View view) {
            super(view);
            mHeaderTextView = view.findViewById(R.id.category_header_tv);
        }

        void bindViews(String role) {
            mHeaderTextView.setText(role);
        }

    }

    @Override
    public int getHeaderPositionForItem(int itemPosition) {
        int headerPosition = 0;
        do {
            if (this.isHeader(itemPosition)) {
                headerPosition = itemPosition;
                break;
            }
            itemPosition -= 1;
        } while (itemPosition >= 0);
        return headerPosition;
    }

    @Override
    public int getHeaderLayout(int headerPosition) {
        return R.layout.team_stats_fragment_category_header;
    }

    @Override
    public void bindHeaderData(View header, int headerPosition) {
        TextView view = header.findViewById(R.id.category_header_tv);
        view.setText((String)mSquadDataset.get(headerPosition));
    }

    @Override
    public boolean isHeader(int itemPosition) {
        return ITEM_TYPE_ROLE == getItemViewType(itemPosition);
    }
}
