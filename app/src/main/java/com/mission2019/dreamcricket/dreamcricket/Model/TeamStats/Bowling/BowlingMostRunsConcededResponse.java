package com.mission2019.dreamcricket.dreamcricket.Model.TeamStats.Bowling;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.mission2019.dreamcricket.dreamcricket.Model.TableRow;

import java.util.ArrayList;

public class BowlingMostRunsConcededResponse {
    @SerializedName("overall")
    @Expose
    private ArrayList<BowlingMostRunsConceded> mOverallStats;

    @SerializedName("atVenue")
    @Expose
    private ArrayList<BowlingMostRunsConceded> mAtVenueStats;

    public ArrayList<BowlingMostRunsConceded> getOverallStats() {
        return mOverallStats;
    }

    public void setOverallStats(ArrayList<BowlingMostRunsConceded> overallStats) {
        mOverallStats = overallStats;
    }

    public ArrayList<BowlingMostRunsConceded> getAtVenueStats() {
        return mAtVenueStats;
    }

    public void setAtVenueStats(ArrayList<BowlingMostRunsConceded> atVenueStats) {
        mAtVenueStats = atVenueStats;
    }

    public ArrayList<TableRow> convertToTableRows(ArrayList<BowlingMostRunsConceded> bowlingBestBowlingFigures) {
        ArrayList<TableRow> tableRows = new ArrayList<>();
        tableRows.add(new TableRow("Bowler", "W", "O", "R"));
        for (BowlingMostRunsConceded bestBowlingFigure: bowlingBestBowlingFigures) {
            tableRows.add(new TableRow(bestBowlingFigure.getBatsman(), bestBowlingFigure.getWickets(),
                    bestBowlingFigure.getOvers(), bestBowlingFigure.getRuns()));
        }
        return tableRows;
    }
}
