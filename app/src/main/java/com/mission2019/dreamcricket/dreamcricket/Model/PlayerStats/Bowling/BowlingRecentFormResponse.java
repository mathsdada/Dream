package com.mission2019.dreamcricket.dreamcricket.Model.PlayerStats.Bowling;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.mission2019.dreamcricket.dreamcricket.Model.PlayerStats.Batting.BattingRecentForm;

import java.util.ArrayList;

public class BowlingRecentFormResponse {
    @SerializedName("overall")
    @Expose
    private ArrayList<BowlingRecentForm> mOverallStats;

    @SerializedName("atVenue")
    @Expose
    private ArrayList<BowlingRecentForm> mAtVenueStats;

    public ArrayList<BowlingRecentForm> getOverallStats() {
        return mOverallStats;
    }

    public void setOverallStats(ArrayList<BowlingRecentForm> overallStats) {
        mOverallStats = overallStats;
    }

    public ArrayList<BowlingRecentForm> getAtVenueStats() {
        return mAtVenueStats;
    }

    public void setAtVenueStats(ArrayList<BowlingRecentForm> atVenueStats) {
        mAtVenueStats = atVenueStats;
    }
}
