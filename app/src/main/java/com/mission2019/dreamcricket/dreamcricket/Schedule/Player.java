package com.mission2019.dreamcricket.dreamcricket.Schedule;

public class Player {
    private String mName;
    private String mRole;
    private String mBattingStyle;
    private String mBowlingStyle;
    private String mGender;

    public Player(String name, String role, String battingStyle, String bowlingStyle, String gender) {
        mName = name;
        mRole = role;
        mBattingStyle = battingStyle;
        mBowlingStyle = bowlingStyle;
        mGender = gender;
    }

    public String getName() {
        return mName;
    }

    public String getRole() {
        return mRole;
    }

    public String getBattingStyle() {
        return mBattingStyle;
    }

    public String getBowlingStyle() {
        return mBowlingStyle;
    }

    public String getGender() {
        return mGender;
    }
}
