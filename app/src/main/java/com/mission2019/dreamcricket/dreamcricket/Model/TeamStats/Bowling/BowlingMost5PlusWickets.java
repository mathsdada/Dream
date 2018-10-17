package com.mission2019.dreamcricket.dreamcricket.Model.TeamStats.Bowling;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

class BowlingMost5PlusWickets {
    @SerializedName("bowler")
    @Expose
    private String mBatsman;

    @SerializedName("innings")
    @Expose
    private String mMatches;

    @SerializedName("wickets")
    @Expose
    private String mWickets;

    @SerializedName("economy")
    private String mEconomy;

    @SerializedName("five_plus")
    private String mFivePlus;

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

    public String getEconomy() {
        return mEconomy;
    }

    public void setEconomy(String economy) {
        mEconomy = economy;
    }

    public String getFivePlus() {
        return mFivePlus;
    }

    public void setFivePlus(String fivePlus) {
        mFivePlus = fivePlus;
    }
}
