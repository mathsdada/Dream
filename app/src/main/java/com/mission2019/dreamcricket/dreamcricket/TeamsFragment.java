package com.mission2019.dreamcricket.dreamcricket;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class TeamsFragment extends Fragment {
    private static final String TAG = TeamsFragment.class.getSimpleName();
    private JSONObject mMatchJSONObject;

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
        View view = inflater.inflate(R.layout.fragment_team_stats, container, false);
        TextView teamOneTextView = view.findViewById(R.id.team_a_title_tv);
        TextView teamTwoTextView = view.findViewById(R.id.team_b_title_tv);

        try {
            JSONArray teamJsonArray = mMatchJSONObject.getJSONArray("match_teams");
            String teamA = teamJsonArray.getJSONObject(0).getString("team_name");
            String teamB = teamJsonArray.getJSONObject(1).getString("team_name");
            teamOneTextView.setText(teamA);
            teamTwoTextView.setText(teamB);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return view;
    }
}
