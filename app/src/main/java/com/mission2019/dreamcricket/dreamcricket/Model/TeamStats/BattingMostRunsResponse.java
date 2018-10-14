package com.mission2019.dreamcricket.dreamcricket.Model.TeamStats;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.mission2019.dreamcricket.dreamcricket.Model.TableRow;

import java.util.ArrayList;

public class BattingMostRunsResponse {
    @SerializedName("overall")
    @Expose
    private ArrayList<BattingMostRuns> mMostRunsOverall;

    @SerializedName("atVenue")
    @Expose
    private ArrayList<BattingMostRuns> mMostRunsAtVenue;

    public ArrayList<BattingMostRuns> getMostRunsOverall() {
        return mMostRunsOverall;
    }

    public void setMostRunsOverall(ArrayList<BattingMostRuns> mostRunsOverall) {
        mMostRunsOverall = mostRunsOverall;
    }

    public ArrayList<BattingMostRuns> getMostRunsAtVenue() {
        return mMostRunsAtVenue;
    }

    public void setMostRunsAtVenue(ArrayList<BattingMostRuns> mostRunsAtVenue) {
        mMostRunsAtVenue = mostRunsAtVenue;
    }

    public ArrayList<TableRow> convertToTableRows(ArrayList<BattingMostRuns> mostRuns) {
        ArrayList<TableRow> tableRows = new ArrayList<>();
        tableRows.add(new TableRow("Batsman", "I", "R", "B"));
        for (BattingMostRuns battingMostRuns: mostRuns) {
            tableRows.add(new TableRow(battingMostRuns.getBatsman(), battingMostRuns.getMatches(),
                    battingMostRuns.getRuns(), battingMostRuns.getBalls()));
        }
        return tableRows;
    }
}
