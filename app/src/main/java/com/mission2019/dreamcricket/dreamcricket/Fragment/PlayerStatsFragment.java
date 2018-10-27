package com.mission2019.dreamcricket.dreamcricket.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mission2019.dreamcricket.dreamcricket.Adapter.StatsCategoriesRecyclerViewAdapter;
import com.mission2019.dreamcricket.dreamcricket.Common.Config;
import com.mission2019.dreamcricket.dreamcricket.Model.Schedule.SchedulePlayer;
import com.mission2019.dreamcricket.dreamcricket.Model.Schedule.ScheduleTeam;

import java.util.ArrayList;

public class PlayerStatsFragment extends Fragment {
    private ArrayList<String> mCategories = Config.playerStatsCategories;
    private StatsCategoriesRecyclerViewAdapter mCategoriesRecyclerViewAdapter;
    private RecyclerView mRecyclerView;

    private String mPlayerTeam;
    private ScheduleTeam mOppTeam;
    private String mFormat, mVenue;
    public static final String KEY_TARGET_TEAM = "KEY_TARGET_TEAM";
    public static final String KEY_OPP_TEAM = "KEY_OPP_TEAM";
    public static final String KEY_FORMAT = "KEY_FORMAT";
    public static final String KEY_VENUE = "KEY_VENUE";

    private ArrayList<String> mOppTeamSquad;
    private ArrayList<String> mOppTeamBowlingStyles;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Gson gson = new Gson();
        mPlayerTeam = getArguments().getString(KEY_TARGET_TEAM);
        mOppTeam = gson.fromJson(getArguments().getString(KEY_OPP_TEAM),
                new TypeToken<ScheduleTeam>() {}.getType());
        mFormat = getArguments().getString(KEY_FORMAT);
        mVenue = getArguments().getString(KEY_VENUE);

        mOppTeamSquad = new ArrayList<>();
        mOppTeamBowlingStyles = new ArrayList<>();
        for (SchedulePlayer player : mOppTeam.getSquad()) {
            mOppTeamSquad.add(player.getName());
            if (!mOppTeamBowlingStyles.contains(player.getBowlingStyle())) {
                mOppTeamBowlingStyles.add(player.getBowlingStyle());
            }
        }
    }
}
