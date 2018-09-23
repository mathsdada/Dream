package com.mission2019.dreamcricket.dreamcricket.TeamStats;

public class BatsmanScore {
    private String mPlayerName;
    private int mRuns;
    private int mBalls;

    public BatsmanScore(String playerName, int runs, int balls) {
        mPlayerName = playerName;
        mRuns = runs;
        mBalls = balls;
    }

    public String getPlayerName() {
        return mPlayerName;
    }

    public int getRuns() {
        return mRuns;
    }

    public int getBalls() {
        return mBalls;
    }
}
