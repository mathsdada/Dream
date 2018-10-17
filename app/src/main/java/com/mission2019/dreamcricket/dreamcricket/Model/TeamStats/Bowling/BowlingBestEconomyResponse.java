package com.mission2019.dreamcricket.dreamcricket.Model.TeamStats.Bowling;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.mission2019.dreamcricket.dreamcricket.Model.TableRow;

import java.util.ArrayList;

public class BowlingBestEconomyResponse {
    @SerializedName("overall")
    @Expose
    private ArrayList<BowlingBestEconomy> mOverallStats;

    @SerializedName("atVenue")
    @Expose
    private ArrayList<BowlingBestEconomy> mAtVenueStats;

    public ArrayList<BowlingBestEconomy> getOverallStats() {
        return mOverallStats;
    }

    public void setOverallStats(ArrayList<BowlingBestEconomy> overallStats) {
        mOverallStats = overallStats;
    }

    public ArrayList<BowlingBestEconomy> getAtVenueStats() {
        return mAtVenueStats;
    }

    public void setAtVenueStats(ArrayList<BowlingBestEconomy> atVenueStats) {
        mAtVenueStats = atVenueStats;
    }

    public ArrayList<TableRow> convertToTableRows(ArrayList<BowlingBestEconomy> bowlingBestEconomies) {
        ArrayList<TableRow> tableRows = new ArrayList<>();
        tableRows.add(new TableRow("Bowler", "I", "W", "Eco"));
        for (BowlingBestEconomy bowlingBestEconomy: bowlingBestEconomies) {
            tableRows.add(new TableRow(bowlingBestEconomy.getBatsman(), bowlingBestEconomy.getMatches(),
                    bowlingBestEconomy.getWickets(), bowlingBestEconomy.getEconomy()));
        }
        return tableRows;
    }
}
