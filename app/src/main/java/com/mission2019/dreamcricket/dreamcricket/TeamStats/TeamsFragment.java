package com.mission2019.dreamcricket.dreamcricket.TeamStats;

import android.animation.LayoutTransition;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mission2019.dreamcricket.dreamcricket.Server.JsonExtractor;
import com.mission2019.dreamcricket.dreamcricket.Server.LocalInterface;
import com.mission2019.dreamcricket.dreamcricket.MatchActivity;
import com.mission2019.dreamcricket.dreamcricket.R;
import com.mission2019.dreamcricket.dreamcricket.Server.SingletonServer;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class TeamsFragment extends Fragment implements SingletonServer.ServerEventListener {
    private static final String TAG = TeamsFragment.class.getSimpleName();
    private JSONObject mMatchJSONObject;
    private String mMatchFormat;

    private static final String ADAPTER_TYPE_FORM = "ADAPTER_TYPE_FORM";
    private static final String ADAPTER_TYPE_MATCHES = "ADAPTER_TYPE_MATCHES";
    private static final String ADAPTER_TYPE_TOP_BATSMEN = "ADAPTER_TYPE_TOP_BATSMEN";
    private static final String ADAPTER_TYPE_TOP_BOWLERS = "ADAPTER_TYPE_TOP_BOWLERS";

    private String mTeamATitle;
    private TextView mTeamATitleTextView;
    private ConstraintLayout mTeamAStatsLayout;
    private RecyclerView mTeamAStatsFormRV;
    private RecyclerView mTeamAStatsMatchesRV;
    private RecyclerView mTeamATopBatsmenRV;
    private RecyclerView mTeamATopBowlersRV;
    private ArrayList<Object> mTeamAStatsFormData;
    private TeamRecylerviewAdapter mTeamAStatsFormRVAdapter;
    private ArrayList<Object> mTeamAStatsMatchesData;
    private TeamRecylerviewAdapter mTeamAStatsMatchesRVAdapter;
    private ArrayList<Object> mTeamAStatsTopBatsmenData;
    private TeamRecylerviewAdapter mTeamAStatsTopBatsmenRVAdapter;
    private ArrayList<Object> mTeamAStatsTopBowlersData;
    private TeamRecylerviewAdapter mTeamAStatsTopBowlersRVAdapter;

    private String mTeamBTitle;
    private TextView mTeamBTitleTextView;
    private ConstraintLayout mTeamBStatsLayout;
    private RecyclerView mTeamBStatsFormRV;
    private RecyclerView mTeamBStatsMatchesRV;
    private RecyclerView mTeamBTopBatsmenRV;
    private RecyclerView mTeamBTopBowlersRV;
    private ArrayList<Object> mTeamBStatsFormData;
    private TeamRecylerviewAdapter mTeamBStatsFormRVAdapter;
    private ArrayList<Object> mTeamBStatsMatchesData;
    private TeamRecylerviewAdapter mTeamBStatsMatchesRVAdapter;
    private ArrayList<Object> mTeamBStatsTopBatsmenData;
    private TeamRecylerviewAdapter mTeamBStatsTopBatsmenRVAdapter;
    private ArrayList<Object> mTeamBStatsTopBowlersData;
    private TeamRecylerviewAdapter mTeamBStatsTopBowlersRVAdapter;

    private SingletonServer mServer;
    public TeamsFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String matchData = getArguments().getString(MatchActivity.KEY_MATCH_DATA);
        try {
            mMatchJSONObject = new JSONObject(matchData);
            Log.e(TAG, mMatchJSONObject.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_team_stats, container, false);
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mTeamATitleTextView = view.findViewById(R.id.team_a_title_tv);
        mTeamAStatsLayout = view.findViewById(R.id.team_a_card_layout);
        mTeamAStatsFormRV = view.findViewById(R.id.recyclerview_team_a_form);
        mTeamAStatsMatchesRV = view.findViewById(R.id.recyclerview_team_a_matches);
        mTeamATopBatsmenRV = view.findViewById(R.id.recyclerview_team_a_top_batsman);
        mTeamATopBowlersRV = view.findViewById(R.id.recyclerview_team_a_top_bowlers);

        mTeamBTitleTextView = view.findViewById(R.id.team_b_title_tv);
        mTeamBStatsLayout = view.findViewById(R.id.team_b_card_layout);
        mTeamBStatsFormRV = view.findViewById(R.id.recyclerview_team_b_form);
        mTeamBStatsMatchesRV = view.findViewById(R.id.recyclerview_team_b_matches);
        mTeamBTopBatsmenRV = view.findViewById(R.id.recyclerview_team_b_top_batsman);
        mTeamBTopBowlersRV = view.findViewById(R.id.recyclerview_team_b_top_bowlers);

        initializeTeamStatsForm();
        initializeTeamStatsRecentMatches();

        JSONObject jsonObject = new JSONObject();
        /* Top Batsman */
        mTeamAStatsTopBatsmenData = new ArrayList<>();
        for (int i=0; i < 10; i++) {
            mTeamAStatsTopBatsmenData.add(jsonObject);
        }
        mTeamAStatsTopBatsmenRVAdapter = new TeamRecylerviewAdapter(mTeamAStatsTopBatsmenData,
                ADAPTER_TYPE_TOP_BATSMEN);
        mTeamATopBatsmenRV.setAdapter(mTeamAStatsTopBatsmenRVAdapter);
        mTeamATopBatsmenRV.setLayoutManager(
                new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        /* Top Bowlers */
        mTeamAStatsTopBowlersData = new ArrayList<>();
        for (int i=0; i < 10; i++) {
            mTeamAStatsTopBowlersData.add(jsonObject);
        }
        mTeamAStatsTopBowlersRVAdapter = new TeamRecylerviewAdapter(mTeamAStatsTopBowlersData,
                ADAPTER_TYPE_TOP_BOWLERS);
        mTeamATopBowlersRV.setAdapter(mTeamAStatsTopBowlersRVAdapter);
        mTeamATopBowlersRV.setLayoutManager(
                new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        /* Top Batsman */
        mTeamBStatsTopBatsmenData = new ArrayList<>();
        for (int i=0; i < 10; i++) {
            mTeamBStatsTopBatsmenData.add(jsonObject);
        }
        mTeamBStatsTopBatsmenRVAdapter = new TeamRecylerviewAdapter(mTeamBStatsTopBatsmenData,
                ADAPTER_TYPE_TOP_BATSMEN);
        mTeamBTopBatsmenRV.setAdapter(mTeamBStatsTopBatsmenRVAdapter);
        mTeamBTopBatsmenRV.setLayoutManager(
                new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        /* Top Bowlers */
        mTeamBStatsTopBowlersData = new ArrayList<>();
        for (int i=0; i < 10; i++) {
            mTeamBStatsTopBowlersData.add(jsonObject);
        }
        mTeamBStatsTopBowlersRVAdapter = new TeamRecylerviewAdapter(mTeamBStatsTopBowlersData,
                ADAPTER_TYPE_TOP_BOWLERS);
        mTeamBTopBowlersRV.setAdapter(mTeamBStatsTopBowlersRVAdapter);
        mTeamBTopBowlersRV.setLayoutManager(
                new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        try {
            JSONArray teamJsonArray = mMatchJSONObject.getJSONArray("match_teams");
            mTeamATitle = teamJsonArray.getJSONObject(0).getString("team_name");
            mTeamBTitle = teamJsonArray.getJSONObject(1).getString("team_name");
            mTeamATitleTextView.setText(mTeamATitle);
            mTeamBTitleTextView.setText(mTeamBTitle);

            mMatchFormat = mMatchJSONObject.getString("match_format");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        mTeamATitleTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTeamAStatsLayout.getLayoutTransition().enableTransitionType(LayoutTransition.CHANGING);
                if (mTeamAStatsLayout.getVisibility() == View.GONE) {
                    mTeamAStatsLayout.setVisibility(View.VISIBLE);
                    mTeamATitleTextView.setCompoundDrawablesRelativeWithIntrinsicBounds(
                            0, 0, R.drawable.baseline_keyboard_arrow_up_24, 0);
                } else {
                    mTeamAStatsLayout.setVisibility(View.GONE);
                    mTeamATitleTextView.setCompoundDrawablesRelativeWithIntrinsicBounds(
                            0, 0, R.drawable.baseline_keyboard_arrow_down_24, 0);
                }
            }
        });
        mTeamBTitleTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTeamBStatsLayout.getLayoutTransition().enableTransitionType(LayoutTransition.CHANGING);
                if (mTeamBStatsLayout.getVisibility() == View.GONE) {
                    mTeamBStatsLayout.setVisibility(View.VISIBLE);
                    mTeamBTitleTextView.setCompoundDrawablesRelativeWithIntrinsicBounds(
                            0, 0, R.drawable.baseline_keyboard_arrow_up_24, 0);
                } else {
                    mTeamBStatsLayout.setVisibility(View.GONE);
                    mTeamBTitleTextView.setCompoundDrawablesRelativeWithIntrinsicBounds(
                            0, 0, R.drawable.baseline_keyboard_arrow_down_24, 0);
                }
            }
        });

        mServer = SingletonServer.getInstance();
        mServer.connect(this);
    }

    private void initializeTeamStatsForm() {
        mTeamAStatsFormData = new ArrayList<>();
        mTeamAStatsFormRVAdapter = new TeamRecylerviewAdapter(mTeamAStatsFormData,
                ADAPTER_TYPE_FORM);
        mTeamAStatsFormRV.setAdapter(mTeamAStatsFormRVAdapter);
        mTeamAStatsFormRV.setLayoutManager(
                new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        mTeamBStatsFormData = new ArrayList<>();
        mTeamBStatsFormRVAdapter = new TeamRecylerviewAdapter(mTeamBStatsFormData,
                ADAPTER_TYPE_FORM);
        mTeamBStatsFormRV.setAdapter(mTeamBStatsFormRVAdapter);
        mTeamBStatsFormRV.setLayoutManager(
                new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
    }

    private void initializeTeamStatsRecentMatches() {
        mTeamAStatsMatchesData = new ArrayList<>();
        mTeamAStatsMatchesRVAdapter = new TeamRecylerviewAdapter(mTeamAStatsMatchesData,
                ADAPTER_TYPE_MATCHES);
        mTeamAStatsMatchesRV.setAdapter(mTeamAStatsMatchesRVAdapter);
        mTeamAStatsMatchesRV.setLayoutManager(
                new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        mTeamBStatsMatchesData = new ArrayList<>();
        mTeamBStatsMatchesRVAdapter = new TeamRecylerviewAdapter(mTeamBStatsMatchesData,
                ADAPTER_TYPE_MATCHES);
        mTeamBStatsMatchesRV.setAdapter(mTeamBStatsMatchesRVAdapter);
        mTeamBStatsMatchesRV.setLayoutManager(
                new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
    }

    private void updateTeamFormOnUI(String teamName, TeamForm teamForm) {
        ArrayList<Object> dataSet;
        final TeamRecylerviewAdapter adapter;
        if (teamName.equals(mTeamATitle)) {
            dataSet = mTeamAStatsFormData;
            adapter = mTeamAStatsFormRVAdapter;
        } else {
            dataSet = mTeamBStatsFormData;
            adapter = mTeamBStatsFormRVAdapter;
        }
        dataSet.clear();
        dataSet.addAll(teamForm.getTeamForm());
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                adapter.notifyDataSetChanged();
            }
        });
    }

    private void updateTeamRecentMatchesOnUI(String teamName, TeamRecentMatches recentMatchesScorecards) {
        ArrayList<Object> dataSet;
        final TeamRecylerviewAdapter adapter;
        if (teamName.equals(mTeamATitle)) {
            dataSet = mTeamAStatsMatchesData;
            adapter = mTeamAStatsMatchesRVAdapter;
        } else {
            dataSet = mTeamBStatsMatchesData;
            adapter = mTeamBStatsMatchesRVAdapter;
        }
        dataSet.clear();
        dataSet.addAll(recentMatchesScorecards.getMatchScorecards());
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                adapter.notifyDataSetChanged();
            }
        });
    }

    private void updateTeamStatsOnUI(TeamStats teamStats) {
        updateTeamFormOnUI(teamStats.getTeamName(), teamStats.getTeamForm());
        updateTeamRecentMatchesOnUI(teamStats.getTeamName(), teamStats.getRecentMatchesScorecards());
    }

    @Override
    public void onServerEvent(Object... args) {
        String eventType = (String) args[0];
        switch (eventType) {
            case LocalInterface.EVENT_CONNECTION_SUCCESS: {
                ArrayList<String> teams = new ArrayList<>();
                teams.add(mTeamATitle); teams.add(mTeamBTitle);
                mServer.getTeamForm(teams, mMatchFormat);
                break;
            }
            case LocalInterface.EVENT_CONNECTION_ERROR:
            case LocalInterface.EVENT_CONNECTION_TIMEOUT: {
                // TODO: Display SnackBar with option Retry so that user can retry to connect again
                break;
            }
            case LocalInterface.EVENT_TEAM_STATS: {
                JSONArray teamStatsResponseJSONArray = (JSONArray) args[1];
                try {
                    for (int teamIndex = 0; teamIndex < teamStatsResponseJSONArray.length(); teamIndex++) {
                        JSONObject teamStatsResponseJSONObject =
                                teamStatsResponseJSONArray.getJSONObject(teamIndex);
                        updateTeamStatsOnUI(JsonExtractor.extractTeamStatsFromJSON(
                                (teamStatsResponseJSONObject)));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
    }


    public class TeamRecylerviewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        private ArrayList<Object> mDataSet;
        private String mDataType;

        TeamRecylerviewAdapter(ArrayList<Object> mTeamFormDataSet, String dataType) {
            this.mDataSet = mTeamFormDataSet;
            this.mDataType = dataType;
        }

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            switch (mDataType) {
                case ADAPTER_TYPE_FORM: {
                    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.team_form_card_item, parent, false);
                    return new FormViewHolder(view);
                }
                case ADAPTER_TYPE_MATCHES: {
                    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.team_matches_card_item, parent, false);
                    return new MatchesViewHolder(view);
                }
                case ADAPTER_TYPE_TOP_BATSMEN: {
                    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.team_top_batsmen_card_item, parent, false);
                    return new TopBatsmenViewHolder(view);
                }
                case ADAPTER_TYPE_TOP_BOWLERS: {
                    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.team_top_bowlers_card_item, parent, false);
                    return new TopBowlersViewHolder(view);
                }
            }
            return null;
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
            switch (mDataType) {
                case ADAPTER_TYPE_FORM:
                    ((FormViewHolder) holder).bindViews((TeamMatchOutcome) mDataSet.get(position));
                    break;
                case ADAPTER_TYPE_MATCHES:
                    ((MatchesViewHolder) holder).bindViews(
                            (MatchScorecard) mDataSet.get(position));
                    break;
                case ADAPTER_TYPE_TOP_BATSMEN:
                    ((TopBatsmenViewHolder) holder).bindViews((JSONObject) mDataSet.get(position));
                    break;
                case ADAPTER_TYPE_TOP_BOWLERS:
                    ((TopBowlersViewHolder) holder).bindViews((JSONObject) mDataSet.get(position));
                    break;
            }
        }

        @Override
        public int getItemCount() {
            return mDataSet.size();
        }

        private class FormViewHolder extends RecyclerView.ViewHolder {
            private TextView mWinLossTextView;
            private TextView mOppTeamTextView;
            FormViewHolder(View view) {
                super(view);
                mWinLossTextView = view.findViewById(R.id.team_form_win_loss_tv);
                mOppTeamTextView = view.findViewById(R.id.team_form_opp_team_tv);
            }

            void bindViews(TeamMatchOutcome teamMatchOutcome) {
                int color = getResources().getColor(R.color.teamOutcomeNR);
                String matchResult = teamMatchOutcome.getOutcome();
                String oppTeam = teamMatchOutcome.getOppTeam();

                mOppTeamTextView.setText("vs "+ oppTeam);
                Log.e(TAG, matchResult + " " + oppTeam);
                switch (matchResult) {
                    case "WIN" : {
                        matchResult = "W";
                        color = getResources().getColor(R.color.teamOutcomeWIN);
                        break;
                    }
                    case "LOST" : {
                        matchResult = "L";
                        color = getResources().getColor(R.color.teamOutcomeLOSS);
                        break;
                    }
                    case "DRAW" : {
                        matchResult = "D";
                        color = getResources().getColor(R.color.teamOutcomeDRAW);
                        break;
                    }
                    case "TIE": {
                        matchResult = "T";
                        color = getResources().getColor(R.color.teamOutcomeTIE);
                        break;
                    }
                    case "NO RESULT": {
                        matchResult = "NR";
                        color = getResources().getColor(R.color.teamOutcomeNR);
                        break;
                    }
                }
                mWinLossTextView.setText(matchResult);
                mWinLossTextView.setBackgroundColor(color);
            }

        }

        private class MatchesViewHolder extends RecyclerView.ViewHolder {
            private TextView mInningsOneTV;
            private TextView mInningsTwoTV;
            private TextView mInningsThreeTV;
            private TextView mInningsFourTV;
            MatchesViewHolder(View view) {
                super(view);
                mInningsOneTV = view.findViewById(R.id.team_matches_innings_1_tv);
                mInningsTwoTV = view.findViewById(R.id.team_matches_innings_2_tv);
                mInningsThreeTV = view.findViewById(R.id.team_matches_innings_3_tv);
                mInningsFourTV = view.findViewById(R.id.team_matches_innings_4_tv);
            }

            void bindViews(MatchScorecard recentMatches) {
                for (MatchInningsScore inningsScore : recentMatches.getMatchInningsScores()) {
                    String inningsScoreStr = inningsScore.getTeamName() + " " +
                            inningsScore.getRuns() + "-" + inningsScore.getWickets() + " (" +
                            inningsScore.getOvers() + ")";
                    switch (inningsScore.getInningsNumber()) {
                        case 0: {
                            mInningsOneTV.setText(inningsScoreStr);
                            mInningsOneTV.setVisibility(View.VISIBLE);
                            break;
                        }
                        case 1: {
                            mInningsTwoTV.setText(inningsScoreStr);
                            mInningsTwoTV.setVisibility(View.VISIBLE);
                            break;
                        }
                        case 2: {
                            mInningsThreeTV.setText(inningsScoreStr);
                            mInningsThreeTV.setVisibility(View.VISIBLE);
                            break;
                        }
                        case 3: {
                            mInningsFourTV.setText(inningsScoreStr);
                            mInningsFourTV.setVisibility(View.VISIBLE);
                            break;
                        }
                    }
                }
            }

        }

        private class TopBatsmenViewHolder extends RecyclerView.ViewHolder {

            TopBatsmenViewHolder(View view) {
                super(view);
            }

            void bindViews(JSONObject teamForm) {
            }

        }

        private class TopBowlersViewHolder extends RecyclerView.ViewHolder {

            TopBowlersViewHolder(View view) {
                super(view);
            }

            void bindViews(JSONObject teamForm) {
            }

        }
    }
}
