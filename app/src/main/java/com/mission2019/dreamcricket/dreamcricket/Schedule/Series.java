package com.mission2019.dreamcricket.dreamcricket.Schedule;

import java.util.ArrayList;

public class Series {
    private String mTitle;
    private ArrayList<Match> mMatches;

    public Series(String title) {
        mTitle = title;
        mMatches = new ArrayList<>();
    }

    public Series(String title, ArrayList<Match> matches) {
        mTitle = title;
        mMatches = matches;
    }

    public void addMatch(Match match) {
        mMatches.add(match);
    }

    public String getTitle() {
        return mTitle;
    }

    public ArrayList<Match> getMatches() {
        return mMatches;
    }
}
