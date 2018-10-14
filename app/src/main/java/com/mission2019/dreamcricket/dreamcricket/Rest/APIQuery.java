package com.mission2019.dreamcricket.dreamcricket.Rest;

import com.mission2019.dreamcricket.dreamcricket.Model.Schedule.ScheduleResponse;
import com.mission2019.dreamcricket.dreamcricket.Model.TeamStats.BattingBestStrikeRateResponse;
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
}
