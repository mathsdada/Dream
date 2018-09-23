package com.mission2019.dreamcricket.dreamcricket.Server;

import com.mission2019.dreamcricket.dreamcricket.Schedule.Match;
import com.mission2019.dreamcricket.dreamcricket.Schedule.Player;
import com.mission2019.dreamcricket.dreamcricket.Schedule.Schedule;
import com.mission2019.dreamcricket.dreamcricket.Schedule.Series;
import com.mission2019.dreamcricket.dreamcricket.Schedule.Team;
import com.mission2019.dreamcricket.dreamcricket.TeamStats.TeamMatchInningsScore;
import com.mission2019.dreamcricket.dreamcricket.TeamStats.TeamMatchScorecard;
import com.mission2019.dreamcricket.dreamcricket.TeamStats.TeamForm;
import com.mission2019.dreamcricket.dreamcricket.TeamStats.TeamMatchOutcome;
import com.mission2019.dreamcricket.dreamcricket.TeamStats.TeamRecentMatches;
import com.mission2019.dreamcricket.dreamcricket.TeamStats.TeamStats;
import com.mission2019.dreamcricket.dreamcricket.Utility;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

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

    public static Schedule extractSchedule(JSONArray seriesesJsonArray) {
        String date = Utility.getCurrentDate("dd-MM-yyyy");
        Schedule schedule = new Schedule(date);
        try {
            for (int seriesIndex = 0; seriesIndex < seriesesJsonArray.length(); seriesIndex++) {
                JSONObject seriesJsonObject = seriesesJsonArray.getJSONObject(seriesIndex);
                Series series = new Series(seriesJsonObject.getString("series_title"));
                JSONArray matchesJsonArray = seriesJsonObject.getJSONArray("series_data");
                for (int matchIndex = 0; matchIndex < matchesJsonArray.length(); matchIndex++) {
                    JSONObject matchJsonObject = matchesJsonArray.getJSONObject(matchIndex);
                    Match match = new Match(
                            matchJsonObject.getString("match_title"),
                            matchJsonObject.getString("match_format"),
                            matchJsonObject.getString("match_time"),
                            matchJsonObject.getString("match_venue"),
                            matchJsonObject.getString("match_gender"));
                    JSONArray teamJsonArray = matchJsonObject.getJSONArray("match_teams");
                    for (int teamIndex = 0; teamIndex < teamJsonArray.length(); teamIndex++) {
                        JSONObject teamJsonObject = teamJsonArray.getJSONObject(teamIndex);
                        Team team = new Team(
                                teamJsonObject.getString("team_name"),
                                teamJsonObject.getString("team_short_name"));
                        JSONArray playersJsonArray = teamJsonObject.getJSONArray("team_squad");
                        for (int playerIndex = 0; playerIndex < playersJsonArray.length(); playerIndex++) {
                            JSONObject playerJsonObject = playersJsonArray.getJSONObject(playerIndex);
                            Player player = new Player(
                                    playerJsonObject.getString("player_name"),
                                    playerJsonObject.getString("player_role"),
                                    playerJsonObject.getString("player_batting_style"),
                                    playerJsonObject.getString("player_bowling_style"),
                                    playerJsonObject.getString("player_gender"));
                            team.addPlayer(player);
                        }
                        match.addTeam(team);
                    }
                    series.addMatch(match);
                }
                schedule.addSeries(series);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return schedule;
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
                TeamMatchScorecard matchScorecard = new TeamMatchScorecard();
                JSONArray matchScoreCardJson = teamRecentMatchesJSONArray.getJSONArray(matchIndex);
                for (int inningsIndex = 0; inningsIndex < matchScoreCardJson.length(); inningsIndex++) {
                    JSONObject inningsScoreCardJson = matchScoreCardJson.getJSONObject(inningsIndex);
                    TeamMatchInningsScore matchInningsScore = new TeamMatchInningsScore(
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
