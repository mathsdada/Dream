package com.mission2019.dreamcricket.dreamcricket.TeamStats;

import java.util.ArrayList;

public class TeamRecentMatches {
    private ArrayList<TeamMatchScorecard> mMatchScorecards;

    public TeamRecentMatches() {
        mMatchScorecards = new ArrayList<>();
    }

    public ArrayList<TeamMatchScorecard> getMatchScorecards() {
        return mMatchScorecards;
    }

    public void setMatchScorecard(TeamMatchScorecard matchScorecard) {
        mMatchScorecards.add(matchScorecard);
    }
}
