package com.mission2019.dreamcricket.dreamcricket.Server;

import com.mission2019.dreamcricket.dreamcricket.TeamStats.MatchInningsScore;
import com.mission2019.dreamcricket.dreamcricket.TeamStats.MatchScorecard;
import com.mission2019.dreamcricket.dreamcricket.TeamStats.TeamForm;
import com.mission2019.dreamcricket.dreamcricket.TeamStats.TeamMatchOutcome;
import com.mission2019.dreamcricket.dreamcricket.TeamStats.TeamRecentMatches;
import com.mission2019.dreamcricket.dreamcricket.TeamStats.TeamStats;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonExtractor {
    public static TeamStats extractTeamStatsFromJSON(JSONObject teamStatsResponseJSON) {
        TeamStats teamStats = null;
        try {
            String teamName = teamStatsResponseJSON.getString("team_name");
            teamStats = new TeamStats(teamName);

            JSONObject teamStatsJSON =  teamStatsResponseJSON.getJSONObject("team_stats");

            JSONArray teamFormDataJSONArray = teamStatsJSON.getJSONArray("form");
            JSONArray teamRecentMatchesJSONArray = teamStatsJSON.getJSONArray("recent_matches");
            teamStats.setTeamForm(JsonExtractorInternal.extractTeamForm(teamFormDataJSONArray));
            teamStats.setRecentMatchesScorecards(JsonExtractorInternal.extractTeamMatchScorecards(teamRecentMatchesJSONArray));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return teamStats;
    }

    public static class JsonExtractorInternal {
        static TeamForm extractTeamForm(JSONArray teamFormDataJSONArray) throws JSONException {
            TeamForm teamForm = new TeamForm();
            for (int matchIndex = 0; matchIndex < teamFormDataJSONArray.length(); matchIndex++) {
                JSONObject teamMatchOutcomeJSON = teamFormDataJSONArray.getJSONObject(matchIndex);
                String outcome = teamMatchOutcomeJSON.getString("outcome");
                String oppTeam = teamMatchOutcomeJSON.getString("opp_team");
                teamForm.setTeamMatchOutcome(new TeamMatchOutcome(outcome, oppTeam));
            }
            return teamForm;
        }

        static TeamRecentMatches extractTeamMatchScorecards(JSONArray teamRecentMatchesJSONArray) throws JSONException {
            TeamRecentMatches recentMatches = new TeamRecentMatches();
            for (int matchIndex = 0; matchIndex < teamRecentMatchesJSONArray.length(); matchIndex++) {
                MatchScorecard matchScorecard = new MatchScorecard();
                JSONArray matchScoreCardJson = teamRecentMatchesJSONArray.getJSONArray(matchIndex);
                for (int inningsIndex = 0; inningsIndex < matchScoreCardJson.length(); inningsIndex++) {
                    JSONObject inningsScoreCardJson = matchScoreCardJson.getJSONObject(inningsIndex);
                    MatchInningsScore matchInningsScore = new MatchInningsScore(
                            inningsScoreCardJson.getString("short_name"),
                            inningsScoreCardJson.getString("runs"),
                            inningsScoreCardJson.getString("wickets"),
                            inningsScoreCardJson.getString("overs"),
                            inningsScoreCardJson.getInt("innings_number")
                    );
                    matchScorecard.setMatchInningsScore(matchInningsScore);
                }
                recentMatches.setMatchScorecard(matchScorecard);
            }
            return recentMatches;
        }
    }
}
