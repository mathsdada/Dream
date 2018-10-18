package com.mission2019.dreamcricket.dreamcricket.Model.TeamStats.Batting;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.mission2019.dreamcricket.dreamcricket.Model.TableRow;

import java.util.ArrayList;

public class BattingBestAverageResponse {
    @SerializedName("overall")
    @Expose
    private ArrayList<BattingBestAverage> mOverallStats;

    @SerializedName("atVenue")
    @Expose
    private ArrayList<BattingBestAverage> mAtVenueStats;

    public ArrayList<BattingBestAverage> getOverallStats() {
        return mOverallStats;
    }

    public void setOverallStats(ArrayList<BattingBestAverage> overallStats) {
        mOverallStats = overallStats;
    }

    public ArrayList<BattingBestAverage> getAtVenueStats() {
        return mAtVenueStats;
    }

    public void setAtVenueStats(ArrayList<BattingBestAverage> atVenueStats) {
        mAtVenueStats = atVenueStats;
    }

    public ArrayList<TableRow> convertToTableRows(ArrayList<BattingBestAverage> bestBatAverages) {
        ArrayList<TableRow> tableRows = new ArrayList<>();
        tableRows.add(new TableRow("Batsman", "I", "R", "B", "Avg"));
        for (BattingBestAverage bestBatAverage: bestBatAverages) {
            tableRows.add(new TableRow(bestBatAverage.getBatsman(), bestBatAverage.getMatches(),
                    bestBatAverage.getRuns(), bestBatAverage.getBalls(),
                    bestBatAverage.getAverage()));
        }
        return tableRows;
    }
}
