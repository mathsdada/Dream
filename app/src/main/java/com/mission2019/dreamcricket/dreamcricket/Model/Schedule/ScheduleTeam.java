package com.mission2019.dreamcricket.dreamcricket.Model.Schedule;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ScheduleTeam {
    @SerializedName("team_name")
    @Expose
    private String name;

    @SerializedName("team_short_name")
    @Expose
    private String shortName;

    @SerializedName("team_squad")
    @Expose
    private ArrayList<SchedulePlayer> squad = null;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public ArrayList<SchedulePlayer> getSquad() {
        return squad;
    }

    public void setSquad(ArrayList<SchedulePlayer> squad) {
        this.squad = squad;
    }

    @Override
    public String toString() {
        return "Team {" +
                    "name = " + name +
                    ", short_name = " + shortName +
                    ", squad = " + squad +
                "}";
    }
}
