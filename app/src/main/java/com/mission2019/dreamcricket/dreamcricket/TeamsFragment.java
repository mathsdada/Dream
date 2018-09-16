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
    private TextView mTeamOneTextView;
    private TextView mTeamTwoTextView;
    private ConstraintLayout mTeamOneCardLayout;
    private RecyclerView mTeamOneFormRecyclerView;

    private ArrayList<Object> mTeamFormDataSet;
    private TeamFormRecyclerViewAdapter mTeamFormRecyclerViewAdapter;

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
        mTeamOneTextView = view.findViewById(R.id.team_a_title_tv);
        mTeamTwoTextView = view.findViewById(R.id.team_b_title_tv);
        mTeamOneCardLayout = view.findViewById(R.id.team_a_card_layout);

        mTeamOneFormRecyclerView = view.findViewById(R.id.recyclerview_team_a_form);
        mTeamFormDataSet = new ArrayList<>();
        JSONObject jsonObject = new JSONObject();
        for (int i=0; i < 10; i++) {
            mTeamFormDataSet.add(jsonObject);
        }
        mTeamFormRecyclerViewAdapter = new TeamFormRecyclerViewAdapter(mTeamFormDataSet);
        mTeamOneFormRecyclerView.setAdapter(mTeamFormRecyclerViewAdapter);
        mTeamOneFormRecyclerView.setLayoutManager(
                new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        try {
            JSONArray teamJsonArray = mMatchJSONObject.getJSONArray("match_teams");
            String teamA = teamJsonArray.getJSONObject(0).getString("team_name");
            String teamB = teamJsonArray.getJSONObject(1).getString("team_name");
            mTeamOneTextView.setText(teamA);
            mTeamTwoTextView.setText(teamB);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        mTeamOneTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTeamOneCardLayout.getLayoutTransition().enableTransitionType(LayoutTransition.CHANGING);
                if (mTeamOneCardLayout.getVisibility() == View.GONE) {
                    mTeamOneCardLayout.setVisibility(View.VISIBLE);
                    mTeamOneTextView.setCompoundDrawablesRelativeWithIntrinsicBounds(
                            0, 0, R.drawable.baseline_keyboard_arrow_up_24, 0);
                } else {
                    mTeamOneCardLayout.setVisibility(View.GONE);
                    mTeamOneTextView.setCompoundDrawablesRelativeWithIntrinsicBounds(
                            0, 0, R.drawable.baseline_keyboard_arrow_down_24, 0);
                }
            }
        });
    }

    public class TeamFormRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        private ArrayList<Object> mTeamFormDataSet;

        public TeamFormRecyclerViewAdapter(ArrayList<Object> mTeamFormDataSet) {
            this.mTeamFormDataSet = mTeamFormDataSet;
        }

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.team_form_card_item, parent, false);
            return new TeamFormViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
            ((TeamFormViewHolder)holder).bindViews((JSONObject) mTeamFormDataSet.get(position));
        }

        @Override
        public int getItemCount() {
            return 10;
        }

        private class TeamFormViewHolder extends RecyclerView.ViewHolder {

            TeamFormViewHolder(View seriesView) {
                super(seriesView);
            }

            void bindViews(JSONObject teamForm) {
            }

        }
    }
}
