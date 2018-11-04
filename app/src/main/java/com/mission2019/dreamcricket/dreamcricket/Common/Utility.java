package com.mission2019.dreamcricket.dreamcricket.Common;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.google.gson.Gson;
import com.mission2019.dreamcricket.dreamcricket.Fragment.TableFragment;
import com.mission2019.dreamcricket.dreamcricket.Model.TableRow;
import com.mission2019.dreamcricket.dreamcricket.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class Utility {
    public static final String TEAM_STATS = "Team Stats";
    public static final String PLAYER_STATS = "Player Stats";
    public static final String VENUE_STATS = "Venue Stats";
    public static String convertEpochTime(String epochTimeStr) {
        long epochTime = Long.parseLong(epochTimeStr);
        Date date = new Date(epochTime*1000L);

        // https://docs.oracle.com/javase/7/docs/api/java/text/SimpleDateFormat.html
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm a");
        simpleDateFormat.setTimeZone(TimeZone.getDefault());
        return simpleDateFormat.format(date);
    }

    public static String getCurrentDate(String format) {
        Date date = Calendar.getInstance().getTime();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        return simpleDateFormat.format(date);
    }

    public static void StartTableActivity(FragmentActivity activity, String title, ArrayList<TableRow> tableRows) {
        Gson gson = new Gson();
        Bundle bundle = new Bundle();
        bundle.putString(TableFragment.KEY_DATA_TYPE, TableFragment.DATA_TYPE_TABLE_ROWS);
        bundle.putString(TableFragment.KEY_TITLE, title);
        bundle.putString(TableFragment.KEY_OVERALL_STATS,
                gson.toJson(tableRows));
        TableFragment fragment = new TableFragment();
        fragment.setArguments(bundle);
        activity.getSupportFragmentManager().beginTransaction().
                replace(R.id.container, fragment).
                addToBackStack(null).
                commit();
    }
}
