package com.mission2019.dreamcricket.dreamcricket.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.button.MaterialButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mission2019.dreamcricket.dreamcricket.Activity.MatchActivity;
import com.mission2019.dreamcricket.dreamcricket.Adapter.PlayerStatsSquadRecyclerViewAdapter;
import com.mission2019.dreamcricket.dreamcricket.Custom.StickyHeaderItemDecoration;
import com.mission2019.dreamcricket.dreamcricket.Model.Schedule.ScheduleMatch;
import com.mission2019.dreamcricket.dreamcricket.Model.Schedule.SchedulePlayer;
import com.mission2019.dreamcricket.dreamcricket.Model.Schedule.ScheduleTeam;
import com.mission2019.dreamcricket.dreamcricket.R;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;

public class PlayersFragment extends Fragment implements PlayerStatsSquadRecyclerViewAdapter.onPlayerClickListener {
    private static String TAG = PlayersFragment.class.getSimpleName();
    private ScheduleMatch mScheduleMatch;
    private PlayerStatsSquadRecyclerViewAdapter mPlayerStatsSquadRecyclerViewAdapter;
    private HashMap<String, ArrayList<Object>> mPerTeamSquadDataset;
    private String mCurrentTeam;
    private ArrayList<Object> mCurrentTeamSquadData;
    private RecyclerView mRecyclerView;
    public PlayersFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String matchData = getArguments().getString(MatchActivity.KEY_MATCH_DATA);

        Gson gson = new Gson();
        Type type = new TypeToken<ScheduleMatch>() {}.getType();
        mScheduleMatch = gson.fromJson(matchData, type);
        generatePerTeamPerRoleSquad();

        mCurrentTeamSquadData = new ArrayList<>();
    }

    private void generatePerTeamPerRoleSquad() {
        HashMap<String, HashMap<String, ArrayList<SchedulePlayer>>> perTeamPerRoleSquad = new HashMap<>();
        for (ScheduleTeam scheduleTeam : mScheduleMatch.getTeams()) {
            HashMap<String, ArrayList<SchedulePlayer>> teamSquad = new HashMap<>();
            for (SchedulePlayer player : scheduleTeam.getSquad()) {
                if (teamSquad.containsKey(player.getRole())) {
                    teamSquad.get(player.getRole()).add(player);
                } else {
                    ArrayList<SchedulePlayer> rolePlayers = new ArrayList<>();
                    rolePlayers.add(player);
                    teamSquad.put(player.getRole(), rolePlayers);
                }
            }
            perTeamPerRoleSquad.put(scheduleTeam.getName(), teamSquad);
        }

        mPerTeamSquadDataset = new HashMap<>();
        for (String team : perTeamPerRoleSquad.keySet()) {
            ArrayList<Object> squad = new ArrayList<>();
            for (String role : perTeamPerRoleSquad.get(team).keySet()) {
                squad.add(role);
                squad.addAll(perTeamPerRoleSquad.get(team).get(role));
            }
            mPerTeamSquadDataset.put(team, squad);
        }
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.player_stats_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final MaterialButton mTeamAButton = view.findViewById(R.id.team_a_button);
        final MaterialButton mTeamBButton = view.findViewById(R.id.team_b_button);
        mRecyclerView = view.findViewById(R.id.recyclerview_team_squad);

        mTeamAButton.setText(mScheduleMatch.getTeams().get(0).getName());
        mCurrentTeam = (String) mTeamAButton.getText();
        mCurrentTeamSquadData.addAll(mPerTeamSquadDataset.get(mCurrentTeam));
        mTeamBButton.setText(mScheduleMatch.getTeams().get(1).getName());

        mTeamAButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTeamBButton.setTextColor(getResources().getColor(R.color.colorPrimary));
                mTeamBButton.setBackgroundTintList(getResources().getColorStateList(android.R.color.white));
                mTeamAButton.setTextColor(getResources().getColor(android.R.color.white));
                mTeamAButton.setBackgroundTintList(getResources().getColorStateList(R.color.colorPrimary));
                mCurrentTeam = (String) mTeamAButton.getText();
                mCurrentTeamSquadData.clear();
                mCurrentTeamSquadData.addAll(mPerTeamSquadDataset.get(mCurrentTeam));
                mPlayerStatsSquadRecyclerViewAdapter.notifyDataSetChanged();
                mRecyclerView.scrollToPosition(0);
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
                mCurrentTeamSquadData.clear();
                mCurrentTeamSquadData.addAll(mPerTeamSquadDataset.get(mCurrentTeam));
                mPlayerStatsSquadRecyclerViewAdapter.notifyDataSetChanged();
                mRecyclerView.scrollToPosition(0);
            }
        });

        mPlayerStatsSquadRecyclerViewAdapter = new PlayerStatsSquadRecyclerViewAdapter(mCurrentTeamSquadData, this);
        mRecyclerView.setAdapter(mPlayerStatsSquadRecyclerViewAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.addItemDecoration(new StickyHeaderItemDecoration(mPlayerStatsSquadRecyclerViewAdapter));
    }

    @Override
    public void onPlayerClick(int pos) {

    }
}
