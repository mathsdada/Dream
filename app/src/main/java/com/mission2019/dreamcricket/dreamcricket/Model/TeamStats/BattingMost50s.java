package com.mission2019.dreamcricket.dreamcricket.Model.TeamStats;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

class BattingMost50s {
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

    @SerializedName("fifties")
    @Expose
    private String mFifties;

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

    public String getFifties() {
        return mFifties;
    }

    public void setFifties(String fifties) {
        mFifties = fifties;
    }
}
