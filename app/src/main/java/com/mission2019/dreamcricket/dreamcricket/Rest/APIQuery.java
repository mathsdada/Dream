package com.mission2019.dreamcricket.dreamcricket.Rest;

import com.mission2019.dreamcricket.dreamcricket.Model.Schedule.ScheduleResponse;
import com.mission2019.dreamcricket.dreamcricket.Model.TeamStats.BattingBestStrikeRateResponse;
import com.mission2019.dreamcricket.dreamcricket.Model.TeamStats.BattingMost100sResponse;
import com.mission2019.dreamcricket.dreamcricket.Model.TeamStats.BattingMost4sResponse;
import com.mission2019.dreamcricket.dreamcricket.Model.TeamStats.BattingMost50sResponse;
import com.mission2019.dreamcricket.dreamcricket.Model.TeamStats.BattingMost6sResponse;
import com.mission2019.dreamcricket.dreamcricket.Model.TeamStats.BattingHighScoresResponse;
import com.mission2019.dreamcricket.dreamcricket.Model.TeamStats.BattingMostDucksResponse;
import com.mission2019.dreamcricket.dreamcricket.Model.TeamStats.BattingMostRunsResponse;
import com.mission2019.dreamcricket.dreamcricket.Model.TeamStats.TeamQuery;
import com.mission2019.dreamcricket.dreamcricket.Model.TeamStats.TeamFormResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface APIQuery {
    // Get Schedule
    @GET("schedule")
    Call<ScheduleResponse> getSchedule();

    @POST("team_stats/recent_form")
    Call<TeamFormResponse> getTeamRecentForm(@Body TeamQuery query);

    @POST("team_stats/batting/most_runs")
    Call<BattingMostRunsResponse> getTeamStatsBattingMostRuns(@Body TeamQuery query);

    @POST("/team_stats/batting/best_strike_rate")
    Call<BattingBestStrikeRateResponse> getBestBatStrikeRate(@Body TeamQuery query);

    @POST("/team_stats/batting/most_50s")
    Call<BattingMost50sResponse> getBattingMost50s(@Body TeamQuery query);

    @POST("/team_stats/batting/most_100s")
    Call<BattingMost100sResponse> getBattingMost100s(@Body TeamQuery query);

    @POST("/team_stats/batting/most_4s")
    Call<BattingMost4sResponse> getBattingMost4s(@Body TeamQuery query);

    @POST("/team_stats/batting/most_6s")
    Call<BattingMost6sResponse> getBattingMost6s(@Body TeamQuery query);

    @POST("/team_stats/batting/most_ducks")
    Call<BattingMostDucksResponse> getBattingMostDucks(@Body TeamQuery query);

    @POST("/team_stats/batting/high_scores")
    Call<BattingHighScoresResponse> getBattingHighScores(@Body TeamQuery query);
}
