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

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mission2019.dreamcricket.dreamcricket.Adapter.StatsCategoriesRecyclerViewAdapter;
import com.mission2019.dreamcricket.dreamcricket.Common.Config;
import com.mission2019.dreamcricket.dreamcricket.Custom.StickyHeaderItemDecoration;
import com.mission2019.dreamcricket.dreamcricket.Model.PlayerStats.Batting.BattingRecentFormResponse;
import com.mission2019.dreamcricket.dreamcricket.Model.PlayerStats.Batting.BattingVsBowlerResponse;
import com.mission2019.dreamcricket.dreamcricket.Model.PlayerStats.Batting.BattingVsBowlingStylesResponse;
import com.mission2019.dreamcricket.dreamcricket.Model.PlayerStats.Bowling.BowlingRecentForm;
import com.mission2019.dreamcricket.dreamcricket.Model.PlayerStats.Bowling.BowlingRecentFormResponse;
import com.mission2019.dreamcricket.dreamcricket.Model.PlayerStats.Bowling.BowlingVsBatsmanResponse;
import com.mission2019.dreamcricket.dreamcricket.Model.PlayerStats.Bowling.BowlingVsBattingStyles;
import com.mission2019.dreamcricket.dreamcricket.Model.PlayerStats.Bowling.BowlingVsBattingStylesResponse;
import com.mission2019.dreamcricket.dreamcricket.Model.PlayerStats.PlayerQuery;
import com.mission2019.dreamcricket.dreamcricket.Model.Schedule.SchedulePlayer;
import com.mission2019.dreamcricket.dreamcricket.Model.Schedule.ScheduleTeam;
import com.mission2019.dreamcricket.dreamcricket.R;
import com.mission2019.dreamcricket.dreamcricket.Rest.API;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.mission2019.dreamcricket.dreamcricket.Common.Utility.StartTableActivity;

public class PlayerStatsCategoriesFragment extends Fragment implements StatsCategoriesRecyclerViewAdapter.OnCategoryClickListener {
    private ArrayList<String> mCategories = Config.playerStatsCategories;
    private StatsCategoriesRecyclerViewAdapter mCategoriesRecyclerViewAdapter;
    private RecyclerView mRecyclerView;

    private String mPlayer;
    private String mPlayerTeam;
    private ScheduleTeam mOppTeam;
    private String mFormat, mVenue;
    public static final String KEY_PLAYER = "KEY_PLAYER";
    public static final String KEY_TARGET_TEAM = "KEY_TARGET_TEAM";
    public static final String KEY_OPP_TEAM = "KEY_OPP_TEAM";
    public static final String KEY_FORMAT = "KEY_FORMAT";
    public static final String KEY_VENUE = "KEY_VENUE";

    private ArrayList<String> mOppTeamSquad;
    private ArrayList<String> mOppTeamBowlingStyles;
    private ArrayList<String> mOppTeamBattingStyles;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Gson gson = new Gson();
        mPlayer = getArguments().getString(KEY_PLAYER);
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
            if (!mOppTeamBowlingStyles.contains(player.getBattingStyle())) {
                mOppTeamBattingStyles.add(player.getBattingStyle());
            }
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.stats_categories_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mRecyclerView = view.findViewById(R.id.recyclerview_stats_categories);
        mCategoriesRecyclerViewAdapter = new StatsCategoriesRecyclerViewAdapter(mCategories, this);
        mRecyclerView.setAdapter(mCategoriesRecyclerViewAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.addItemDecoration(new StickyHeaderItemDecoration(mCategoriesRecyclerViewAdapter));
    }

