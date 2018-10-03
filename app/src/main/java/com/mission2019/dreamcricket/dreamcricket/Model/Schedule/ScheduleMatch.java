package com.mission2019.dreamcricket.dreamcricket.Model.Schedule;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ScheduleMatch {
    @SerializedName("match_title")
    @Expose
    private String mTitle;

    @SerializedName("match_format")
    @Expose
    private String mFormat;

    @SerializedName("match_time")
    @Expose
    private String mTime;

    @SerializedName("match_venue")
    @Expose
    private String mVenue;

    @SerializedName("match_gender")
    @Expose
    private String mGender;

    @SerializedName("match_teams")
    @Expose
    private ArrayList<ScheduleTeam> mTeams = null;

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getFormat() {
        return mFormat;
    }

    public void setFormat(String format) {
        mFormat = format;
    }

    public String getTime() {
        return mTime;
    }

    public void setTime(String time) {
        mTime = time;
    }

    public String getVenue() {
        return mVenue;
    }

    public void setVenue(String venue) {
        mVenue = venue;
    }

    public String getGender() {
        return mGender;
    }

    public void setGender(String gender) {
        mGender = gender;
    }

    public ArrayList<ScheduleTeam> getTeams() {
        return mTeams;
    }

    public void setTeams(ArrayList<ScheduleTeam> teams) {
        mTeams = teams;
    }

    @Override
    public String toString() {
        return "Match {" +
                    "title = " + mTitle +
                    ", format = " + mFormat +
                    ", time = " + mTime +
                    ", venue = " + mVenue +
                    ", gender = " + mGender +
                    ", teams = " + mTeams +
                "}";
    }
}
