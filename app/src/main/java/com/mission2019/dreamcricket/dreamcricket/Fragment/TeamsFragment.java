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
import com.mission2019.dreamcricket.dreamcricket.Model.TableRow;
import com.mission2019.dreamcricket.dreamcricket.Model.TeamStats.Batting.BattingBestAverageResponse;
import com.mission2019.dreamcricket.dreamcricket.Model.TeamStats.Batting.BattingBestStrikeRateResponse;
import com.mission2019.dreamcricket.dreamcricket.Model.TeamStats.Batting.BattingMost100sResponse;
import com.mission2019.dreamcricket.dreamcricket.Model.TeamStats.Batting.BattingMost4sResponse;
import com.mission2019.dreamcricket.dreamcricket.Model.TeamStats.Batting.BattingMost50sResponse;
import com.mission2019.dreamcricket.dreamcricket.Model.TeamStats.Batting.BattingMost6sResponse;
import com.mission2019.dreamcricket.dreamcricket.Model.TeamStats.Batting.BattingHighScoresResponse;
import com.mission2019.dreamcricket.dreamcricket.Model.TeamStats.Batting.BattingMostDucksResponse;
import com.mission2019.dreamcricket.dreamcricket.Model.TeamStats.Batting.BattingMostRunsResponse;
import com.mission2019.dreamcricket.dreamcricket.Model.TeamStats.Bowling.BowlingBestBowlingFigureResponse;
import com.mission2019.dreamcricket.dreamcricket.Model.TeamStats.Bowling.BowlingBestEconomyResponse;
import com.mission2019.dreamcricket.dreamcricket.Model.TeamStats.Bowling.BowlingBestStrikeRateResponse;
import com.mission2019.dreamcricket.dreamcricket.Model.TeamStats.Bowling.BowlingMost4PlusWicketsResponse;
import com.mission2019.dreamcricket.dreamcricket.Model.TeamStats.Bowling.BowlingMost5PlusWicketsResponse;
import com.mission2019.dreamcricket.dreamcricket.Model.TeamStats.Bowling.BowlingMostMaidensResponse;
import com.mission2019.dreamcricket.dreamcricket.Model.TeamStats.Bowling.BowlingMostRunsConcededResponse;
import com.mission2019.dreamcricket.dreamcricket.Model.TeamStats.Bowling.BowlingMostWicketsResponse;
import com.mission2019.dreamcricket.dreamcricket.Model.TeamStats.HeadToHead.HeadToHeadRunsVsBowlersResponse;
import com.mission2019.dreamcricket.dreamcricket.Model.TeamStats.HeadToHead.HeadToHeadRunsVsBowlingStylesResponse;
import com.mission2019.dreamcricket.dreamcricket.Model.TeamStats.TeamQuery;
import com.mission2019.dreamcricket.dreamcricket.Model.TeamStats.RecentMatches.TeamFormResponse;
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
    private ArrayList<String> mOppTeamSquad;
    private ArrayList<String> mOppTeamBowlingStyles;
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
        mOppTeamSquad = new ArrayList<>();
        mOppTeamBowlingStyles = new ArrayList<>();
        for (SchedulePlayer player : mOppTeam.getSquad()) {
            mOppTeamSquad.add(player.getName());
            if (!mOppTeamBowlingStyles.contains(player.getBowlingStyle())) {
                mOppTeamBowlingStyles.add(player.getBowlingStyle());
            }
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
    public void onCategoryItemClick(final int pos) {
        switch (mCategories.get(pos)) {
            case Config.TEAM_BATTING_FORM: {
              TeamQuery teamFormQuery = new TeamQuery(
                        mTargetTeam.getName(), mVenue, mFormat);
                API.query().getTeamRecentForm(teamFormQuery).enqueue(new Callback<TeamFormResponse>() {
                    @Override
                    public void onResponse(Call<TeamFormResponse> call, Response<TeamFormResponse> response) {
                        if (response.code() != 404) {
                            StartTableActivity(Config.TEAM_BATTING_FORM, response.body().convertToTableRows(
                                    response.body().getOverallStats()));
                        } else {
                            Toast.makeText(getActivity(), "Stats Not Available : " + mCategories.get(pos) + " of " +
                                    mTargetTeam.getName(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<TeamFormResponse> call, Throwable t) {

                    }
                });
                break;
            }
            case Config.TEAM_BATTING_MOST_RUNS: {
                TeamQuery teamQuery = new TeamQuery(mTargetTeam.getName(), mVenue, mFormat);
                teamQuery.setSquad(mTargetTeamSquad);
                API.query().getTeamStatsBattingMostRuns(teamQuery).enqueue(new Callback<BattingMostRunsResponse>() {
                    @Override
                    public void onResponse(Call<BattingMostRunsResponse> call,
                                           Response<BattingMostRunsResponse> response) {
                        if (response.code() != 404) {
                            StartTableActivity(Config.TEAM_BATTING_MOST_RUNS, response.body().convertToTableRows(
                                    response.body().getOverallStats()));
                        } else {
                            Toast.makeText(getActivity(), "Stats Not Available : " + mCategories.get(pos) + " of " +
                                    mTargetTeam.getName(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<BattingMostRunsResponse> call, Throwable t) {

                    }
                });
                break;
            }
            case Config.TEAM_BATTING_BEST_AVG: {
                TeamQuery teamQuery = new TeamQuery(mTargetTeam.getName(), mVenue, mFormat);
                teamQuery.setSquad(mTargetTeamSquad);
                API.query().getBestBatAverage(teamQuery).enqueue(new Callback<BattingBestAverageResponse>() {
                    @Override
                    public void onResponse(Call<BattingBestAverageResponse> call, Response<BattingBestAverageResponse> response) {
                        if (response.code() != 404) {
                            StartTableActivity(Config.TEAM_BATTING_BEST_AVG, response.body().convertToTableRows(
                                    response.body().getOverallStats()));
                        } else {
                            Toast.makeText(getActivity(), "Stats Not Available : " + mCategories.get(pos) + " of " +
                                    mTargetTeam.getName(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<BattingBestAverageResponse> call, Throwable t) {

                    }
                });
                break;
            }
            case Config.TEAM_BATTING_BEST_SR: {
                TeamQuery teamQuery = new TeamQuery(mTargetTeam.getName(), mVenue, mFormat);
                teamQuery.setSquad(mTargetTeamSquad);
                API.query().getBestBatStrikeRate(teamQuery).enqueue(new Callback<BattingBestStrikeRateResponse>() {
                    @Override
                    public void onResponse(Call<BattingBestStrikeRateResponse> call, Response<BattingBestStrikeRateResponse> response) {
                        if (response.code() != 404) {
                            StartTableActivity(Config.TEAM_BATTING_BEST_SR, response.body().convertToTableRows(
                                    response.body().getOverallStats()));
                        } else {
                            Toast.makeText(getActivity(), "Stats Not Available : " + mCategories.get(pos) + " of " +
                                    mTargetTeam.getName(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<BattingBestStrikeRateResponse> call, Throwable t) {

                    }
                });
                break;
            }
            case Config.TEAM_BATTING_MOST_DUCKS: {
                TeamQuery teamQuery = new TeamQuery(mTargetTeam.getName(), mVenue, mFormat);
                teamQuery.setSquad(mTargetTeamSquad);
                API.query().getBattingMostDucks(teamQuery).enqueue(new Callback<BattingMostDucksResponse>() {
                    @Override
                    public void onResponse(Call<BattingMostDucksResponse> call, Response<BattingMostDucksResponse> response) {
                        if (response.code() != 404) {
                            StartTableActivity(Config.TEAM_BATTING_MOST_DUCKS, response.body().convertToTableRows(
                                    response.body().getOverallStats()));
                        } else {
                            Toast.makeText(getActivity(), "Stats Not Available : " + mCategories.get(pos) + " of " +
                                    mTargetTeam.getName(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<BattingMostDucksResponse> call, Throwable t) {

                    }
                });
                break;
            }
            case Config.TEAM_BATTING_MOST_50s: {
                TeamQuery teamQuery = new TeamQuery(mTargetTeam.getName(), mVenue, mFormat);
                teamQuery.setSquad(mTargetTeamSquad);
                API.query().getBattingMost50s(teamQuery).enqueue(new Callback<BattingMost50sResponse>() {
                    @Override
                    public void onResponse(Call<BattingMost50sResponse> call, Response<BattingMost50sResponse> response) {
                        if (response.code() != 404) {
                            StartTableActivity(Config.TEAM_BATTING_MOST_50s, response.body().convertToTableRows(
                                    response.body().getOverallStats()));
                        } else {
                            Toast.makeText(getActivity(), "Stats Not Available : " + mCategories.get(pos) + " of " +
                                    mTargetTeam.getName(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<BattingMost50sResponse> call, Throwable t) {

                    }
                });
                break;
            }
            case Config.TEAM_BATTING_MOST_100s: {
                TeamQuery teamQuery = new TeamQuery(mTargetTeam.getName(), mVenue, mFormat);
                teamQuery.setSquad(mTargetTeamSquad);
                API.query().getBattingMost100s(teamQuery).enqueue(new Callback<BattingMost100sResponse>() {
                    @Override
                    public void onResponse(Call<BattingMost100sResponse> call, Response<BattingMost100sResponse> response) {
                        if (response.code() != 404) {
                            StartTableActivity(Config.TEAM_BATTING_MOST_100s, response.body().convertToTableRows(
                                    response.body().getOverallStats()));
                        } else {
                            Toast.makeText(getActivity(), "Stats Not Available : " + mCategories.get(pos) + " of " +
                                    mTargetTeam.getName(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<BattingMost100sResponse> call, Throwable t) {

                    }
                });
                break;
            }
            case Config.TEAM_BATTING_MOST_4s: {
                TeamQuery teamQuery = new TeamQuery(mTargetTeam.getName(), mVenue, mFormat);
                teamQuery.setSquad(mTargetTeamSquad);
                API.query().getBattingMost4s(teamQuery).enqueue(new Callback<BattingMost4sResponse>() {
                    @Override
                    public void onResponse(Call<BattingMost4sResponse> call, Response<BattingMost4sResponse> response) {
                        if (response.code() != 404) {
                            StartTableActivity(Config.TEAM_BATTING_MOST_4s, response.body().convertToTableRows(
                                    response.body().getOverallStats()));
                        } else {
                            Toast.makeText(getActivity(), "Stats Not Available : " + mCategories.get(pos) + " of " +
                                    mTargetTeam.getName(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<BattingMost4sResponse> call, Throwable t) {

                    }
                });
                break;
            }
            case Config.TEAM_BATTING_MOST_6s: {
                TeamQuery teamQuery = new TeamQuery(mTargetTeam.getName(), mVenue, mFormat);
                teamQuery.setSquad(mTargetTeamSquad);
                API.query().getBattingMost6s(teamQuery).enqueue(new Callback<BattingMost6sResponse>() {
                    @Override
                    public void onResponse(Call<BattingMost6sResponse> call, Response<BattingMost6sResponse> response) {
                        if (response.code() != 404) {
                            StartTableActivity(Config.TEAM_BATTING_MOST_6s, response.body().convertToTableRows(
                                    response.body().getOverallStats()));
                        } else {
                            Toast.makeText(getActivity(), "Stats Not Available : " + mCategories.get(pos) + " of " +
                                    mTargetTeam.getName(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<BattingMost6sResponse> call, Throwable t) {

                    }
                });
                break;
            }
            case Config.TEAM_BATTING_HIGH_SCORES: {
                TeamQuery teamQuery = new TeamQuery(mTargetTeam.getName(), mVenue, mFormat);
                teamQuery.setSquad(mTargetTeamSquad);
                API.query().getBattingHighScores(teamQuery).enqueue(new Callback<BattingHighScoresResponse>() {
                    @Override
                    public void onResponse(Call<BattingHighScoresResponse> call, Response<BattingHighScoresResponse> response) {
                        if (response.code() != 404) {
                            StartTableActivity(Config.TEAM_BATTING_HIGH_SCORES, response.body().convertToTableRows(
                                    response.body().getOverallStats()));
                        } else {
                            Toast.makeText(getActivity(), "Stats Not Available : " + mCategories.get(pos) + " of " +
                                    mTargetTeam.getName(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<BattingHighScoresResponse> call, Throwable t) {

                    }
                });
                break;
            }
            case Config.TEAM_BOWLING_MOST_WICKETS: {
                TeamQuery teamQuery = new TeamQuery(mTargetTeam.getName(), mVenue, mFormat);
                teamQuery.setSquad(mTargetTeamSquad);
                API.query().getBowlingMostWickets(teamQuery).enqueue(new Callback<BowlingMostWicketsResponse>() {
                    @Override
                    public void onResponse(Call<BowlingMostWicketsResponse> call, Response<BowlingMostWicketsResponse> response) {
                        if (response.code() != 404) {
                            StartTableActivity(Config.TEAM_BOWLING_MOST_WICKETS, response.body().convertToTableRows(
                                    response.body().getOverallStats()));
                        } else {
                            Toast.makeText(getActivity(), "Stats Not Available : " + mCategories.get(pos) + " of " +
                                    mTargetTeam.getName(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<BowlingMostWicketsResponse> call, Throwable t) {

                    }
                });
                break;
            }
            case Config.TEAM_BOWLING_BEST_ECO: {
                TeamQuery teamQuery = new TeamQuery(mTargetTeam.getName(), mVenue, mFormat);
                teamQuery.setSquad(mTargetTeamSquad);
                API.query().getBowlingBestEconomy(teamQuery).enqueue(new Callback<BowlingBestEconomyResponse>() {
                    @Override
                    public void onResponse(Call<BowlingBestEconomyResponse> call, Response<BowlingBestEconomyResponse> response) {
                        if (response.code() != 404) {
                            StartTableActivity(Config.TEAM_BOWLING_BEST_ECO, response.body().convertToTableRows(
                                    response.body().getOverallStats()));
                        } else {
                            Toast.makeText(getActivity(), "Stats Not Available : " + mCategories.get(pos) + " of " +
                                    mTargetTeam.getName(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<BowlingBestEconomyResponse> call, Throwable t) {

                    }
                });
                break;
            }
            case Config.TEAM_BOWLING_BEST_SR: {
                TeamQuery teamQuery = new TeamQuery(mTargetTeam.getName(), mVenue, mFormat);
                teamQuery.setSquad(mTargetTeamSquad);
                API.query().getBowlingBestStrikeRate(teamQuery).enqueue(new Callback<BowlingBestStrikeRateResponse>() {
                    @Override
                    public void onResponse(Call<BowlingBestStrikeRateResponse> call, Response<BowlingBestStrikeRateResponse> response) {
                        if (response.code() != 404) {
                            StartTableActivity(Config.TEAM_BOWLING_BEST_SR, response.body().convertToTableRows(
                                    response.body().getOverallStats()));
                        } else {
                            Toast.makeText(getActivity(), "Stats Not Available : " + mCategories.get(pos) + " of " +
                                    mTargetTeam.getName(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<BowlingBestStrikeRateResponse> call, Throwable t) {

                    }
                });
                break;
            }
            case Config.TEAM_BOWLING_MOST_4PLUS_WKTS: {
                TeamQuery teamQuery = new TeamQuery(mTargetTeam.getName(), mVenue, mFormat);
                teamQuery.setSquad(mTargetTeamSquad);
                API.query().getBowlingMost4PlusWickets(teamQuery).enqueue(new Callback<BowlingMost4PlusWicketsResponse>() {
                    @Override
                    public void onResponse(Call<BowlingMost4PlusWicketsResponse> call, Response<BowlingMost4PlusWicketsResponse> response) {
                        if (response.code() != 404) {
                            StartTableActivity(Config.TEAM_BOWLING_MOST_4PLUS_WKTS, response.body().convertToTableRows(
                                    response.body().getOverallStats()));
                        } else {
                            Toast.makeText(getActivity(), "Stats Not Available : " + mCategories.get(pos) + " of " +
                                    mTargetTeam.getName(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<BowlingMost4PlusWicketsResponse> call, Throwable t) {

                    }
                });
                break;
            }
            case Config.TEAM_BOWLING_MOST_5PLUS_WKTS: {
                TeamQuery teamQuery = new TeamQuery(mTargetTeam.getName(), mVenue, mFormat);
                teamQuery.setSquad(mTargetTeamSquad);
                API.query().getBowlingMost5PlusWickets(teamQuery).enqueue(new Callback<BowlingMost5PlusWicketsResponse>() {
                    @Override
                    public void onResponse(Call<BowlingMost5PlusWicketsResponse> call, Response<BowlingMost5PlusWicketsResponse> response) {
                        if (response.code() != 404) {
                            StartTableActivity(Config.TEAM_BOWLING_MOST_5PLUS_WKTS, response.body().convertToTableRows(
                                    response.body().getOverallStats()));
                        } else {
                            Toast.makeText(getActivity(), "Stats Not Available : " + mCategories.get(pos) + " of " +
                                    mTargetTeam.getName(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<BowlingMost5PlusWicketsResponse> call, Throwable t) {

                    }
                });
                break;
            }
            case Config.TEAM_BOWLING_MOST_MAIDENS: {
                TeamQuery teamQuery = new TeamQuery(mTargetTeam.getName(), mVenue, mFormat);
                teamQuery.setSquad(mTargetTeamSquad);
                API.query().getBowlingMostMaidens(teamQuery).enqueue(new Callback<BowlingMostMaidensResponse>() {
                    @Override
                    public void onResponse(Call<BowlingMostMaidensResponse> call, Response<BowlingMostMaidensResponse> response) {
                        if (response.code() != 404) {
                            StartTableActivity(Config.TEAM_BOWLING_MOST_MAIDENS, response.body().convertToTableRows(
                                    response.body().getOverallStats()));
                        } else {
                            Toast.makeText(getActivity(), "Stats Not Available : " + mCategories.get(pos) + " of " +
                                    mTargetTeam.getName(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<BowlingMostMaidensResponse> call, Throwable t) {

                    }
                });
                break;
            }
            case Config.TEAM_BOWLING_BEST_INN_BOWLING: {
                TeamQuery teamQuery = new TeamQuery(mTargetTeam.getName(), mVenue, mFormat);
                teamQuery.setSquad(mTargetTeamSquad);
                API.query().getBowlingBestFiguresInInnings(teamQuery).enqueue(new Callback<BowlingBestBowlingFigureResponse>() {
                    @Override
                    public void onResponse(Call<BowlingBestBowlingFigureResponse> call, Response<BowlingBestBowlingFigureResponse> response) {
                        if (response.code() != 404) {
                            StartTableActivity(Config.TEAM_BOWLING_BEST_INN_BOWLING, response.body().convertToTableRows(
                                    response.body().getOverallStats()));
                        } else {
                            Toast.makeText(getActivity(), "Stats Not Available : " + mCategories.get(pos) + " of " +
                                    mTargetTeam.getName(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<BowlingBestBowlingFigureResponse> call, Throwable t) {

                    }
                });
                break;
            }
            case Config.TEAM_BOWLING_MOST_RUNS_CONCEDED_INN: {
                TeamQuery teamQuery = new TeamQuery(mTargetTeam.getName(), mVenue, mFormat);
                teamQuery.setSquad(mTargetTeamSquad);
                API.query().getBowlingMostRunsConcededInInnings(teamQuery).enqueue(new Callback<BowlingMostRunsConcededResponse>() {
                    @Override
                    public void onResponse(Call<BowlingMostRunsConcededResponse> call, Response<BowlingMostRunsConcededResponse> response) {
                        if (response.code() != 404) {
                            StartTableActivity(Config.TEAM_BOWLING_MOST_RUNS_CONCEDED_INN, response.body().convertToTableRows(
                                    response.body().getOverallStats()));
                        } else {
                            Toast.makeText(getActivity(), "Stats Not Available : " + mCategories.get(pos) + " of " +
                                    mTargetTeam.getName(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<BowlingMostRunsConcededResponse> call, Throwable t) {

                    }
                });
                break;
            }
            case Config.TEAM_HEAD_TO_HEAD_RUNS_AGAINST_BOWLING_STYLES: {
                TeamQuery teamQuery = new TeamQuery(mTargetTeam.getName(), mVenue, mFormat);
                teamQuery.setSquad(mTargetTeamSquad);
                teamQuery.setSquadExtra(mOppTeamBowlingStyles);
                API.query().getHeadToHeadRunsVsBowlingStyles(teamQuery).enqueue(new Callback<HeadToHeadRunsVsBowlingStylesResponse>() {
                    @Override
                    public void onResponse(Call<HeadToHeadRunsVsBowlingStylesResponse> call, Response<HeadToHeadRunsVsBowlingStylesResponse> response) {
                        if (response.code() != 404) {
                            StartTableActivity(Config.TEAM_HEAD_TO_HEAD_RUNS_AGAINST_BOWLING_STYLES, response.body().convertToTableRows(
                                    response.body().getOverallStats()));
                        } else {
                            Toast.makeText(getActivity(), "Stats Not Available : " + mCategories.get(pos) + " of " +
                                    mTargetTeam.getName(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<HeadToHeadRunsVsBowlingStylesResponse> call, Throwable t) {

                    }
                });
                break;
            }
            case Config.TEAM_HEAD_TO_HEAD_RUNS_AGAINST_BOWLERS: {
                TeamQuery teamQuery = new TeamQuery(mTargetTeam.getName(), mVenue, mFormat);
                teamQuery.setSquad(mTargetTeamSquad);
                teamQuery.setOppTeam(mOppTeam.getName());
                teamQuery.setSquadExtra(mOppTeamSquad);
                API.query().getHeadToHeadRunsVsBowlers(teamQuery).enqueue(new Callback<HeadToHeadRunsVsBowlersResponse>() {
                    @Override
                    public void onResponse(Call<HeadToHeadRunsVsBowlersResponse> call, Response<HeadToHeadRunsVsBowlersResponse> response) {
                        if (response.code() != 404) {
                            StartTableActivity(Config.TEAM_HEAD_TO_HEAD_RUNS_AGAINST_BOWLERS, response.body().convertToTableRows(
                                    response.body().getOverallStats()));
                        } else {
                            Toast.makeText(getActivity(), "Stats Not Available : " + mCategories.get(pos) + " of " +
                                    mTargetTeam.getName(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<HeadToHeadRunsVsBowlersResponse> call, Throwable t) {

                    }
                });
                break;
            }

        }
    }

    private void StartTableActivity(String title, ArrayList<TableRow> tableRows) {
        Gson gson = new Gson();
        Bundle bundle = new Bundle();
        bundle.putString(TableFragment.TABLE_TITLE, title);
        bundle.putString(TableFragment.TABLE_DATA_OVERALL,
                gson.toJson(tableRows));
        TableFragment fragment = new TableFragment();
        fragment.setArguments(bundle);
        getActivity().getSupportFragmentManager().beginTransaction().
                replace(R.id.container, fragment).
                addToBackStack(null).
                commit();
    }
}
