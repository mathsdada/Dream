package com.mission2019.dreamcricket.dreamcricket.Model.TeamStats.Bowling;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.mission2019.dreamcricket.dreamcricket.Model.TableRow;

import java.util.ArrayList;

public class BowlingMost5PlusWicketsResponse {
    @SerializedName("overall")
    @Expose
    private ArrayList<BowlingMost5PlusWickets> mOverallStats;

    @SerializedName("atVenue")
    @Expose
    private ArrayList<BowlingMost5PlusWickets> mAtVenueStats;

    public ArrayList<BowlingMost5PlusWickets> getOverallStats() {
        return mOverallStats;
    }

    public void setOverallStats(ArrayList<BowlingMost5PlusWickets> overallStats) {
        mOverallStats = overallStats;
    }

    public ArrayList<BowlingMost5PlusWickets> getAtVenueStats() {
        return mAtVenueStats;
    }

    public void setAtVenueStats(ArrayList<BowlingMost5PlusWickets> atVenueStats) {
        mAtVenueStats = atVenueStats;
    }

    public ArrayList<TableRow> convertToTableRows(ArrayList<BowlingMost5PlusWickets> bowlingMost5PlusWickets) {
        ArrayList<TableRow> tableRows = new ArrayList<>();
        tableRows.add(new TableRow("Bowler", "I", "W", "Eco", "5+"));
        for (BowlingMost5PlusWickets most5PlusWickets: bowlingMost5PlusWickets) {
            tableRows.add(new TableRow(most5PlusWickets.getBatsman(), most5PlusWickets.getMatches(),
                    most5PlusWickets.getWickets(), most5PlusWickets.getEconomy(), most5PlusWickets.getFivePlus()));
        }
        return tableRows;
    }
}
