package com.mission2019.dreamcricket.dreamcricket.Rest;

import com.mission2019.dreamcricket.dreamcricket.Model.Schedule.ScheduleResponse;
import com.mission2019.dreamcricket.dreamcricket.Model.TeamStats.TeamBattingMostRunsResponse;
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
    Call<TeamBattingMostRunsResponse> getTeamStatsBattingMostRuns(@Body TeamQuery query);
}
