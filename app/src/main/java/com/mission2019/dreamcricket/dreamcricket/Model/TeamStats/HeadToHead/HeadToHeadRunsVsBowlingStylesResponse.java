package com.mission2019.dreamcricket.dreamcricket.Model.TeamStats.HeadToHead;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.mission2019.dreamcricket.dreamcricket.Model.TableRow;

import java.util.ArrayList;

public class HeadToHeadRunsVsBowlingStylesResponse {
    @SerializedName("overall")
    @Expose
    private ArrayList<HeadToHeadRunsVsBowlingStyles> mOverallStats;

    @SerializedName("atVenue")
    @Expose
    private ArrayList<HeadToHeadRunsVsBowlingStyles> mAtVenueStats;

    public ArrayList<HeadToHeadRunsVsBowlingStyles> getOverallStats() {
        return mOverallStats;
    }

    public void setOverallStats(ArrayList<HeadToHeadRunsVsBowlingStyles> overallStats) {
        mOverallStats = overallStats;
    }

    public ArrayList<HeadToHeadRunsVsBowlingStyles> getAtVenueStats() {
        return mAtVenueStats;
    }

    public void setAtVenueStats(ArrayList<HeadToHeadRunsVsBowlingStyles> atVenueStats) {
        mAtVenueStats = atVenueStats;
    }

    public ArrayList<TableRow> convertToTableRows(ArrayList<HeadToHeadRunsVsBowlingStyles> headToHeadRunsVsBowlingStyles) {
        ArrayList<TableRow> tableRows = new ArrayList<>();
        tableRows.add(new TableRow("Bowling Style", "M", "B", "R", "W", "SR"));
        for (HeadToHeadRunsVsBowlingStyles headToHeadRunsVsBowlingStyle: headToHeadRunsVsBowlingStyles) {
            tableRows.add(new TableRow(headToHeadRunsVsBowlingStyle.getBowlingStyle(), headToHeadRunsVsBowlingStyle.getMatches(),
                    headToHeadRunsVsBowlingStyle.getBalls(), headToHeadRunsVsBowlingStyle.getRuns(), headToHeadRunsVsBowlingStyle.getWickets(), headToHeadRunsVsBowlingStyle.getStrikeRate()));
        }
        return tableRows;
    }
}
