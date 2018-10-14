package com.mission2019.dreamcricket.dreamcricket.Model.TeamStats;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TeamFormQuery {
    @SerializedName("team_name")
    @Expose
    private String mTeamName;

    @SerializedName("venue_name")
    @Expose
    private String mVenue;

    @SerializedName("format")
    @Expose
    private String mFormat;

    public TeamFormQuery(String teamName, String venue, String format) {
        mTeamName = teamName;
        mVenue = venue;
        mFormat = format;
    }

    public String getTeamName() {
        return mTeamName;
    }

    public void setTeamName(String teamName) {
        mTeamName = teamName;
    }

    public String getVenue() {
        return mVenue;
    }

    public void setVenue(String venue) {
        mVenue = venue;
    }

    public String getFormat() {
        return mFormat;
    }

    public void setFormat(String format) {
        mFormat = format;
    }
}
