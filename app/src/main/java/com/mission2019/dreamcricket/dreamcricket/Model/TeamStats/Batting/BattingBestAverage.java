package com.mission2019.dreamcricket.dreamcricket.Model.TeamStats.Batting;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

class BattingBestAverage {
    @SerializedName("batsman")
    @Expose
    private String mBatsman;

    @SerializedName("innings")
    @Expose
    private String mMatches;

    @SerializedName("runs")
    @Expose
    private String mRuns;

    @SerializedName("balls")
    @Expose
    private String mBalls;

    @SerializedName("average")
    @Expose
    private String mAverage;

    public String getBatsman() {
        return mBatsman;
    }

    public void setBatsman(String batsman) {
        mBatsman = batsman;
    }

    public String getMatches() {
        return mMatches;
    }

    public void setMatches(String matches) {
        mMatches = matches;
    }

    public String getRuns() {
        return mRuns;
    }

    public void setRuns(String runs) {
        mRuns = runs;
    }

    public String getBalls() {
        return mBalls;
    }

    public void setBalls(String balls) {
        mBalls = balls;
    }

    public String getAverage() {
        return mAverage;
    }

    public void setAverage(String average) {
        mAverage = average;
    }
}
