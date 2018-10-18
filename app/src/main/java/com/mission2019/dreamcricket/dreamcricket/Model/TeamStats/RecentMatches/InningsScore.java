package com.mission2019.dreamcricket.dreamcricket.Model.TeamStats.RecentMatches;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class InningsScore {
    @SerializedName("batting_team")
    @Expose
    private String mBattingTeam;

    @SerializedName("innings_score")
    @Expose
    private String mInningsScore;

    public String getBattingTeam() {
        return mBattingTeam;
    }

    public void setBattingTeam(String battingTeam) {
        mBattingTeam = battingTeam;
    }

    public String getInningsScore() {
        return mInningsScore;
    }

    public void setInningsScore(String inningsScore) {
        mInningsScore = inningsScore;
    }
}
