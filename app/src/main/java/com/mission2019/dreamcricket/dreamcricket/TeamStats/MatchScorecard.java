package com.mission2019.dreamcricket.dreamcricket.TeamStats;

import java.util.ArrayList;

public class MatchScorecard {
    private ArrayList<MatchInningsScore> mMatchInningsScores;

    public MatchScorecard() {
        mMatchInningsScores = new ArrayList<>();
    }

    public ArrayList<MatchInningsScore> getMatchInningsScores() {
        return mMatchInningsScores;
    }

    public void setMatchInningsScore(MatchInningsScore matchInningsScore) {
        mMatchInningsScores.add(matchInningsScore);
    }
}
