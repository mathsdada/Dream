package com.mission2019.dreamcricket.dreamcricket.Model.PlayerStats.Bowling;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.mission2019.dreamcricket.dreamcricket.Model.PlayerStats.Batting.BattingVsBowlingStyles;
import com.mission2019.dreamcricket.dreamcricket.Model.TableRow;

import java.util.ArrayList;

public class BowlingVsBattingStylesResponse {
    @SerializedName("overall")
    @Expose
    private ArrayList<BowlingVsBattingStyles> mOverallStats;

    @SerializedName("atVenue")
    @Expose
    private ArrayList<BowlingVsBattingStyles> mAtVenueStats;

    public ArrayList<BowlingVsBattingStyles> getOverallStats() {
        return mOverallStats;
    }

    public void setOverallStats(ArrayList<BowlingVsBattingStyles> overallStats) {
        mOverallStats = overallStats;
    }

    public ArrayList<BowlingVsBattingStyles> getAtVenueStats() {
        return mAtVenueStats;
    }

    public void setAtVenueStats(ArrayList<BowlingVsBattingStyles> atVenueStats) {
        mAtVenueStats = atVenueStats;
    }

    public ArrayList<TableRow> convertToTableRows(ArrayList<BowlingVsBattingStyles> bowlingVsBattingStyles) {
        ArrayList<TableRow> tableRows = new ArrayList<>();
        tableRows.add(new TableRow("Batting Style", "I", "Wickets"));
        for (BowlingVsBattingStyles bowlingVsBattingStyle: bowlingVsBattingStyles) {
            tableRows.add(new TableRow(
                    bowlingVsBattingStyle.getBattingStyle(),
                    bowlingVsBattingStyle.getInnings(),
                    bowlingVsBattingStyle.getWickets() + "-"+bowlingVsBattingStyle.getRuns() + " (" + bowlingVsBattingStyle.getOvers() + ")"));
        }
        return tableRows;
    }
}
