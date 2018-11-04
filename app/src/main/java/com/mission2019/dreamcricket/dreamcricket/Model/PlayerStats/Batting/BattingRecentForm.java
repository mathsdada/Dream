package com.mission2019.dreamcricket.dreamcricket.Model.PlayerStats.Batting;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BattingRecentForm {
    @SerializedName("played_for")
    private String mTeamPf;

    @SerializedName("played_against")
    @Expose
    private String mTeamPa;

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


    public String getTeamPf() {
        return mTeamPf;
    }

    public void setTeamPf(String teamPf) {
        mTeamPf = teamPf;
    }

    public String getTeamPa() {
        return mTeamPa;
    }

    public void setTeamPa(String teamPa) {
        mTeamPa = teamPa;
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
