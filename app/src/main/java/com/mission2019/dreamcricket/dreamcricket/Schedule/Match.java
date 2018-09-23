package com.mission2019.dreamcricket.dreamcricket.Schedule;

import java.util.ArrayList;

public class Match {
    private String mTitle;
    private String mFormat;
    private String mTime;
    private String mVenue;
    private ArrayList<Team> mTeams;
    private String mGender;

    public Match(String title, String format, String time, String venue, String gender) {
        mTitle = title;
        mFormat = format;
        mTime = time;
        mVenue = venue;
        mGender = gender;
        mTeams = new ArrayList<>();
    }

    public Match(String title, String format, String time, String venue, ArrayList<Team> teams, String gender) {
        mTitle = title;
        mFormat = format;
        mTime = time;
        mVenue = venue;
        mTeams = teams;
        mGender = gender;
    }

    public void addTeam(Team team) {
        mTeams.add(team);
    }

    public String getTitle() {
        return mTitle;
    }

    public String getFormat() {
        return mFormat;
    }

    public String getTime() {
        return mTime;
    }

    public String getVenue() {
        return mVenue;
    }

    public ArrayList<Team> getTeams() {
        return mTeams;
    }

    public String getGender() {
        return mGender;
    }
}
