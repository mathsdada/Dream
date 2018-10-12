package com.mission2019.dreamcricket.dreamcricket.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
                inflate(R.layout.table_activity_row, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((RowViewHolder)holder).bindViews(mTableData.get(position));
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
        TextView col1Tv, col2Tv,col3Tv, col4Tv, col5Tv, col6Tv;
        TableRow row = mTableData.get(headerPosition);
        col1Tv = header.findViewById(R.id.column_1);
        col2Tv = header.findViewById(R.id.column_2);
        col3Tv = header.findViewById(R.id.column_3);
        col4Tv = header.findViewById(R.id.column_4);
        col5Tv = header.findViewById(R.id.column_5);
        col6Tv = header.findViewById(R.id.column_6);
        if (row.getColOne() != null) {
            col1Tv.setText(row.getColOne());
        } else {
            col1Tv.setVisibility(View.GONE);
        }
        if (row.getColTwo() != null) {
            col2Tv.setText(row.getColTwo());
        } else {
            col2Tv.setVisibility(View.GONE);
        }
        if (row.getColThree() != null) {
            col3Tv.setText(row.getColThree());
        } else {
            col3Tv.setVisibility(View.GONE);
        }
        if (row.getColFour() != null) {
            col4Tv.setText(row.getColFour());
        } else {
            col4Tv.setVisibility(View.GONE);
        }
        if (row.getColFive() != null) {
            col5Tv.setText(row.getColFive());
        } else {
            col5Tv.setVisibility(View.GONE);
        }
        if (row.getColSix() != null) {
            col6Tv.setText(row.getColSix());
        } else {
            col6Tv.setVisibility(View.GONE);
        }
    }

    @Override
    public boolean isHeader(int itemPosition) {
        return itemPosition == 0;
    }

    private class RowViewHolder extends RecyclerView.ViewHolder {
        private TextView col1Tv, col2Tv,col3Tv, col4Tv, col5Tv, col6Tv;
        RowViewHolder(@NonNull View itemView) {
            super(itemView);
            col1Tv = itemView.findViewById(R.id.column_1);
            col2Tv = itemView.findViewById(R.id.column_2);
            col3Tv = itemView.findViewById(R.id.column_3);
            col4Tv = itemView.findViewById(R.id.column_4);
            col5Tv = itemView.findViewById(R.id.column_5);
            col6Tv = itemView.findViewById(R.id.column_6);
        }

        void bindViews(TableRow row) {
            if (row.getColOne() != null) {
                col1Tv.setText(row.getColOne());
            } else {
                col1Tv.setVisibility(View.GONE);
            }
            if (row.getColTwo() != null) {
                col2Tv.setText(row.getColTwo());
            } else {
                col2Tv.setVisibility(View.GONE);
            }
            if (row.getColThree() != null) {
                col3Tv.setText(row.getColThree());
            } else {
                col3Tv.setVisibility(View.GONE);
            }
            if (row.getColFour() != null) {
                col4Tv.setText(row.getColFour());
            } else {
                col4Tv.setVisibility(View.GONE);
            }
            if (row.getColFive() != null) {
                col5Tv.setText(row.getColFive());
            } else {
                col5Tv.setVisibility(View.GONE);
            }
            if (row.getColSix() != null) {
                col6Tv.setText(row.getColSix());
            } else {
                col6Tv.setVisibility(View.GONE);
            }
        }
    }
}
