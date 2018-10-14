package com.mission2019.dreamcricket.dreamcricket.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mission2019.dreamcricket.dreamcricket.Adapter.TableRecyclerViewAdapter;
import com.mission2019.dreamcricket.dreamcricket.Custom.StickyHeaderItemDecoration;
import com.mission2019.dreamcricket.dreamcricket.Model.TableRow;
import com.mission2019.dreamcricket.dreamcricket.R;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class TableFragment extends Fragment implements View.OnClickListener {
    private ArrayList<TableRow> mTableRows;
    private String mTableTitle;
    private TableRecyclerViewAdapter mRecyclerViewAdapter;
    private RecyclerView mRecyclerView;
    private TextView mCloseTV;
    public static final String TABLE_DATA_OVERALL = "TABLE_DATA_OVERALL";
    public static final String TABLE_DATA_AT_VENUE = "TABLE_DATA_AT_VENUE";
    public static final String TABLE_TITLE = "TABLE_TITLE";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<TableRow>>(){}.getType();
        mTableRows = gson.fromJson(getArguments().getString(TABLE_DATA_OVERALL), type);
        mTableTitle = getArguments().getString(TABLE_TITLE);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.table_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRecyclerView = view.findViewById(R.id.recyclerview_table);
        mCloseTV = view.findViewById(R.id.close_tv);
        TextView textView = view.findViewById(R.id.stats_title_tv);
        textView.setText(mTableTitle);

        mRecyclerViewAdapter = new TableRecyclerViewAdapter(mTableRows);
        mRecyclerView.setAdapter(mRecyclerViewAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.addItemDecoration(new StickyHeaderItemDecoration(mRecyclerViewAdapter));

        mCloseTV.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (getFragmentManager().getBackStackEntryCount() != 0) {
            getFragmentManager().popBackStack();
        }
        getActivity().getSupportFragmentManager().beginTransaction().remove(this).commit();
    }
}
