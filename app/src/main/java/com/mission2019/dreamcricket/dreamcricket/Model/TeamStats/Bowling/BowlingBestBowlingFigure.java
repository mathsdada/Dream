package com.mission2019.dreamcricket.dreamcricket.Model.TeamStats.Bowling;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

class BowlingBestBowlingFigure {
    @SerializedName("bowler")
    @Expose
    private String mBatsman;

    @SerializedName("wickets")
    @Expose
    private String mWickets;

    @SerializedName("runs")
    @Expose
    private String mRuns;

    @SerializedName("overs")
    private String mOvers;

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
