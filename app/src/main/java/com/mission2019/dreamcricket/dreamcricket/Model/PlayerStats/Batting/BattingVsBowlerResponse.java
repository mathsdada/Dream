package com.mission2019.dreamcricket.dreamcricket.Model.PlayerStats.Batting;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.mission2019.dreamcricket.dreamcricket.Model.TableRow;

import java.util.ArrayList;

public class BattingVsBowlerResponse {
    @SerializedName("overall")
    @Expose
    private ArrayList<BattingVsBowler> mOverallStats;

    @SerializedName("atVenue")
    @Expose
    private ArrayList<BattingVsBowler> mAtVenueStats;

    public ArrayList<BattingVsBowler> getOverallStats() {
        return mOverallStats;
    }

    public void setOverallStats(ArrayList<BattingVsBowler> overallStats) {
        mOverallStats = overallStats;
    }

    public ArrayList<BattingVsBowler> getAtVenueStats() {
        return mAtVenueStats;
    }

    public void setAtVenueStats(ArrayList<BattingVsBowler> atVenueStats) {
        mAtVenueStats = atVenueStats;
    }

    public ArrayList<TableRow> convertToTableRows(ArrayList<BattingVsBowler> battingVsBowlers) {
        ArrayList<TableRow> tableRows = new ArrayList<>();
        tableRows.add(new TableRow("Bowler", "I", "R", "SR", "OUTs"));
        for (BattingVsBowler battingVsBowler: battingVsBowlers) {
            tableRows.add(new TableRow(battingVsBowler.getBowler(), battingVsBowler.getInnings(),
                    battingVsBowler.getRuns(), battingVsBowler.getStrikeRate(), battingVsBowler.getNumOuts()));
        }
        return tableRows;
    }
}
