package com.mission2019.dreamcricket.dreamcricket;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ScheduleActivity extends AppCompatActivity implements SingletonServer.ServerEventListener, ScheduleRecyclerViewAdapter.MatchCardItemClickListener {
    public static final String TAG = ScheduleActivity.class.getSimpleName();
    private static final String PERSIST_KEY_SCHEDULE = "PERSIST_KEY_SCHEDULE";
    private static final String PERSIST_KEY_SCHEDULE_DATE = "PERSIST_KEY_SCHEDULE_DATE";
    private String dateFormat = "dd-MM-yyyy";
    private JSONArray mSeriesJsonArray;
    private ArrayList<Object> mScheduleAdapterDataSet;
    private ScheduleRecyclerViewAdapter mScheduleRecyclerViewAdapter;
    private SingletonServer mServer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mServer = SingletonServer.getInstance();
        mScheduleAdapterDataSet = new ArrayList<>();
        RecyclerView recyclerViewSchedule = findViewById(R.id.recyclerview_schedule);
        recyclerViewSchedule.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL));
        mScheduleRecyclerViewAdapter = new ScheduleRecyclerViewAdapter(mScheduleAdapterDataSet, this);
        recyclerViewSchedule.setAdapter(mScheduleRecyclerViewAdapter);
        recyclerViewSchedule.setLayoutManager(new LinearLayoutManager(this));
    }

    private void saveInstanceState() {
        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(PERSIST_KEY_SCHEDULE, mSeriesJsonArray.toString());
        editor.putString(PERSIST_KEY_SCHEDULE_DATE, Utility.getCurrentDate(dateFormat));
        editor.apply();
    }

    private void restoreInstanceState() {
        mSeriesJsonArray = null;

        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        String savedDate = sharedPref.getString(PERSIST_KEY_SCHEDULE_DATE, "");
        String currentDate = Utility.getCurrentDate(dateFormat);
        if(savedDate.equals(currentDate)) {
            try {
                String scheduleJsonString = sharedPref.getString(PERSIST_KEY_SCHEDULE, "");
                if(scheduleJsonString.length() != 0) {
                    mSeriesJsonArray = new JSONArray(scheduleJsonString);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.clear();
        editor.apply();
    }

    @Override
    protected void onResume() {
        super.onResume();
        restoreInstanceState();
        if (mSeriesJsonArray == null) {
            mServer.connect(this);
        } else {
            updateScheduleAdapterDataSet(mSeriesJsonArray);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        saveInstanceState();
        mServer.disconnect();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_schedule, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onServerEvent(final Object... args) {
        String eventType = (String) args[0];
        switch (eventType) {
            case LocalInterface.EVENT_CONNECTION_SUCCESS: {
                mServer.getSchedule();
                break;
            }
            case LocalInterface.EVENT_CONNECTION_ERROR:
            case LocalInterface.EVENT_CONNECTION_TIMEOUT: {
                // TODO: Display SnackBar with option Retry so that user can retry to connect again
                break;
            }
            case LocalInterface.EVENT_SCHEDULE: {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mSeriesJsonArray = (JSONArray) args[1];
                        updateScheduleAdapterDataSet(mSeriesJsonArray);
                    }
                });
                break;
            }
        }
    }

    private void updateScheduleAdapterDataSet(JSONArray seriesJsonArray) {
        mScheduleAdapterDataSet.clear();
        for (int seriesIndex = 0; seriesIndex < seriesJsonArray.length(); seriesIndex++) {
            try {
                JSONObject seriesJsonObject = seriesJsonArray.getJSONObject(seriesIndex);
                mScheduleAdapterDataSet.add(seriesJsonObject.getString("series_title"));
                JSONArray matchJsonArray = seriesJsonObject.getJSONArray("series_data");
                for (int matchIndex = 0; matchIndex < matchJsonArray.length(); matchIndex++) {
                    JSONObject matchJsonObject = matchJsonArray.getJSONObject(matchIndex);
                    mScheduleAdapterDataSet.add(matchJsonObject);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        /* Add one dummy series at the end */
        mScheduleAdapterDataSet.add("");
        mScheduleRecyclerViewAdapter.notifyDataSetChanged();
    }

    @Override
    public void onMatchCardItemClick(int pos) {
        Intent intent = new Intent(ScheduleActivity.this, MatchActivity.class);
        JSONObject jsonObject = (JSONObject) mScheduleAdapterDataSet.get(pos);
        intent.putExtra(MatchActivity.KEY_MATCH_DATA, jsonObject.toString());
        startActivity(intent);
    }
}
