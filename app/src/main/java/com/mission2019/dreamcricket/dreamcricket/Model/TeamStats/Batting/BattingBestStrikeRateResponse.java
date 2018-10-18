package com.mission2019.dreamcricket.dreamcricket.Model.TeamStats.Batting;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.mission2019.dreamcricket.dreamcricket.Model.TableRow;

import java.util.ArrayList;

public class BattingBestStrikeRateResponse {
    @SerializedName("overall")
    @Expose
    private ArrayList<BattingBestStrikeRate> mOverallStats;

    @SerializedName("atVenue")
    @Expose
    private ArrayList<BattingBestStrikeRate> mAtVenueStats;

    public ArrayList<BattingBestStrikeRate> getOverallStats() {
        return mOverallStats;
    }

    public void setOverallStats(ArrayList<BattingBestStrikeRate> overallStats) {
        mOverallStats = overallStats;
    }

    public ArrayList<BattingBestStrikeRate> getAtVenueStats() {
        return mAtVenueStats;
    }

    public void setAtVenueStats(ArrayList<BattingBestStrikeRate> atVenueStats) {
        mAtVenueStats = atVenueStats;
    }

    public ArrayList<TableRow> convertToTableRows(ArrayList<BattingBestStrikeRate> bestStrikeRates) {
        ArrayList<TableRow> tableRows = new ArrayList<>();
        tableRows.add(new TableRow("Batsman", "I", "R", "B", "SR"));
        for (BattingBestStrikeRate bestStrikeRate: bestStrikeRates) {
            tableRows.add(new TableRow(bestStrikeRate.getBatsman(), bestStrikeRate.getMatches(),
                    bestStrikeRate.getRuns(), bestStrikeRate.getBalls(),
                    bestStrikeRate.getStrikeRate()));
        }
        return tableRows;
    }
}
