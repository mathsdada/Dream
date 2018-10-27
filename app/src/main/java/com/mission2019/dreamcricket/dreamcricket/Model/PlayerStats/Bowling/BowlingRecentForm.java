package com.mission2019.dreamcricket.dreamcricket.Model.PlayerStats.Bowling;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BowlingRecentForm {
    @SerializedName("opp_team")
    @Expose
    private String mOppTeam;

    @SerializedName("wickets")
    @Expose
    private String mWickets;

    @SerializedName("runs")
    @Expose
    private String mRuns;

    @SerializedName("overs")
    @Expose
    private String mOvers;

    @SerializedName("venue")
    @Expose
    private String mVenue;

    @SerializedName("date")
    @Expose
    private String mDate;

//    @SerializedName("fours")
//    @Expose
//    private String mFours;
//
//    @SerializedName("sixes")
//    @Expose
//    private String mSixes;


    public String getOppTeam() {
        return mOppTeam;
    }

    public void setOppTeam(String oppTeam) {
        mOppTeam = oppTeam;
    }

    public String getRuns() {
        return mRuns;
    }

    public void setRuns(String runs) {
        mRuns = runs;
    }

    public String getVenue() {
        return mVenue;
    }

    public void setVenue(String venue) {
        mVenue = venue;
    }

    public String getDate() {
        return mDate;
    }

    public void setDate(String date) {
        mDate = date;
    }

    public String getWickets() {
        return mWickets;
    }

    public void setWickets(String wickets) {
        mWickets = wickets;
    }

    public String getOvers() {
        return mOvers;
    }

    public void setOvers(String overs) {
        mOvers = overs;
    }
}
