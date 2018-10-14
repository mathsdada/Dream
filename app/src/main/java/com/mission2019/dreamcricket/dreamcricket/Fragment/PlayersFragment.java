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
import android.widget.ArrayAdapter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mission2019.dreamcricket.dreamcricket.Activity.MatchActivity;
import com.mission2019.dreamcricket.dreamcricket.Adapter.PlayerStatsSquadRecyclerViewAdapter;
import com.mission2019.dreamcricket.dreamcricket.Custom.StickyHeaderItemDecoration;
import com.mission2019.dreamcricket.dreamcricket.Model.Schedule.ScheduleMatch;
import com.mission2019.dreamcricket.dreamcricket.Model.Schedule.SchedulePlayer;
import com.mission2019.dreamcricket.dreamcricket.Model.Schedule.ScheduleTeam;
import com.mission2019.dreamcricket.dreamcricket.R;

import java.util.ArrayList;
import java.util.HashMap;

public class PlayersFragment extends Fragment implements PlayerStatsSquadRecyclerViewAdapter.onPlayerClickListener {
    private static String TAG = PlayersFragment.class.getSimpleName();

    private PlayerStatsSquadRecyclerViewAdapter mPlayerStatsSquadRecyclerViewAdapter;
    private ArrayList<Object> mTargetTeamSquadDataset;

    public static final String KEY_TARGET_TEAM = "KEY_TARGET_TEAM";
    public static final String KEY_OPP_TEAM = "KEY_OPP_TEAM";
    public static final String KEY_FORMAT = "KEY_FORMAT";
    public static final String KEY_VENUE = "KEY_VENUE";

    private ScheduleTeam mTargetTeam, mOppTeam;
    private String mFormat, mVenue;

    public PlayersFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String matchData = getArguments().getString(MatchActivity.KEY_MATCH_DATA);

        Gson gson = new Gson();
        mTargetTeam = gson.fromJson(getArguments().getString(KEY_TARGET_TEAM),
                new TypeToken<ScheduleTeam>() {}.getType());
        mOppTeam = gson.fromJson(getArguments().getString(KEY_OPP_TEAM),
                new TypeToken<ScheduleTeam>() {}.getType());
        mFormat = getArguments().getString(KEY_FORMAT);
        mVenue = getArguments().getString(KEY_VENUE);

        mTargetTeamSquadDataset = generatePerRoleSquad(mTargetTeam);
    }

    private ArrayList<Object> generatePerRoleSquad(ScheduleTeam team) {
        HashMap<String, ArrayList<SchedulePlayer>> teamSquadPerRole = new HashMap<>();
        for (SchedulePlayer player : team.getSquad()) {
            if (teamSquadPerRole.containsKey(player.getRole())) {
                teamSquadPerRole.get(player.getRole()).add(player);
            } else {
                ArrayList<SchedulePlayer> rolePlayers = new ArrayList<>();
                rolePlayers.add(player);
                teamSquadPerRole.put(player.getRole(), rolePlayers);
            }
        }
        ArrayList<Object> dataset = new ArrayList<>();
        for (String role : teamSquadPerRole.keySet()) {
            dataset.add(role);
            dataset.addAll(teamSquadPerRole.get(role));
        }
        return dataset;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.player_stats_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerview_team_squad);
        mPlayerStatsSquadRecyclerViewAdapter =
                new PlayerStatsSquadRecyclerViewAdapter(mTargetTeamSquadDataset, this);
        recyclerView.setAdapter(mPlayerStatsSquadRecyclerViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.addItemDecoration(new StickyHeaderItemDecoration(mPlayerStatsSquadRecyclerViewAdapter));
    }

    @Override
    public void onPlayerClick(int pos) {

    }
}
