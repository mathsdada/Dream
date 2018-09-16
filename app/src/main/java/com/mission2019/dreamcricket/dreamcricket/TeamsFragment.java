package com.mission2019.dreamcricket.dreamcricket;

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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class TeamsFragment extends Fragment {
    private static final String TAG = TeamsFragment.class.getSimpleName();
    private JSONObject mMatchJSONObject;
    private TextView mTeamATitleTextView;
    private TextView mTeamBTitleTextView;
    private ConstraintLayout mTeamAStatsLayout;
    private RecyclerView mTeamAStatsFormRV;
    private RecyclerView mTeamAStatsMatchesRV;

    private ArrayList<Object> mTeamAStatsFormData;
    private TeamRecylerviewAdapter mTeamAStatsFormRVAdapter;
    private ArrayList<Object> mTeamAStatsMatchesData;
    private TeamRecylerviewAdapter mTeamAStatsMatchesRVAdapter;

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
        mTeamBTitleTextView = view.findViewById(R.id.team_b_title_tv);
        mTeamAStatsLayout = view.findViewById(R.id.team_a_card_layout);
        mTeamAStatsFormRV = view.findViewById(R.id.recyclerview_team_a_form);
        mTeamAStatsMatchesRV = view.findViewById(R.id.recyclerview_team_a_matches);


        /* Form */
        mTeamAStatsFormData = new ArrayList<>();
        JSONObject jsonObject = new JSONObject();
        for (int i=0; i < 10; i++) {
            mTeamAStatsFormData.add(jsonObject);
        }
        mTeamAStatsFormRVAdapter = new TeamRecylerviewAdapter(mTeamAStatsFormData, "TeamForm");
        mTeamAStatsFormRV.setAdapter(mTeamAStatsFormRVAdapter);
        mTeamAStatsFormRV.setLayoutManager(
                new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        /* Recent Matches */
        mTeamAStatsMatchesData = new ArrayList<>();
        for (int i=0; i < 10; i++) {
            mTeamAStatsMatchesData.add(jsonObject);
        }
        mTeamAStatsMatchesRVAdapter = new TeamRecylerviewAdapter(mTeamAStatsMatchesData, "TeamMatches");
        mTeamAStatsMatchesRV.setAdapter(mTeamAStatsMatchesRVAdapter);
        mTeamAStatsMatchesRV.setLayoutManager(
                new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        try {
            JSONArray teamJsonArray = mMatchJSONObject.getJSONArray("match_teams");
            String teamA = teamJsonArray.getJSONObject(0).getString("team_name");
            String teamB = teamJsonArray.getJSONObject(1).getString("team_name");
            mTeamATitleTextView.setText(teamA);
            mTeamBTitleTextView.setText(teamB);
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
    }

    public class TeamRecylerviewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        private ArrayList<Object> mDataSet;
        private String mDataType;

        public TeamRecylerviewAdapter(ArrayList<Object> mTeamFormDataSet, String dataType) {
            this.mDataSet = mTeamFormDataSet;
            this.mDataType = dataType;
        }

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            if (mDataType.equals("TeamForm")) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.team_form_card_item, parent, false);
                return new FormViewHolder(view);
            }
            if (mDataType.equals("TeamMatches")) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.team_matches_card_item, parent, false);
                return new MatchesViewHolder(view);
            }
            return null;
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
            if (mDataType.equals("TeamForm")) {
                ((FormViewHolder) holder).bindViews((JSONObject) mDataSet.get(position));
            } else if (mDataType.equals("TeamMatches")) {
                ((MatchesViewHolder) holder).bindViews((JSONObject) mDataSet.get(position));
            }
        }

        @Override
        public int getItemCount() {
            return mDataSet.size();
        }

        private class FormViewHolder extends RecyclerView.ViewHolder {

            FormViewHolder(View view) {
                super(view);
            }

            void bindViews(JSONObject teamForm) {
            }

        }

        private class MatchesViewHolder extends RecyclerView.ViewHolder {

            MatchesViewHolder(View view) {
                super(view);
            }

            void bindViews(JSONObject teamForm) {
            }

        }
    }
}
