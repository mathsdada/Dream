package com.mission2019.dreamcricket.dreamcricket.Model.TeamStats;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.mission2019.dreamcricket.dreamcricket.Model.TableRow;

import java.util.ArrayList;

public class BattingMost6sResponse {
    @SerializedName("overall")
    @Expose
    private ArrayList<BattingMost6s> mMostRunsOverall;

    @SerializedName("atVenue")
    @Expose
    private ArrayList<BattingMost6s> mMostRunsAtVenue;

    public ArrayList<BattingMost6s> getOverallStats() {
        return mMostRunsOverall;
    }

    public void setMostRunsOverall(ArrayList<BattingMost6s> mostRunsOverall) {
        mMostRunsOverall = mostRunsOverall;
    }

    public ArrayList<BattingMost6s> getAtVenueStats() {
        return mMostRunsAtVenue;
    }

    public void setMostRunsAtVenue(ArrayList<BattingMost6s> mostRunsAtVenue) {
        mMostRunsAtVenue = mostRunsAtVenue;
    }

    public ArrayList<TableRow> convertToTableRows(ArrayList<BattingMost6s> most6s) {
        ArrayList<TableRow> tableRows = new ArrayList<>();
        tableRows.add(new TableRow("Batsman", "I", "R", "B", "6s"));
        for (BattingMost6s battingMost6s: most6s) {
            tableRows.add(new TableRow(battingMost6s.getBatsman(), battingMost6s.getMatches(),
                    battingMost6s.getRuns(), battingMost6s.getBalls(), battingMost6s.getSixes()));
        }
        return tableRows;
    }
}