    @Override
    public void onCategoryItemClick(int pos) {
        switch (mCategories.get(pos)) {
            case Config.PLAYER_BATTING_RECENT_FORM: {
                PlayerQuery playerQuery = new PlayerQuery(mPlayer, mVenue, mFormat);
                API.query().getPlayerRecentBattingForm(playerQuery).enqueue(new Callback<BattingRecentFormResponse>() {
                    @Override
                    public void onResponse(Call<BattingRecentFormResponse> call, Response<BattingRecentFormResponse> response) {
                        if (response.code() != 404) {
                            StartTableActivity(getActivity(), Config.PLAYER_BATTING_RECENT_FORM,
                                    response.body().convertToTableRows(response.body().getOverallStats()));
                        }
                    }

                    @Override
                    public void onFailure(Call<BattingRecentFormResponse> call, Throwable t) {

                    }
                });
                break;
            }
            case Config.PLAYER_BATTING_PERF_VS_BOWLING_STYLES: {
                PlayerQuery playerQuery = new PlayerQuery(mPlayer, mVenue, mFormat);
                playerQuery.setList1(mOppTeamBowlingStyles);
                API.query().getPlayerBattingVsBowlingStyles(playerQuery).enqueue(new Callback<BattingVsBowlingStylesResponse>() {
                    @Override
                    public void onResponse(Call<BattingVsBowlingStylesResponse> call, Response<BattingVsBowlingStylesResponse> response) {
                        if (response.code() != 404) {
                            StartTableActivity(getActivity(), Config.PLAYER_BATTING_PERF_VS_BOWLING_STYLES,
                                    response.body().convertToTableRows(response.body().getOverallStats()));
                        }
                    }

                    @Override
                    public void onFailure(Call<BattingVsBowlingStylesResponse> call, Throwable t) {

                    }
                });
                break;
            }
            case Config.PLAYER_BATTING_PERF_VS_BOWLERS: {
                PlayerQuery playerQuery = new PlayerQuery(mPlayer, mVenue, mFormat);
                playerQuery.setList1(mOppTeamSquad);
                API.query().getPlayerBattingVsBowlers(playerQuery).enqueue(new Callback<BattingVsBowlerResponse>() {
                    @Override
                    public void onResponse(Call<BattingVsBowlerResponse> call, Response<BattingVsBowlerResponse> response) {
                        if (response.code() != 404) {
                            StartTableActivity(getActivity(), Config.PLAYER_BATTING_PERF_VS_BOWLERS,
                                    response.body().convertToTableRows(response.body().getOverallStats()));
                        }
                    }

                    @Override
                    public void onFailure(Call<BattingVsBowlerResponse> call, Throwable t) {

                    }
                });
                break;
            }
            case Config.PLAYER_BOWLING_RECENT_FORM: {
                PlayerQuery playerQuery = new PlayerQuery(mPlayer, mVenue, mFormat);
                API.query().getPlayerRecentBowlingForm(playerQuery).enqueue(new Callback<BowlingRecentFormResponse>() {
                    @Override
                    public void onResponse(Call<BowlingRecentFormResponse> call, Response<BowlingRecentFormResponse> response) {
                        if (response.code() != 404) {
                            StartTableActivity(getActivity(), Config.PLAYER_BOWLING_RECENT_FORM,
                                    response.body().convertToTableRows(response.body().getOverallStats()));
                        }
                    }

                    @Override
                    public void onFailure(Call<BowlingRecentFormResponse> call, Throwable t) {

                    }
                });
                break;
            }
            case Config.PLAYER_BOWLING_PERF_VS_BATTING_STYLES: {
                PlayerQuery playerQuery = new PlayerQuery(mPlayer, mVenue, mFormat);
                playerQuery.setList1(mOppTeamBattingStyles);
                API.query().getPlayerBowlingVsBattingStyles(playerQuery).enqueue(new Callback<BowlingVsBattingStylesResponse>() {
                    @Override
                    public void onResponse(Call<BowlingVsBattingStylesResponse> call, Response<BowlingVsBattingStylesResponse> response) {
                        if (response.code() != 404) {
                            StartTableActivity(getActivity(), Config.PLAYER_BOWLING_PERF_VS_BATTING_STYLES,
                                    response.body().convertToTableRows(response.body().getOverallStats()));
                        }
                    }

                    @Override
                    public void onFailure(Call<BowlingVsBattingStylesResponse> call, Throwable t) {

                    }
                });
                break;
            }
            case Config.PLAYER_BOWLING_PERF_VS_BATSMEN: {
                PlayerQuery playerQuery = new PlayerQuery(mPlayer, mVenue, mFormat);
                playerQuery.setList1(mOppTeamBattingStyles);
                API.query().getPlayerBowlingVsBatsmen(playerQuery).enqueue(new Callback<BowlingVsBatsmanResponse>() {
                    @Override
                    public void onResponse(Call<BowlingVsBatsmanResponse> call, Response<BowlingVsBatsmanResponse> response) {
                        if (response.code() != 404) {
                            StartTableActivity(getActivity(), Config.PLAYER_BOWLING_PERF_VS_BATSMEN,
                                    response.body().convertToTableRows(response.body().getOverallStats()));
                        }
                    }

                    @Override
                    public void onFailure(Call<BowlingVsBatsmanResponse> call, Throwable t) {

                    }
                });
                break;
            }
        }
    }
}
