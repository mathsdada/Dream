package com.mission2019.dreamcricket.dreamcricket.Model.TeamStats.RecentMatches;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class MatchScore {
    @SerializedName("match_outcome")
    @Expose
    private String mMatchOutcome;

    @SerializedName("match_date")
    @Expose
    private String mMatchDate;

    @SerializedName("match_score")
    @Expose
    private ArrayList<InningsScore> mInningsScores;

    public String getMatchOutcome() {
        return mMatchOutcome;
    }

    public void setMatchOutcome(String matchOutcome) {
        mMatchOutcome = matchOutcome;
    }

    public String getMatchDate() {
        return mMatchDate;
    }

    public void setMatchDate(String matchDate) {
        mMatchDate = matchDate;
    }

    public ArrayList<InningsScore> getInningsScores() {
        return mInningsScores;
    }

    public void setInningsScores(ArrayList<InningsScore> inningsScores) {
        mInningsScores = inningsScores;
    }
}
