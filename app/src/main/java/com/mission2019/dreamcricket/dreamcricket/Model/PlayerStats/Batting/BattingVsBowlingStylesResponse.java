package com.mission2019.dreamcricket.dreamcricket.Model.PlayerStats.Batting;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class BattingVsBowlingStylesResponse {
    @SerializedName("overall")
    @Expose
    private ArrayList<BattingVsBowlingStyles> mOverallStats;

    @SerializedName("atVenue")
    @Expose
    private ArrayList<BattingVsBowlingStyles> mAtVenueStats;

    public ArrayList<BattingVsBowlingStyles> getOverallStats() {
        return mOverallStats;
    }

    public void setOverallStats(ArrayList<BattingVsBowlingStyles> overallStats) {
        mOverallStats = overallStats;
    }

    public ArrayList<BattingVsBowlingStyles> getAtVenueStats() {
        return mAtVenueStats;
    }

    public void setAtVenueStats(ArrayList<BattingVsBowlingStyles> atVenueStats) {
        mAtVenueStats = atVenueStats;
    }
}
