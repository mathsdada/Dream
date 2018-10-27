package com.mission2019.dreamcricket.dreamcricket.Model.PlayerStats.Batting;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BattingVsBowlingStyles {
    @SerializedName("bowling_style")
    @Expose
    private String mBowlingStyle;

    @SerializedName("innings")
    @Expose
    private String mInnings;

    @SerializedName("runs")
    @Expose
    private String mRuns;

    @SerializedName("strike_rate")
    @Expose
    private String mStrikeRate;

    @SerializedName("fours")
    @Expose
    private String mFours;

    @SerializedName("sixes")
    @Expose
    private String mSixes;

    @SerializedName("no_of_outs")
    @Expose
    private String mNumOuts;


    public String getBowlingStyle() {
        return mBowlingStyle;
    }

    public void setBowlingStyle(String bowlingStyle) {
        mBowlingStyle = bowlingStyle;
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

    public String getNumOuts() {
        return mNumOuts;
    }

    public void setNumOuts(String numOuts) {
        mNumOuts = numOuts;
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
