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
    public static final String KEY_MATCH_DATA_TEAM_FRAGMENT = "KEY_MATCH_DATA_TEAM_FRAGMENT";
    private JSONObject mMatchJSONObject;

    public TeamsFragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String matchData = getArguments().getString(KEY_MATCH_DATA_TEAM_FRAGMENT);
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
        View view = inflater.inflate(R.layout.fragment_match, container, false);
        TextView textView = view.findViewById(R.id.section_label);
        textView.setText("Team Fragment");
        return view;
    }
}
