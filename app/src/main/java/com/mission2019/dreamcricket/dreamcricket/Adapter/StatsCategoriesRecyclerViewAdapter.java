package com.mission2019.dreamcricket.dreamcricket.Adapter;

import android.support.annotation.NonNull;
import android.support.design.button.MaterialButton;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mission2019.dreamcricket.dreamcricket.Custom.StickyHeaderItemDecoration;
import com.mission2019.dreamcricket.dreamcricket.R;

import java.util.ArrayList;

public class StatsCategoriesRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements StickyHeaderItemDecoration.StickyHeaderInterface{
    private ArrayList<String> mCategories;
    private OnCategoryClickListener mClickListener;
    private static final int ITEM_TYPE_CATEGORY_HEAD = 0;
    private static final int ITEM_TYPE_CATEGORY = 1;

    public interface OnCategoryClickListener {
        void onCategoryItemClick(int pos);
    }

    public StatsCategoriesRecyclerViewAdapter(ArrayList<String> categories, OnCategoryClickListener clickListener) {
        mCategories = categories;
        mClickListener = clickListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if (viewType == ITEM_TYPE_CATEGORY) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.team_stats_fragment_category, parent, false);
            return new CategoryViewHolder(view);
        } else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.team_stats_fragment_category_header, parent, false);
            return new HeaderViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (ITEM_TYPE_CATEGORY_HEAD == getItemViewType(position)) {
            ((HeaderViewHolder)holder).bindViews(mCategories.get(position));
        } else {
            ((CategoryViewHolder) holder).bindViews(mCategories.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return mCategories.size();
    }

    @Override
    public int getItemViewType(int position) {
        switch (mCategories.get(position)) {
            case "Recent Matches":
            case "Batting Records":
            case "Bowling Records":
            case "Head to Head Records":
            case "Batting":
            case "Bowling":
                return ITEM_TYPE_CATEGORY_HEAD;
            default:
                return ITEM_TYPE_CATEGORY;

        }
    }

    private class CategoryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView mCategoryTextView;

        CategoryViewHolder(View view) {
            super(view);
            mCategoryTextView = view.findViewById(R.id.category_tv);
            mCategoryTextView.setOnClickListener(this);
        }

        void bindViews(String category) {
            mCategoryTextView.setText(category);
        }

        @Override
        public void onClick(View v) {
            mClickListener.onCategoryItemClick(getAdapterPosition());
        }
    }

    private class HeaderViewHolder extends RecyclerView.ViewHolder {
        private TextView mHeaderTextView;

        HeaderViewHolder(View view) {
            super(view);
            mHeaderTextView = view.findViewById(R.id.category_header_tv);
        }

        void bindViews(String category) {
            mHeaderTextView.setText(category);
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
        view.setText(mCategories.get(headerPosition));
    }

    @Override
    public boolean isHeader(int itemPosition) {
        return ITEM_TYPE_CATEGORY_HEAD == getItemViewType(itemPosition);
    }
}
