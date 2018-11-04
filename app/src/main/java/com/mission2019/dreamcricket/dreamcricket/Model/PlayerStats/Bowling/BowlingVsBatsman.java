package com.mission2019.dreamcricket.dreamcricket.Model.PlayerStats.Bowling;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BowlingVsBatsman {
    @SerializedName("batsman")
    @Expose
    private String mBatsman;

    @SerializedName("innings")
    @Expose
    private String mInnings;

    @SerializedName("wickets")
    @Expose
    private String mWickets;

    @SerializedName("runs")
    @Expose
    private String mRuns;

    @SerializedName("overs")
    @Expose
    private String mOvers;

    @SerializedName("fours")
    @Expose
    private String mFours;

    @SerializedName("sixes")
    @Expose
    private String mSixes;


    public String getBatsman() {
        return mBatsman;
    }

    public void setBatsman(String batsman) {
        mBatsman = batsman;
    }

    public String getRuns() {
        return mRuns;
    }

    public void setRuns(String runs) {
        mRuns = runs;
    }

    public String getOvers() {
        return mOvers;
    }

    public void setOvers(String overs) {
        mOvers = overs;
    }

    public String getWickets() {
        return mWickets;
    }

    public void setWickets(String wickets) {
        mWickets = wickets;
    }

    public String getInnings() {
        return mInnings;
    }

    public void setInnings(String innings) {
        mInnings = innings;
    }

    public String getFours() {
        return mFours;
    }

    public void setFours(String fours) {
        mFours = fours;
    }

    public String getSixes() {
        return mSixes;
    }

    public void setSixes(String sixes) {
        mSixes = sixes;
    }
}
