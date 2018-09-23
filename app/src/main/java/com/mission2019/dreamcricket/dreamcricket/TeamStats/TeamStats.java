package com.mission2019.dreamcricket.dreamcricket.TeamStats;

import java.util.ArrayList;

public class TeamStats {
    private String mTeamName;
    private TeamForm mTeamForm;
    private TeamRecentMatches mRecentMatchesScorecards;
    private ArrayList<BatsmanScore> mTopBattingScores;
    private ArrayList<BowlerScore> mTopBowlingScores;

    public TeamStats(String teamName) {
        mTeamName = teamName;
    }

    public String getTeamName() {
        return mTeamName;
    }

    public void setTeamName(String teamName) {
        mTeamName = teamName;
    }

    public TeamForm getTeamForm() {
        return mTeamForm;
    }

    public void setTeamForm(TeamForm teamForm) {
        mTeamForm = teamForm;
    }

    public TeamRecentMatches getRecentMatchesScorecards() {
        return mRecentMatchesScorecards;
    }

    public void setRecentMatchesScorecards(TeamRecentMatches recentMatchesScorecards) {
        mRecentMatchesScorecards = recentMatchesScorecards;
    }

    public void setTopBattingScores(ArrayList<BatsmanScore> topBattingScores) {
        mTopBattingScores = topBattingScores;
    }

    public ArrayList<BatsmanScore> getTopBattingScores() {
        return mTopBattingScores;
    }

    public ArrayList<BowlerScore> getTopBowlingScores() {
        return mTopBowlingScores;
    }

    public void setTopBowlingScores(ArrayList<BowlerScore> topBowlingScores) {
        mTopBowlingScores = topBowlingScores;
    }
}
