package com.mission2019.dreamcricket.dreamcricket.Model.TeamStats.Bowling;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

class BowlingMostMaidens {
    @SerializedName("bowler")
    @Expose
    private String mBatsman;

    @SerializedName("innings")
    @Expose
    private String mMatches;

    @SerializedName("wickets")
    @Expose
    private String mWickets;

    @SerializedName("maidens")
    private String mMaidens;

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

    public String getWickets() {
        return mWickets;
    }

    public void setWickets(String wickets) {
        mWickets = wickets;
    }

    public String getMaidens() {
        return mMaidens;
    }

    public void setMaidens(String maidens) {
        mMaidens = maidens;
    }
}
