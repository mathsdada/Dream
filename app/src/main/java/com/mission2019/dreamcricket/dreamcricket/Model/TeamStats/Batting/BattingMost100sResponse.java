package com.mission2019.dreamcricket.dreamcricket.Model.TeamStats.Batting;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.mission2019.dreamcricket.dreamcricket.Model.TableRow;

import java.util.ArrayList;

public class BattingMost100sResponse {
    @SerializedName("overall")
    @Expose
    private ArrayList<BattingMost100s> mMostRunsOverall;

    @SerializedName("atVenue")
    @Expose
    private ArrayList<BattingMost100s> mMostRunsAtVenue;

    public ArrayList<BattingMost100s> getOverallStats() {
        return mMostRunsOverall;
    }

    public void setMostRunsOverall(ArrayList<BattingMost100s> mostRunsOverall) {
        mMostRunsOverall = mostRunsOverall;
    }

    public ArrayList<BattingMost100s> getAtVenueStats() {
        return mMostRunsAtVenue;
    }

    public void setMostRunsAtVenue(ArrayList<BattingMost100s> mostRunsAtVenue) {
        mMostRunsAtVenue = mostRunsAtVenue;
    }

    public ArrayList<TableRow> convertToTableRows(ArrayList<BattingMost100s> most100s) {
        ArrayList<TableRow> tableRows = new ArrayList<>();
        tableRows.add(new TableRow("Batsman", "I", "R", "B", "100s"));
        for (BattingMost100s battingMost100s: most100s) {
            tableRows.add(new TableRow(battingMost100s.getBatsman(), battingMost100s.getMatches(),
                    battingMost100s.getRuns(), battingMost100s.getBalls(), battingMost100s.getHundreds()));
        }
        return tableRows;
    }
}
