package com.mission2019.dreamcricket.dreamcricket.Model.Schedule;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SchedulePlayer {
    @SerializedName("player_name")
    @Expose
    private String name;

    @SerializedName("player_role")
    @Expose
    private String role;

    @SerializedName("player_batting_style")
    @Expose
    private String battingStyle;

    @SerializedName("player_bowling_style")
    @Expose
    private String bowlingStyle;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getBattingStyle() {
        return battingStyle;
    }

    public void setBattingStyle(String battingStyle) {
        this.battingStyle = battingStyle;
    }

    public String getBowlingStyle() {
        return bowlingStyle;
    }

    public void setBowlingStyle(String bowlingStyle) {
        this.bowlingStyle = bowlingStyle;
    }

    @Override
    public String toString() {
        return "SchedulePlayer {" +
                    "name = " + name +
                    ", role = " + role +
                    ", batting_style = " + battingStyle +
                    ", bowling_style = " + bowlingStyle +
                "}";
    }
}
