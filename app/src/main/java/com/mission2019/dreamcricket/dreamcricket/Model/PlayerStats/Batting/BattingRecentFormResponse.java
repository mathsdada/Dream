package com.mission2019.dreamcricket.dreamcricket.Model.PlayerStats.Batting;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.mission2019.dreamcricket.dreamcricket.Model.TableRow;

import java.util.ArrayList;

public class BattingRecentFormResponse {
    @SerializedName("overall")
    @Expose
    private ArrayList<BattingRecentForm> mOverallStats;

    @SerializedName("atVenue")
    @Expose
    private ArrayList<BattingRecentForm> mAtVenueStats;

    public ArrayList<BattingRecentForm> getOverallStats() {
        return mOverallStats;
    }

    public void setOverallStats(ArrayList<BattingRecentForm> overallStats) {
        mOverallStats = overallStats;
    }

    public ArrayList<BattingRecentForm> getAtVenueStats() {
        return mAtVenueStats;
    }

    public void setAtVenueStats(ArrayList<BattingRecentForm> atVenueStats) {
        mAtVenueStats = atVenueStats;
    }

    public ArrayList<TableRow> convertToTableRows(ArrayList<BattingRecentForm> battingRecentForms) {
        ArrayList<TableRow> tableRows = new ArrayList<>();
        tableRows.add(new TableRow("R", "SR", "PF", "PA"));
        for (BattingRecentForm recentForm: battingRecentForms) {
            tableRows.add(new TableRow(recentForm.getRuns(), recentForm.getStrikeRate(),
                    recentForm.getTeamPf(), recentForm.getTeamPa()));
        }
        return tableRows;
    }
}
