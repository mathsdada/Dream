package com.mission2019.dreamcricket.dreamcricket.Model.TeamStats;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.mission2019.dreamcricket.dreamcricket.Model.TableRow;

import java.util.ArrayList;

public class BattingMost50sResponse {
    @SerializedName("overall")
    @Expose
    private ArrayList<BattingMost50s> mMostRunsOverall;

    @SerializedName("atVenue")
    @Expose
    private ArrayList<BattingMost50s> mMostRunsAtVenue;

    public ArrayList<BattingMost50s> getOverallStats() {
        return mMostRunsOverall;
    }

    public void setMostRunsOverall(ArrayList<BattingMost50s> mostRunsOverall) {
        mMostRunsOverall = mostRunsOverall;
    }

    public ArrayList<BattingMost50s> getAtVenueStats() {
        return mMostRunsAtVenue;
    }

    public void setMostRunsAtVenue(ArrayList<BattingMost50s> mostRunsAtVenue) {
        mMostRunsAtVenue = mostRunsAtVenue;
    }

    public ArrayList<TableRow> convertToTableRows(ArrayList<BattingMost50s> most50s) {
        ArrayList<TableRow> tableRows = new ArrayList<>();
        tableRows.add(new TableRow("Batsman", "I", "R", "B", "50s"));
        for (BattingMost50s battingMost50s: most50s) {
            tableRows.add(new TableRow(battingMost50s.getBatsman(), battingMost50s.getMatches(),
                    battingMost50s.getRuns(), battingMost50s.getBalls(), battingMost50s.getFifties()));
        }
        return tableRows;
    }
}
