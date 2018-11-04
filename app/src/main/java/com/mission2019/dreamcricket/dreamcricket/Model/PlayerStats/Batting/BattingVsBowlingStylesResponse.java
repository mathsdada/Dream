package com.mission2019.dreamcricket.dreamcricket.Model.PlayerStats.Batting;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.mission2019.dreamcricket.dreamcricket.Model.TableRow;

import java.util.ArrayList;

public class BattingVsBowlingStylesResponse {
    @SerializedName("overall")
    @Expose
    private ArrayList<BattingVsBowlingStyles> mOverallStats;

    @SerializedName("atVenue")
    @Expose
    private ArrayList<BattingVsBowlingStyles> mAtVenueStats;

    public ArrayList<BattingVsBowlingStyles> getOverallStats() {
        return mOverallStats;
    }

    public void setOverallStats(ArrayList<BattingVsBowlingStyles> overallStats) {
        mOverallStats = overallStats;
    }

    public ArrayList<BattingVsBowlingStyles> getAtVenueStats() {
        return mAtVenueStats;
    }

    public void setAtVenueStats(ArrayList<BattingVsBowlingStyles> atVenueStats) {
        mAtVenueStats = atVenueStats;
    }

    public ArrayList<TableRow> convertToTableRows(ArrayList<BattingVsBowlingStyles> battingVsBowlingStyles) {
        ArrayList<TableRow> tableRows = new ArrayList<>();
        tableRows.add(new TableRow("Bowling Style", "I", "R", "SR", "OUTs"));
        for (BattingVsBowlingStyles battingVsBowlingStyle: battingVsBowlingStyles) {
            tableRows.add(new TableRow(battingVsBowlingStyle.getBowlingStyle(), battingVsBowlingStyle.getInnings(),
                    battingVsBowlingStyle.getRuns(), battingVsBowlingStyle.getStrikeRate(), battingVsBowlingStyle.getNumOuts()));
        }
        return tableRows;
    }
}
