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
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mission2019.dreamcricket.dreamcricket.Adapter.TeamStatsCategoriesRecyclerViewAdapter;
import com.mission2019.dreamcricket.dreamcricket.Common.Config;
import com.mission2019.dreamcricket.dreamcricket.Custom.StickyHeaderItemDecoration;
import com.mission2019.dreamcricket.dreamcricket.Model.Schedule.SchedulePlayer;
import com.mission2019.dreamcricket.dreamcricket.Model.Schedule.ScheduleTeam;
import com.mission2019.dreamcricket.dreamcricket.Model.TeamStats.TeamBattingMostRunsResponse;
import com.mission2019.dreamcricket.dreamcricket.Model.TeamStats.TeamQuery;
import com.mission2019.dreamcricket.dreamcricket.Model.TeamStats.TeamFormResponse;
import com.mission2019.dreamcricket.dreamcricket.R;
import com.mission2019.dreamcricket.dreamcricket.Rest.API;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TeamsFragment extends Fragment implements TeamStatsCategoriesRecyclerViewAdapter.OnCategoryClickListener {
    private static final String TAG = TeamsFragment.class.getSimpleName();
    private ArrayList<String> mCategories = Config.teamStatsCategories;
    private TeamStatsCategoriesRecyclerViewAdapter mCategoriesRecyclerViewAdapter;
    private RecyclerView mRecyclerView;

    private ScheduleTeam mTargetTeam, mOppTeam;
    private String mFormat, mVenue;
    public static final String KEY_TARGET_TEAM = "KEY_TARGET_TEAM";
    public static final String KEY_OPP_TEAM = "KEY_OPP_TEAM";
    public static final String KEY_FORMAT = "KEY_FORMAT";
    public static final String KEY_VENUE = "KEY_VENUE";
    private ArrayList<String> mTargetTeamSquad;
    public TeamsFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Gson gson = new Gson();
        mTargetTeam = gson.fromJson(getArguments().getString(KEY_TARGET_TEAM),
                new TypeToken<ScheduleTeam>() {}.getType());
        mOppTeam = gson.fromJson(getArguments().getString(KEY_OPP_TEAM),
                new TypeToken<ScheduleTeam>() {}.getType());
        mFormat = getArguments().getString(KEY_FORMAT);
        mVenue = getArguments().getString(KEY_VENUE);

        mTargetTeamSquad = new ArrayList<>();
        for (SchedulePlayer player : mTargetTeam.getSquad()) {
            mTargetTeamSquad.add(player.getName());
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.team_stats_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRecyclerView = view.findViewById(R.id.recyclerview_team_stats_categories);
        mCategoriesRecyclerViewAdapter = new TeamStatsCategoriesRecyclerViewAdapter(mCategories, this);
        mRecyclerView.setAdapter(mCategoriesRecyclerViewAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.addItemDecoration(new StickyHeaderItemDecoration(mCategoriesRecyclerViewAdapter));
    }

    @Override
    public void onCategoryItemClick(int pos) {
        Toast.makeText(getActivity(), "Getting Stats " + mCategories.get(pos) + " of " +
                mTargetTeam.getName(), Toast.LENGTH_SHORT).show();
        switch (mCategories.get(pos)) {
            case "Recent Match Results": {
                TeamQuery teamFormQuery = new TeamQuery(
                        mTargetTeam.getName(), mVenue, mFormat);
                API.query().getTeamRecentForm(teamFormQuery).enqueue(new Callback<TeamFormResponse>() {
                    @Override
                    public void onResponse(Call<TeamFormResponse> call, Response<TeamFormResponse> response) {
                        Gson gson = new Gson();
                        Bundle bundle = new Bundle();
                        bundle.putString(TableFragment.TABLE_TITLE, "Recent Match Results");
                        bundle.putString(TableFragment.TABLE_DATA_OVERALL,
                                gson.toJson(response.body().convertToTableRows(
                                        response.body().getTeamFormOverall())));
                        TableFragment fragment = new TableFragment();
                        fragment.setArguments(bundle);
                        getActivity().getSupportFragmentManager().beginTransaction().
                                replace(R.id.container, fragment).
                                addToBackStack(null).
                                commit();
                    }

                    @Override
                    public void onFailure(Call<TeamFormResponse> call, Throwable t) {

                    }
                });
                break;
            }
            case "Most Runs": {
                TeamQuery teamQuery = new TeamQuery(mTargetTeam.getName(), mVenue, mFormat);
                teamQuery.setSquad(mTargetTeamSquad);
                API.query().getTeamStatsBattingMostRuns(teamQuery).enqueue(new Callback<TeamBattingMostRunsResponse>() {
                    @Override
                    public void onResponse(Call<TeamBattingMostRunsResponse> call,
                                           Response<TeamBattingMostRunsResponse> response) {
                        Gson gson = new Gson();
                        Bundle bundle = new Bundle();
                        bundle.putString(TableFragment.TABLE_TITLE, "Most Runs");
                        bundle.putString(TableFragment.TABLE_DATA_OVERALL,
                                gson.toJson(response.body().convertToTableRows(
                                        response.body().getMostRunsOverall())));
                        TableFragment fragment = new TableFragment();
                        fragment.setArguments(bundle);
                        getActivity().getSupportFragmentManager().beginTransaction().
                                replace(R.id.container, fragment).
                                addToBackStack(null).
                                commit();
                    }

                    @Override
                    public void onFailure(Call<TeamBattingMostRunsResponse> call, Throwable t) {

                    }
                });
                break;
            }
        }
    }
}
