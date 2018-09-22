package com.mission2019.dreamcricket.dreamcricket.TeamStats;

import java.util.ArrayList;

public class TeamRecentMatches {
    private ArrayList<MatchScorecard> mMatchScorecards;

    public TeamRecentMatches() {
        mMatchScorecards = new ArrayList<>();
    }

    public ArrayList<MatchScorecard> getMatchScorecards() {
        return mMatchScorecards;
    }

    public void setMatchScorecard(MatchScorecard matchScorecard) {
        mMatchScorecards.add(matchScorecard);
    }
}
