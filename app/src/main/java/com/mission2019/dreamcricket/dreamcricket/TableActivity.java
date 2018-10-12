package com.mission2019.dreamcricket.dreamcricket;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mission2019.dreamcricket.dreamcricket.Adapter.TableRecyclerViewAdapter;
import com.mission2019.dreamcricket.dreamcricket.Custom.StickyHeaderItemDecoration;
import com.mission2019.dreamcricket.dreamcricket.Model.TableRow;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class TableActivity extends AppCompatActivity {
    private ArrayList<TableRow> mTableData;
    private TableRecyclerViewAdapter mRecyclerViewAdapter;
    private RecyclerView mRecyclerView;
    public static final String TABLE_DATA = "TABLE_DATA";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table);

        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<TableRow>>(){}.getType();
        mTableData = gson.fromJson(getIntent().getStringExtra(TABLE_DATA), type);

        mRecyclerView = findViewById(R.id.recyclerview_table);
        mRecyclerViewAdapter = new TableRecyclerViewAdapter(mTableData);
        mRecyclerView.setAdapter(mRecyclerViewAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(new StickyHeaderItemDecoration(mRecyclerViewAdapter));
    }
}
