package com.mission2019.dreamcricket.dreamcricket.TeamStats;

import java.util.ArrayList;

public class TeamMatchScorecard {
    private ArrayList<TeamMatchInningsScore> mMatchInningsScores;

    public TeamMatchScorecard() {
        mMatchInningsScores = new ArrayList<>();
    }

    public ArrayList<TeamMatchInningsScore> getMatchInningsScores() {
        return mMatchInningsScores;
    }

    public void setMatchInningsScore(TeamMatchInningsScore matchInningsScore) {
        mMatchInningsScores.add(matchInningsScore);
    }
}
