package com.mission2019.dreamcricket.dreamcricket.Model.TeamStats.Bowling;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.mission2019.dreamcricket.dreamcricket.Model.TableRow;

import java.util.ArrayList;

public class BowlingMostMaidensResponse {
    @SerializedName("overall")
    @Expose
    private ArrayList<BowlingMostMaidens> mOverallStats;

    @SerializedName("atVenue")
    @Expose
    private ArrayList<BowlingMostMaidens> mAtVenueStats;

    public ArrayList<BowlingMostMaidens> getOverallStats() {
        return mOverallStats;
    }

    public void setOverallStats(ArrayList<BowlingMostMaidens> overallStats) {
        mOverallStats = overallStats;
    }

    public ArrayList<BowlingMostMaidens> getAtVenueStats() {
        return mAtVenueStats;
    }

    public void setAtVenueStats(ArrayList<BowlingMostMaidens> atVenueStats) {
        mAtVenueStats = atVenueStats;
    }

    public ArrayList<TableRow> convertToTableRows(ArrayList<BowlingMostMaidens> bowlingMostMaidens) {
        ArrayList<TableRow> tableRows = new ArrayList<>();
        tableRows.add(new TableRow("Bowler", "I", "W", "M"));
        for (BowlingMostMaidens mostMaidens: bowlingMostMaidens) {
            tableRows.add(new TableRow(mostMaidens.getBatsman(), mostMaidens.getMatches(),
                    mostMaidens.getWickets(), mostMaidens.getMaidens()));
        }
        return tableRows;
    }
}
