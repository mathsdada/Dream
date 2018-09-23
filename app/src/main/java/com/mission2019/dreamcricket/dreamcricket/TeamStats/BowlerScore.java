package com.mission2019.dreamcricket.dreamcricket.TeamStats;

public class BowlerScore {
    private String mName;
    private int mWickets;
    private int mRuns;
    private String mOvers;

    public BowlerScore(String name, int wickets, int runs, String overs) {
        mName = name;
        mWickets = wickets;
        mRuns = runs;
        mOvers = overs;
    }

    public String getName() {
        return mName;
    }

    public int getWickets() {
        return mWickets;
    }

    public int getRuns() {
        return mRuns;
    }

    public String getOvers() {
        return mOvers;
    }
}
