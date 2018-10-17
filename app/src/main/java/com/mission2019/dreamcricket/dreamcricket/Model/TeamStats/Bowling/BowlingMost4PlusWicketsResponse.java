package com.mission2019.dreamcricket.dreamcricket.Model.TeamStats.Bowling;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.mission2019.dreamcricket.dreamcricket.Model.TableRow;

import java.util.ArrayList;

public class BowlingMost4PlusWicketsResponse {
    @SerializedName("overall")
    @Expose
    private ArrayList<BowlingMost4PlusWickets> mOverallStats;

    @SerializedName("atVenue")
    @Expose
    private ArrayList<BowlingMost4PlusWickets> mAtVenueStats;

    public ArrayList<BowlingMost4PlusWickets> getOverallStats() {
        return mOverallStats;
    }

    public void setOverallStats(ArrayList<BowlingMost4PlusWickets> overallStats) {
        mOverallStats = overallStats;
    }

    public ArrayList<BowlingMost4PlusWickets> getAtVenueStats() {
        return mAtVenueStats;
    }

    public void setAtVenueStats(ArrayList<BowlingMost4PlusWickets> atVenueStats) {
        mAtVenueStats = atVenueStats;
    }

    public ArrayList<TableRow> convertToTableRows(ArrayList<BowlingMost4PlusWickets> bowlingMost4PlusWickets) {
        ArrayList<TableRow> tableRows = new ArrayList<>();
        tableRows.add(new TableRow("Bowler", "I", "W", "Eco", "4+"));
        for (BowlingMost4PlusWickets most4PlusWickets: bowlingMost4PlusWickets) {
            tableRows.add(new TableRow(most4PlusWickets.getBatsman(), most4PlusWickets.getMatches(),
                    most4PlusWickets.getWickets(), most4PlusWickets.getEconomy(), most4PlusWickets.getFourPlus()));
        }
        return tableRows;
    }
}
