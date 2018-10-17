package com.mission2019.dreamcricket.dreamcricket.Model.TeamStats.Bowling;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.mission2019.dreamcricket.dreamcricket.Model.TableRow;

import java.util.ArrayList;

public class BowlingMostWicketsResponse {
    @SerializedName("overall")
    @Expose
    private ArrayList<BowlingMostWickets> mOverallStats;

    @SerializedName("atVenue")
    @Expose
    private ArrayList<BowlingMostWickets> mAtVenueStats;

    public ArrayList<BowlingMostWickets> getOverallStats() {
        return mOverallStats;
    }

    public void setOverallStats(ArrayList<BowlingMostWickets> overallStats) {
        mOverallStats = overallStats;
    }

    public ArrayList<BowlingMostWickets> getAtVenueStats() {
        return mAtVenueStats;
    }

    public void setAtVenueStats(ArrayList<BowlingMostWickets> atVenueStats) {
        mAtVenueStats = atVenueStats;
    }

    public ArrayList<TableRow> convertToTableRows(ArrayList<BowlingMostWickets> bowlingMostWickets) {
        ArrayList<TableRow> tableRows = new ArrayList<>();
        tableRows.add(new TableRow("Bowler", "I", "W"));
        for (BowlingMostWickets mostWickets: bowlingMostWickets) {
            tableRows.add(new TableRow(mostWickets.getBatsman(), mostWickets.getMatches(),
                    mostWickets.getWickets()));
        }
        return tableRows;
    }
}
