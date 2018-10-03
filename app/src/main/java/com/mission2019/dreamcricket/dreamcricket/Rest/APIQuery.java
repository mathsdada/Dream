package com.mission2019.dreamcricket.dreamcricket.Rest;

import com.mission2019.dreamcricket.dreamcricket.Model.Schedule.ScheduleResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIQuery {
    // Get Schedule
    @GET("schedule")
    Call<ScheduleResponse> getSchedule(@Query("api_key") String apiKey);
}
