package com.mission2019.dreamcricket.dreamcricket.Model.TeamStats.Batting;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.mission2019.dreamcricket.dreamcricket.Model.TableRow;

import java.util.ArrayList;

public class BattingBestAverageResponse {
    @SerializedName("overall")
    @Expose
    private ArrayList<BattingBestAverage> mMostRunsOverall;

    @SerializedName("atVenue")
    @Expose
    private ArrayList<BattingBestAverage> mMostRunsAtVenue;

    public ArrayList<BattingBestAverage> getMostRunsOverall() {
        return mMostRunsOverall;
    }

    public void setMostRunsOverall(ArrayList<BattingBestAverage> mostRunsOverall) {
        mMostRunsOverall = mostRunsOverall;
    }

    public ArrayList<BattingBestAverage> getMostRunsAtVenue() {
        return mMostRunsAtVenue;
    }

    public void setMostRunsAtVenue(ArrayList<BattingBestAverage> mostRunsAtVenue) {
        mMostRunsAtVenue = mostRunsAtVenue;
    }

    public ArrayList<TableRow> convertToTableRows(ArrayList<BattingBestAverage> bestBatAverages) {
        ArrayList<TableRow> tableRows = new ArrayList<>();
        tableRows.add(new TableRow("Batsman", "I", "R", "B", "Avg"));
        for (BattingBestAverage bestBatAverage: bestBatAverages) {
            tableRows.add(new TableRow(bestBatAverage.getBatsman(), bestBatAverage.getMatches(),
                    bestBatAverage.getRuns(), bestBatAverage.getBalls(),
                    bestBatAverage.getAverage()));
        }
        return tableRows;
    }
}
