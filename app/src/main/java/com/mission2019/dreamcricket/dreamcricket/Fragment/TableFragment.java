package com.mission2019.dreamcricket.dreamcricket.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mission2019.dreamcricket.dreamcricket.Adapter.MatchScoresRecyclerViewAdapter;
import com.mission2019.dreamcricket.dreamcricket.Adapter.TableRecyclerViewAdapter;
import com.mission2019.dreamcricket.dreamcricket.Custom.StickyHeaderItemDecoration;
import com.mission2019.dreamcricket.dreamcricket.Model.TableRow;
import com.mission2019.dreamcricket.dreamcricket.Model.TeamStats.RecentMatches.MatchScore;
import com.mission2019.dreamcricket.dreamcricket.R;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class TableFragment extends Fragment implements View.OnClickListener {
    private ArrayList<TableRow> mTableRows = null;
    private ArrayList<MatchScore> mMatchScores = null;
    private String mDataType;
    private String mTableTitle;
    private TableRecyclerViewAdapter mRecyclerViewAdapter;
    private MatchScoresRecyclerViewAdapter mMatchScoresRecyclerViewAdapter;
    private RecyclerView mRecyclerView;
    private TextView mCloseTV;
    public static final String KEY_OVERALL_STATS = "KEY_OVERALL_STATS";
    public static final String KEY_AT_VENUE_STATS = "KEY_AT_VENUE_STATS";
    public static final String KEY_TITLE = "KEY_TITLE";
    public static final String KEY_DATA_TYPE = "KEY_DATA_TYPE";
    public static final String DATA_TYPE_TABLE_ROWS = "DATA_TYPE_TABLE_ROWS";
    public static final String DATA_TYPE_MATCH_SCORES = "DATA_TYPE_MATCH_SCORES";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Gson gson = new Gson();
        mDataType = getArguments().getString(KEY_DATA_TYPE);
        mTableTitle = getArguments().getString(KEY_TITLE);
        if (mDataType.equals(DATA_TYPE_TABLE_ROWS)) {
            Type type = new TypeToken<ArrayList<TableRow>>(){}.getType();
            mTableRows = gson.fromJson(getArguments().getString(KEY_OVERALL_STATS), type);
        } else {
            Type type = new TypeToken<ArrayList<MatchScore>>(){}.getType();
            mMatchScores = gson.fromJson(getArguments().getString(KEY_OVERALL_STATS), type);
        }
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

        if (mDataType.equals(DATA_TYPE_TABLE_ROWS)) {
            mRecyclerViewAdapter = new TableRecyclerViewAdapter(mTableRows);
            mRecyclerView.setAdapter(mRecyclerViewAdapter);
            mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            mRecyclerView.addItemDecoration(new StickyHeaderItemDecoration(mRecyclerViewAdapter));
        } else {
            mMatchScoresRecyclerViewAdapter = new MatchScoresRecyclerViewAdapter(mMatchScores);
            mRecyclerView.setAdapter(mMatchScoresRecyclerViewAdapter);
            mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.HORIZONTAL));
        }

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
