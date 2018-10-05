package com.mission2019.dreamcricket.dreamcricket.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.button.MaterialButton;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mission2019.dreamcricket.dreamcricket.Adapter.TeamStatsCategoriesRecyclerViewAdapter;
import com.mission2019.dreamcricket.dreamcricket.Common.Config;
import com.mission2019.dreamcricket.dreamcricket.Model.Schedule.ScheduleMatch;
import com.mission2019.dreamcricket.dreamcricket.Activity.MatchActivity;
import com.mission2019.dreamcricket.dreamcricket.R;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class TeamsFragment extends Fragment {
    private static final String TAG = TeamsFragment.class.getSimpleName();
    private ScheduleMatch mScheduleMatch;
    private String mCurrentTeam;
    private ArrayList<String> mCategories = Config.teamStatsCategories;
    private TeamStatsCategoriesRecyclerViewAdapter mCategoriesRecyclerViewAdapter;
    public TeamsFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String matchData = getArguments().getString(MatchActivity.KEY_MATCH_DATA);

        Gson gson = new Gson();
        Type type = new TypeToken<ScheduleMatch>() {}.getType();
        mScheduleMatch = gson.fromJson(matchData, type);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.team_stats_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final MaterialButton mTeamAButton = view.findViewById(R.id.team_a_button);
        final MaterialButton mTeamBButton = view.findViewById(R.id.team_b_button);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerview_team_stats_categories);

        mTeamAButton.setText(mScheduleMatch.getTeams().get(0).getName());
        mCurrentTeam = (String) mTeamAButton.getText();
        mTeamBButton.setText(mScheduleMatch.getTeams().get(1).getName());

        mTeamAButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTeamBButton.setTextColor(getResources().getColor(R.color.colorPrimary));
                mTeamBButton.setBackgroundTintList(getResources().getColorStateList(android.R.color.white));
                mTeamAButton.setTextColor(getResources().getColor(android.R.color.white));
                mTeamAButton.setBackgroundTintList(getResources().getColorStateList(R.color.colorPrimary));
                mCurrentTeam = (String) mTeamAButton.getText();
            }
        });
        mTeamBButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTeamAButton.setTextColor(getResources().getColor(R.color.colorPrimary));
                mTeamAButton.setBackgroundTintList(getResources().getColorStateList(android.R.color.white));
                mTeamBButton.setTextColor(getResources().getColor(android.R.color.white));
                mTeamBButton.setBackgroundTintList(getResources().getColorStateList(R.color.colorPrimary));
                mCurrentTeam = (String) mTeamBButton.getText();
            }
        });

        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.HORIZONTAL));
        mCategoriesRecyclerViewAdapter = new TeamStatsCategoriesRecyclerViewAdapter(mCategories, null);
        recyclerView.setAdapter(mCategoriesRecyclerViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }
}
