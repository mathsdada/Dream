package com.mission2019.dreamcricket.dreamcricket.Rest;

import com.mission2019.dreamcricket.dreamcricket.Model.Schedule.ScheduleResponse;
import com.mission2019.dreamcricket.dreamcricket.Model.TeamStats.TeamFormQuery;
import com.mission2019.dreamcricket.dreamcricket.Model.TeamStats.TeamFormResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface APIQuery {
    // Get Schedule
    @GET("schedule")
    Call<ScheduleResponse> getSchedule();

    @POST("team_stats/recent_form")
    Call<TeamFormResponse> getTeamRecentForm(@Body TeamFormQuery query);
}
