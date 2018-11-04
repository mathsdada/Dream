package com.mission2019.dreamcricket.dreamcricket.Model.PlayerStats.Bowling;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.mission2019.dreamcricket.dreamcricket.Model.TableRow;

import java.util.ArrayList;

public class BowlingVsBatsmanResponse {
    @SerializedName("overall")
    @Expose
    private ArrayList<BowlingVsBatsman> mOverallStats;

    @SerializedName("atVenue")
    @Expose
    private ArrayList<BowlingVsBatsman> mAtVenueStats;

    public ArrayList<BowlingVsBatsman> getOverallStats() {
        return mOverallStats;
    }

    public void setOverallStats(ArrayList<BowlingVsBatsman> overallStats) {
        mOverallStats = overallStats;
    }

    public ArrayList<BowlingVsBatsman> getAtVenueStats() {
        return mAtVenueStats;
    }

    public void setAtVenueStats(ArrayList<BowlingVsBatsman> atVenueStats) {
        mAtVenueStats = atVenueStats;
    }

    public ArrayList<TableRow> convertToTableRows(ArrayList<BowlingVsBatsman> bowlingVsBatsmen) {
        ArrayList<TableRow> tableRows = new ArrayList<>();
        tableRows.add(new TableRow("Batsman", "I", "Wickets"));
        for (BowlingVsBatsman bowlingVsBatsman: bowlingVsBatsmen) {
            tableRows.add(new TableRow(
                    bowlingVsBatsman.getBatsman(),
                    bowlingVsBatsman.getInnings(),
                    bowlingVsBatsman.getWickets() + "-"+bowlingVsBatsman.getRuns() + " (" + bowlingVsBatsman.getOvers() + ")"));
        }
        return tableRows;
    }
}
