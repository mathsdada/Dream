package com.mission2019.dreamcricket.dreamcricket.Schedule;

import java.util.ArrayList;

public class Team {
    private String mName;
    private String mShortName;
    private ArrayList<Player> mSquad;

    public Team(String name, String shortName) {
        mName = name;
        mShortName = shortName;
        mSquad = new ArrayList<>();
    }

    public Team(String name, String shortName, ArrayList<Player> squad) {
        mName = name;
        mShortName = shortName;
        mSquad = squad;
    }

    public void addPlayer(Player player) {
        mSquad.add(player);
    }

    public String getName() {
        return mName;
    }

    public String getShortName() {
        return mShortName;
    }

    public ArrayList<Player> getSquad() {
        return mSquad;
    }
}
