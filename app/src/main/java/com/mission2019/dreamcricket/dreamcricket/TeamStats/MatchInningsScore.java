package com.mission2019.dreamcricket.dreamcricket.TeamStats;

public class MatchInningsScore {
    private String mTeamName;
    private String mRuns;
    private String mWickets;
    private String mOvers;
    private int mInningsNumber;

    public MatchInningsScore(String teamName, String runs, String wickets, String overs, int inningsNumber) {
        mTeamName = teamName;
        mRuns = runs;
        mWickets = wickets;
        mOvers = overs;
        mInningsNumber = inningsNumber;
    }

    public String getTeamName() {
        return mTeamName;
    }

    public String getRuns() {
        return mRuns;
    }

    public String getWickets() {
        return mWickets;
    }

    public String getOvers() {
        return mOvers;
    }

    public int getInningsNumber() {
        return mInningsNumber;
    }
}
