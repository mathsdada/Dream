package com.mission2019.dreamcricket.dreamcricket.Rest;

import com.mission2019.dreamcricket.dreamcricket.Model.PlayerStats.Batting.BattingRecentForm;
import com.mission2019.dreamcricket.dreamcricket.Model.PlayerStats.Batting.BattingRecentFormResponse;
import com.mission2019.dreamcricket.dreamcricket.Model.PlayerStats.Batting.BattingVsBowlerResponse;
import com.mission2019.dreamcricket.dreamcricket.Model.PlayerStats.Batting.BattingVsBowlingStylesResponse;
import com.mission2019.dreamcricket.dreamcricket.Model.PlayerStats.Bowling.BowlingRecentForm;
import com.mission2019.dreamcricket.dreamcricket.Model.PlayerStats.Bowling.BowlingRecentFormResponse;
import com.mission2019.dreamcricket.dreamcricket.Model.PlayerStats.Bowling.BowlingVsBatsmanResponse;
import com.mission2019.dreamcricket.dreamcricket.Model.PlayerStats.Bowling.BowlingVsBattingStylesResponse;
import com.mission2019.dreamcricket.dreamcricket.Model.PlayerStats.PlayerQuery;
import com.mission2019.dreamcricket.dreamcricket.Model.Schedule.ScheduleResponse;
import com.mission2019.dreamcricket.dreamcricket.Model.TeamStats.Batting.BattingBestAverageResponse;
import com.mission2019.dreamcricket.dreamcricket.Model.TeamStats.Batting.BattingBestStrikeRateResponse;
import com.mission2019.dreamcricket.dreamcricket.Model.TeamStats.Batting.BattingMost100sResponse;
import com.mission2019.dreamcricket.dreamcricket.Model.TeamStats.Batting.BattingMost4sResponse;
import com.mission2019.dreamcricket.dreamcricket.Model.TeamStats.Batting.BattingMost50sResponse;
import com.mission2019.dreamcricket.dreamcricket.Model.TeamStats.Batting.BattingMost6sResponse;
import com.mission2019.dreamcricket.dreamcricket.Model.TeamStats.Batting.BattingHighScoresResponse;
import com.mission2019.dreamcricket.dreamcricket.Model.TeamStats.Batting.BattingMostDucksResponse;
import com.mission2019.dreamcricket.dreamcricket.Model.TeamStats.Batting.BattingMostRunsResponse;
import com.mission2019.dreamcricket.dreamcricket.Model.TeamStats.Bowling.BowlingBestBowlingFigureResponse;
import com.mission2019.dreamcricket.dreamcricket.Model.TeamStats.Bowling.BowlingBestEconomyResponse;
import com.mission2019.dreamcricket.dreamcricket.Model.TeamStats.Bowling.BowlingBestStrikeRateResponse;
import com.mission2019.dreamcricket.dreamcricket.Model.TeamStats.Bowling.BowlingMost4PlusWicketsResponse;
import com.mission2019.dreamcricket.dreamcricket.Model.TeamStats.Bowling.BowlingMost5PlusWicketsResponse;
import com.mission2019.dreamcricket.dreamcricket.Model.TeamStats.Bowling.BowlingMostMaidensResponse;
import com.mission2019.dreamcricket.dreamcricket.Model.TeamStats.Bowling.BowlingMostRunsConcededResponse;
import com.mission2019.dreamcricket.dreamcricket.Model.TeamStats.Bowling.BowlingMostWicketsResponse;
import com.mission2019.dreamcricket.dreamcricket.Model.TeamStats.HeadToHead.HeadToHeadRunsVsBowlersResponse;
import com.mission2019.dreamcricket.dreamcricket.Model.TeamStats.HeadToHead.HeadToHeadRunsVsBowlingStylesResponse;
import com.mission2019.dreamcricket.dreamcricket.Model.TeamStats.RecentMatches.MatchScoresResponse;
import com.mission2019.dreamcricket.dreamcricket.Model.TeamStats.TeamQuery;
import com.mission2019.dreamcricket.dreamcricket.Model.TeamStats.RecentMatches.TeamFormResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface APIQuery {
    // Get Schedule
    @GET("schedule")
    Call<ScheduleResponse> getSchedule();

    // Team Stats
    @POST("team_stats/recent_form")
    Call<TeamFormResponse> getTeamRecentForm(@Body TeamQuery query);

    @POST("/team_stats/recent_match_scores")
    Call<MatchScoresResponse> getTeamStatsRecentMatchScores(@Body TeamQuery query);

    @POST("team_stats/batting/most_runs")
    Call<BattingMostRunsResponse> getTeamStatsBattingMostRuns(@Body TeamQuery query);

    @POST("/team_stats/batting/best_average")
    Call<BattingBestAverageResponse> getBestBatAverage(@Body TeamQuery query);

    @POST("/team_stats/batting/best_strike_rate")
    Call<BattingBestStrikeRateResponse> getBestBatStrikeRate(@Body TeamQuery query);

    @POST("/team_stats/batting/most_50s")
    Call<BattingMost50sResponse> getBattingMost50s(@Body TeamQuery query);

    @POST("/team_stats/batting/most_100s")
    Call<BattingMost100sResponse> getBattingMost100s(@Body TeamQuery query);

    @POST("/team_stats/batting/most_4s")
    Call<BattingMost4sResponse> getBattingMost4s(@Body TeamQuery query);

    @POST("/team_stats/batting/most_6s")
    Call<BattingMost6sResponse> getBattingMost6s(@Body TeamQuery query);

    @POST("/team_stats/batting/most_ducks")
    Call<BattingMostDucksResponse> getBattingMostDucks(@Body TeamQuery query);

    @POST("/team_stats/batting/high_scores")
    Call<BattingHighScoresResponse> getBattingHighScores(@Body TeamQuery query);

    @POST("/team_stats/bowling/most_wickets")
    Call<BowlingMostWicketsResponse> getBowlingMostWickets(@Body TeamQuery query);

    @POST("/team_stats/bowling/best_economy")
    Call<BowlingBestEconomyResponse> getBowlingBestEconomy(@Body TeamQuery query);

    @POST("/team_stats/bowling/best_strike_rate")
    Call<BowlingBestStrikeRateResponse> getBowlingBestStrikeRate(@Body TeamQuery query);

    @POST("/team_stats/bowling/most_4_plus_wickets")
    Call<BowlingMost4PlusWicketsResponse> getBowlingMost4PlusWickets(@Body TeamQuery query);

    @POST("/team_stats/bowling/most_5_plus_wickets")
    Call<BowlingMost5PlusWicketsResponse> getBowlingMost5PlusWickets(@Body TeamQuery query);

    @POST("/team_stats/bowling/most_maidens")
    Call<BowlingMostMaidensResponse> getBowlingMostMaidens(@Body TeamQuery query);

    @POST("/team_stats/bowling/best_bowling_in_innings")
    Call<BowlingBestBowlingFigureResponse> getBowlingBestFiguresInInnings(@Body TeamQuery query);

    @POST("/team_stats/bowling/most_runs_conceded_in_innings")
    Call<BowlingMostRunsConcededResponse> getBowlingMostRunsConcededInInnings(@Body TeamQuery query);

    @POST("/team_stats/head_to_head/runs_against_bowling_styles")
    Call<HeadToHeadRunsVsBowlingStylesResponse> getHeadToHeadRunsVsBowlingStyles(@Body TeamQuery query);

    @POST("/team_stats/head_to_head/runs_against_bowlers")
    Call<HeadToHeadRunsVsBowlersResponse> getHeadToHeadRunsVsBowlers(@Body TeamQuery query);

    // Player Stats
    @POST("/player_stats/batting/recent_form")
    Call<BattingRecentFormResponse> getPlayerRecentBattingForm(@Body PlayerQuery query);

    @POST("/player_stats/batting/runs_vs_bowling_styles")
    Call<BattingVsBowlingStylesResponse> getPlayerBattingVsBowlingStyles(@Body PlayerQuery query);

    @POST("/player_stats/batting/runs_vs_bowlers")
    Call<BattingVsBowlerResponse> getPlayerBattingVsBowlers(@Body PlayerQuery query);

    @POST("/player_stats/bowling/recent_form")
    Call<BowlingRecentFormResponse> getPlayerRecentBowlingForm(@Body PlayerQuery query);

    @POST("/player_stats/bowling/wickets_vs_batting_styles")
    Call<BowlingVsBattingStylesResponse> getPlayerBowlingVsBattingStyles(@Body PlayerQuery query);

    @POST("/player_stats/bowling/wickets_vs_batsmen")
    Call<BowlingVsBatsmanResponse> getPlayerBowlingVsBatsmen(@Body PlayerQuery query);
}
