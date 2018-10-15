package com.mission2019.dreamcricket.dreamcricket.Model.TeamStats;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.mission2019.dreamcricket.dreamcricket.Model.TableRow;

import java.util.ArrayList;

public class BattingMostDucksResponse {
    @SerializedName("overall")
    @Expose
    private ArrayList<BattingMostDucks> mMostRunsOverall;

    @SerializedName("atVenue")
    @Expose
    private ArrayList<BattingMostDucks> mMostRunsAtVenue;

    public ArrayList<BattingMostDucks> getOverallStats() {
        return mMostRunsOverall;
    }

    public void setMostRunsOverall(ArrayList<BattingMostDucks> mostRunsOverall) {
        mMostRunsOverall = mostRunsOverall;
    }

    public ArrayList<BattingMostDucks> getAtVenueStats() {
        return mMostRunsAtVenue;
    }

    public void setMostRunsAtVenue(ArrayList<BattingMostDucks> mostRunsAtVenue) {
        mMostRunsAtVenue = mostRunsAtVenue;
    }

    public ArrayList<TableRow> convertToTableRows(ArrayList<BattingMostDucks> mostDucks) {
        ArrayList<TableRow> tableRows = new ArrayList<>();
        tableRows.add(new TableRow("Batsman", "I", "R", "B", "0s"));
        for (BattingMostDucks battingMostDucks: mostDucks) {
            tableRows.add(new TableRow(battingMostDucks.getBatsman(), battingMostDucks.getMatches(),
                    battingMostDucks.getRuns(), battingMostDucks.getBalls(), battingMostDucks.getDucks()));
        }
        return tableRows;
    }
}
