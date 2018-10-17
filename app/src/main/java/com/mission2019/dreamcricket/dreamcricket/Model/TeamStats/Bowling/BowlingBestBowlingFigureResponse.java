package com.mission2019.dreamcricket.dreamcricket.Model.TeamStats.Bowling;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.mission2019.dreamcricket.dreamcricket.Model.TableRow;

import java.util.ArrayList;

public class BowlingBestBowlingFigureResponse {
    @SerializedName("overall")
    @Expose
    private ArrayList<BowlingBestBowlingFigure> mOverallStats;

    @SerializedName("atVenue")
    @Expose
    private ArrayList<BowlingBestBowlingFigure> mAtVenueStats;

    public ArrayList<BowlingBestBowlingFigure> getOverallStats() {
        return mOverallStats;
    }

    public void setOverallStats(ArrayList<BowlingBestBowlingFigure> overallStats) {
        mOverallStats = overallStats;
    }

    public ArrayList<BowlingBestBowlingFigure> getAtVenueStats() {
        return mAtVenueStats;
    }

    public void setAtVenueStats(ArrayList<BowlingBestBowlingFigure> atVenueStats) {
        mAtVenueStats = atVenueStats;
    }

    public ArrayList<TableRow> convertToTableRows(ArrayList<BowlingBestBowlingFigure> bowlingBestBowlingFigures) {
        ArrayList<TableRow> tableRows = new ArrayList<>();
        tableRows.add(new TableRow("Bowler", "W", "R", "O"));
        for (BowlingBestBowlingFigure bestBowlingFigure: bowlingBestBowlingFigures) {
            tableRows.add(new TableRow(bestBowlingFigure.getBatsman(), bestBowlingFigure.getWickets(),
                    bestBowlingFigure.getRuns(), bestBowlingFigure.getOvers()));
        }
        return tableRows;
    }
}
