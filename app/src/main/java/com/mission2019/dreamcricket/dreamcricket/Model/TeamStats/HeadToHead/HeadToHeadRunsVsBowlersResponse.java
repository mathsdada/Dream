package com.mission2019.dreamcricket.dreamcricket.Model.TeamStats.HeadToHead;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.mission2019.dreamcricket.dreamcricket.Model.TableRow;

import java.util.ArrayList;

public class HeadToHeadRunsVsBowlersResponse {
    @SerializedName("overall")
    @Expose
    private ArrayList<HeadToHeadRunsVsBowlers> mOverallStats;

    @SerializedName("atVenue")
    @Expose
    private ArrayList<HeadToHeadRunsVsBowlers> mAtVenueStats;

    public ArrayList<HeadToHeadRunsVsBowlers> getOverallStats() {
        return mOverallStats;
    }

    public void setOverallStats(ArrayList<HeadToHeadRunsVsBowlers> overallStats) {
        mOverallStats = overallStats;
    }

    public ArrayList<HeadToHeadRunsVsBowlers> getAtVenueStats() {
        return mAtVenueStats;
    }

    public void setAtVenueStats(ArrayList<HeadToHeadRunsVsBowlers> atVenueStats) {
        mAtVenueStats = atVenueStats;
    }

    public ArrayList<TableRow> convertToTableRows(ArrayList<HeadToHeadRunsVsBowlers> headToHeadRunsVsBowlingStyles) {
        ArrayList<TableRow> tableRows = new ArrayList<>();
        tableRows.add(new TableRow("Opp. Bowler", "M", "B", "R", "W", "SR"));
        for (HeadToHeadRunsVsBowlers headToHeadRunsVsBowlingStyle: headToHeadRunsVsBowlingStyles) {
            tableRows.add(new TableRow(headToHeadRunsVsBowlingStyle.getBowler(), headToHeadRunsVsBowlingStyle.getMatches(),
                    headToHeadRunsVsBowlingStyle.getBalls(), headToHeadRunsVsBowlingStyle.getRuns(), headToHeadRunsVsBowlingStyle.getWickets(), headToHeadRunsVsBowlingStyle.getStrikeRate()));
        }
        return tableRows;
    }
}
