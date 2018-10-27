package com.mission2019.dreamcricket.dreamcricket.Model.PlayerStats.Batting;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BattingRecentForm {
    @SerializedName("opp_team")
    @Expose
    private String mOppTeam;

    @SerializedName("runs")
    @Expose
    private String mRuns;

    @SerializedName("strike_rate")
    @Expose
    private String mStrikeRate;

    @SerializedName("dismissal_type")
    @Expose
    private String mDismissalType;

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

    public String getStrikeRate() {
        return mStrikeRate;
    }

    public void setStrikeRate(String strikeRate) {
        mStrikeRate = strikeRate;
    }

    public String getDismissalType() {
        return mDismissalType;
    }

    public void setDismissalType(String dismissalType) {
        mDismissalType = dismissalType;
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
}
