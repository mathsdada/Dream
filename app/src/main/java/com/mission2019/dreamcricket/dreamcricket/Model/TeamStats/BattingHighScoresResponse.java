package com.mission2019.dreamcricket.dreamcricket.Model.TeamStats;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.mission2019.dreamcricket.dreamcricket.Model.TableRow;

import java.util.ArrayList;

public class BattingHighScoresResponse {
    @SerializedName("overall")
    @Expose
    private ArrayList<BattingHighScores> mMostRunsOverall;

    @SerializedName("atVenue")
    @Expose
    private ArrayList<BattingHighScores> mMostRunsAtVenue;

    public ArrayList<BattingHighScores> getOverallStats() {
        return mMostRunsOverall;
    }

    public void setMostRunsOverall(ArrayList<BattingHighScores> mostRunsOverall) {
        mMostRunsOverall = mostRunsOverall;
    }

    public ArrayList<BattingHighScores> getAtVenueStats() {
        return mMostRunsAtVenue;
    }

    public void setMostRunsAtVenue(ArrayList<BattingHighScores> mostRunsAtVenue) {
        mMostRunsAtVenue = mostRunsAtVenue;
    }

    public ArrayList<TableRow> convertToTableRows(ArrayList<BattingHighScores> mostDucks) {
        ArrayList<TableRow> tableRows = new ArrayList<>();
        tableRows.add(new TableRow("Batsman", "I", "R", "B", "HS"));
        for (BattingHighScores battingHighScores: mostDucks) {
            tableRows.add(new TableRow(battingHighScores.getBatsman(), battingHighScores.getMatches(),
                    battingHighScores.getRuns(), battingHighScores.getBalls(), battingHighScores.getHighScore()));
        }
        return tableRows;
    }
}
