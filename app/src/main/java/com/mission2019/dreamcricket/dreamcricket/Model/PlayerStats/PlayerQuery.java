package com.mission2019.dreamcricket.dreamcricket.Model.PlayerStats;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class PlayerQuery {
    @SerializedName("player_name")
    @Expose
    private String mPlayerName;

    @SerializedName("venue_name")
    @Expose
    private String mVenueName;

    @SerializedName("format")
    @Expose
    private String mFormat;

    @SerializedName("list_1")
    @Expose
    private ArrayList<String> mList1 = null;

    public PlayerQuery(String playerName, String venueName, String format) {
        mPlayerName = playerName;
        mVenueName = venueName;
        mFormat = format;
    }

    public String getPlayerName() {
        return mPlayerName;
    }

    public void setPlayerName(String playerName) {
        mPlayerName = playerName;
    }

    public String getVenueName() {
        return mVenueName;
    }

    public void setVenueName(String venueName) {
        mVenueName = venueName;
    }

    public String getFormat() {
        return mFormat;
    }

    public void setFormat(String format) {
        mFormat = format;
    }

    public ArrayList<String> getList1() {
        return mList1;
    }

    public void setList1(ArrayList<String> list1) {
        mList1 = list1;
    }
}
