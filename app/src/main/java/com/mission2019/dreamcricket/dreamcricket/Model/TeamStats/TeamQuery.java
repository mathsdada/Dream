package com.mission2019.dreamcricket.dreamcricket.Model.TeamStats;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class TeamQuery {
    @SerializedName("team_name")
    @Expose
    private String mTeamName;

    @SerializedName("venue_name")
    @Expose
    private String mVenue;

    @SerializedName("format")
    @Expose
    private String mFormat;

    @SerializedName("squad")
    @Expose
    private ArrayList<String> mSquad = null;

    @SerializedName("squad-2")
    @Expose
    private ArrayList<String> mSquadExtra = null;

    public TeamQuery(String teamName, String venue, String format) {
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

    public ArrayList<String> getSquad() {
        return mSquad;
    }

    public void setSquad(ArrayList<String> squad) {
        mSquad = squad;
    }

    public ArrayList<String> getSquadExtra() {
        return mSquadExtra;
    }

    public void setSquadExtra(ArrayList<String> squadExtra) {
        mSquadExtra = squadExtra;
    }
}
