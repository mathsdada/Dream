package com.mission2019.dreamcricket.dreamcricket.Model.PlayerStats.Bowling;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.mission2019.dreamcricket.dreamcricket.Model.PlayerStats.Batting.BattingVsBowlingStyles;

import java.util.ArrayList;

public class BowlingVsBattingStylesResponse {
    @SerializedName("overall")
    @Expose
    private ArrayList<BowlingVsBattingStyles> mOverallStats;

    @SerializedName("atVenue")
    @Expose
    private ArrayList<BowlingVsBattingStyles> mAtVenueStats;

    public ArrayList<BowlingVsBattingStyles> getOverallStats() {
        return mOverallStats;
    }

    public void setOverallStats(ArrayList<BowlingVsBattingStyles> overallStats) {
        mOverallStats = overallStats;
    }

    public ArrayList<BowlingVsBattingStyles> getAtVenueStats() {
        return mAtVenueStats;
    }

    public void setAtVenueStats(ArrayList<BowlingVsBattingStyles> atVenueStats) {
        mAtVenueStats = atVenueStats;
    }
}
