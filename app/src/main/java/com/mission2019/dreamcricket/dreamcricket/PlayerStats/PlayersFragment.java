package com.mission2019.dreamcricket.dreamcricket.PlayerStats;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mission2019.dreamcricket.dreamcricket.MatchActivity;
import com.mission2019.dreamcricket.dreamcricket.R;

import org.json.JSONException;
import org.json.JSONObject;

public class PlayersFragment extends Fragment {
    private static String TAG = PlayersFragment.class.getSimpleName();
    private JSONObject mMatchJSONObject;

    public PlayersFragment() {
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
        View view = inflater.inflate(R.layout.fragment_match, container, false);
        TextView textView = view.findViewById(R.id.section_label);
        textView.setText("Players Fragment");
        return view;
    }
}
