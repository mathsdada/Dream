package com.mission2019.dreamcricket.dreamcricket.Model.TeamStats.RecentMatches;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class MatchScoresResponse {
    @SerializedName("overall")
    @Expose
    private ArrayList<MatchScore> mOverallStats;

    @SerializedName("atVenue")
    @Expose
    private ArrayList<MatchScore> mAtVenueStats;

    public ArrayList<MatchScore> getOverallStats() {
        return mOverallStats;
    }

    public void setOverallStats(ArrayList<MatchScore> overallStats) {
        mOverallStats = overallStats;
    }

    public ArrayList<MatchScore> getAtVenueStats() {
        return mAtVenueStats;
    }

    public void setAtVenueStats(ArrayList<MatchScore> atVenueStats) {
        mAtVenueStats = atVenueStats;
    }
}
