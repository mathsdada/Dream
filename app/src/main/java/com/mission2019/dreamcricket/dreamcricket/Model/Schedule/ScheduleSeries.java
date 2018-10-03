package com.mission2019.dreamcricket.dreamcricket.Model.Schedule;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ScheduleSeries {
    @SerializedName("series_title")
    @Expose
    private String mTitle;

    @SerializedName("series_data")
    @Expose
    private ArrayList<ScheduleMatch> mMatches = null;

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        this.mTitle = title;
    }

    public ArrayList<ScheduleMatch> getMatches() {
        return mMatches;
    }

    public void setMatches(ArrayList<ScheduleMatch> matches) {
        mMatches = matches;
    }

    @Override
    public String toString() {
        return " Series {" +
                    "title = " + mTitle +
                    ", matches = " + mMatches +
                "}";
    }
}
