package com.mission2019.dreamcricket.dreamcricket.Adapter;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mission2019.dreamcricket.dreamcricket.Custom.StickyHeaderItemDecoration;
import com.mission2019.dreamcricket.dreamcricket.Model.TableRow;
import com.mission2019.dreamcricket.dreamcricket.R;

import java.util.ArrayList;

public class TableRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements StickyHeaderItemDecoration.StickyHeaderInterface {
    private ArrayList<TableRow> mTableData;

    public TableRecyclerViewAdapter(ArrayList<TableRow> tableData) {
        mTableData = tableData;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RowViewHolder(LayoutInflater.from(parent.getContext()).
                inflate(R.layout.table_activity_row, parent, false), false);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((RowViewHolder) holder).bindViews(mTableData.get(position));
    }

    @Override
    public int getItemCount() {
        return mTableData.size();
    }

    @Override
    public int getHeaderPositionForItem(int itemPosition) {
        return 0;
    }

    @Override
    public int getHeaderLayout(int headerPosition) {
        return R.layout.table_activity_row;
    }

    @Override
    public void bindHeaderData(View header, int headerPosition) {
        RowViewHolder viewHolder = new RowViewHolder(header, true);
        TableRow row = mTableData.get(headerPosition);
        viewHolder.bindViews(row);
    }

    @Override
    public boolean isHeader(int itemPosition) {
        return itemPosition == 0;
    }

    private class RowViewHolder extends RecyclerView.ViewHolder {
        private TextView col1Tv, col2Tv, col3Tv, col4Tv, col5Tv, col6Tv;

        RowViewHolder(@NonNull View itemView, boolean isHeader) {
            super(itemView);
            col1Tv = itemView.findViewById(R.id.column_1);
            col2Tv = itemView.findViewById(R.id.column_2);
            col3Tv = itemView.findViewById(R.id.column_3);
            col4Tv = itemView.findViewById(R.id.column_4);
            col5Tv = itemView.findViewById(R.id.column_5);
            col6Tv = itemView.findViewById(R.id.column_6);
            if (isHeader) {
                itemView.setBackgroundColor(Color.GREEN);
//                col1Tv.setTextColor(Color.WHITE);
//                col2Tv.setTextColor(Color.WHITE);
//                col3Tv.setTextColor(Color.WHITE);
//                col4Tv.setTextColor(Color.WHITE);
//                col5Tv.setTextColor(Color.WHITE);
//                col6Tv.setTextColor(Color.WHITE);
            }
        }

        void bindViews(TableRow row) {
            if (row.getColOne() != null) {
                col1Tv.setText(row.getColOne());
                col1Tv.setVisibility(View.VISIBLE);
            }
            if (row.getColTwo() != null) {
                col2Tv.setText(row.getColTwo());
                col2Tv.setVisibility(View.VISIBLE);
            }
            if (row.getColThree() != null) {
                col3Tv.setText(row.getColThree());
                col3Tv.setVisibility(View.VISIBLE);
            } else {
                /* If there are only two columns available,
                 * then change the weight of first column to 1, so that columns will share
                 * the screen 50-50 */
                LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) col1Tv.getLayoutParams();
                layoutParams.weight = 1;
                col1Tv.setLayoutParams(layoutParams);
            }
            if (row.getColFour() != null) {
                col4Tv.setText(row.getColFour());
                col4Tv.setVisibility(View.VISIBLE);
            }
            if (row.getColFive() != null) {
                col5Tv.setText(row.getColFive());
                col5Tv.setVisibility(View.VISIBLE);
            }
            if (row.getColSix() != null) {
                col6Tv.setText(row.getColSix());
                col6Tv.setVisibility(View.VISIBLE);
            }
        }
    }
}
