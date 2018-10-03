package com.mission2019.dreamcricket.dreamcricket.Model.Schedule;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ScheduleResponse {
    @SerializedName("response_schedule")
    @Expose
    private ArrayList<ScheduleSeries> mSeriesList = null;

    public ArrayList<ScheduleSeries> getSeriesList() {
        return mSeriesList;
    }

    public void setSeriesList(ArrayList<ScheduleSeries> seriesList) {
        mSeriesList = seriesList;
    }

    @Override
    public String toString() {
        return "Schedule {" +
                    "series_list = " + mSeriesList +
                "}";
    }
}
