package com.mission2019.dreamcricket.dreamcricket.Model.TeamStats.Bowling;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.mission2019.dreamcricket.dreamcricket.Model.TableRow;

import java.util.ArrayList;

public class BowlingBestStrikeRateResponse {
    @SerializedName("overall")
    @Expose
    private ArrayList<BowlingBestStrikeRate> mOverallStats;

    @SerializedName("atVenue")
    @Expose
    private ArrayList<BowlingBestStrikeRate> mAtVenueStats;

    public ArrayList<BowlingBestStrikeRate> getOverallStats() {
        return mOverallStats;
    }

    public void setOverallStats(ArrayList<BowlingBestStrikeRate> overallStats) {
        mOverallStats = overallStats;
    }

    public ArrayList<BowlingBestStrikeRate> getAtVenueStats() {
        return mAtVenueStats;
    }

    public void setAtVenueStats(ArrayList<BowlingBestStrikeRate> atVenueStats) {
        mAtVenueStats = atVenueStats;
    }

    public ArrayList<TableRow> convertToTableRows(ArrayList<BowlingBestStrikeRate> bowlingBestStrikeRates) {
        ArrayList<TableRow> tableRows = new ArrayList<>();
        tableRows.add(new TableRow("Bowler", "I", "W", "Eco", "SR"));
        for (BowlingBestStrikeRate bowlingBestStrikeRate: bowlingBestStrikeRates) {
            tableRows.add(new TableRow(bowlingBestStrikeRate.getBatsman(), bowlingBestStrikeRate.getMatches(),
                    bowlingBestStrikeRate.getWickets(), bowlingBestStrikeRate.getEconomy(), bowlingBestStrikeRate.getStrikeRate()));
        }
        return tableRows;
    }
}
