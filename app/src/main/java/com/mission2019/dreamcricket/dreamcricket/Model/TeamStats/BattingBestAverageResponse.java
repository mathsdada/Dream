package com.mission2019.dreamcricket.dreamcricket.Model.TeamStats;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.mission2019.dreamcricket.dreamcricket.Model.TableRow;

import java.util.ArrayList;

public class BattingBestAverageResponse {
    @SerializedName("overall")
    @Expose
    private ArrayList<BestBatAverage> mMostRunsOverall;

    @SerializedName("atVenue")
    @Expose
    private ArrayList<BestBatAverage> mMostRunsAtVenue;

    public ArrayList<BestBatAverage> getMostRunsOverall() {
        return mMostRunsOverall;
    }

    public void setMostRunsOverall(ArrayList<BestBatAverage> mostRunsOverall) {
        mMostRunsOverall = mostRunsOverall;
    }

    public ArrayList<BestBatAverage> getMostRunsAtVenue() {
        return mMostRunsAtVenue;
    }

    public void setMostRunsAtVenue(ArrayList<BestBatAverage> mostRunsAtVenue) {
        mMostRunsAtVenue = mostRunsAtVenue;
    }

    public ArrayList<TableRow> convertToTableRows(ArrayList<BestBatAverage> bestBatAverages) {
        ArrayList<TableRow> tableRows = new ArrayList<>();
        tableRows.add(new TableRow("Batsman", "I", "R", "B", "Avg"));
        for (BestBatAverage bestBatAverage: bestBatAverages) {
            tableRows.add(new TableRow(bestBatAverage.getBatsman(), bestBatAverage.getMatches(),
                    bestBatAverage.getRuns(), bestBatAverage.getBalls(),
                    bestBatAverage.getAverage()));
        }
        return tableRows;
    }
}
