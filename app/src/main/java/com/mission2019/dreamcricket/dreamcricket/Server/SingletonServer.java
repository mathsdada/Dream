package com.mission2019.dreamcricket.dreamcricket.Server;

import android.util.Log;

import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URISyntaxException;
import java.util.ArrayList;

public class SingletonServer {
    private String TAG = SingletonServer.class.getSimpleName();
    private static SingletonServer instance = null;
    private Socket mSocket;
    private ServerEventListener mEventListener;
    private boolean isConnected = false;

    public interface ServerEventListener {
        void onServerEvent(Object... args);
    }

    private void deliverEvent(String eventSchedule, Object data) {
        if (mEventListener != null) {
            mEventListener.onServerEvent(eventSchedule, data);
        }
    }

    public static synchronized SingletonServer getInstance() {
        if (instance == null) {
            instance = new SingletonServer();
        }
        return instance;
    }

    private SingletonServer() {
        try {
            mSocket = IO.socket("http://192.168.0.105:5678");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        mSocket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                isConnected = true;
                deliverEvent(LocalInterface.EVENT_CONNECTION_SUCCESS, null);
            }
        });
        mSocket.on(Socket.EVENT_CONNECT_ERROR, new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                deliverEvent(LocalInterface.EVENT_CONNECTION_ERROR, null);
            }
        });
        mSocket.on(Socket.EVENT_CONNECT_TIMEOUT, new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                deliverEvent(LocalInterface.EVENT_CONNECTION_TIMEOUT, null);
            }
        });
        mSocket.on(Socket.EVENT_DISCONNECT, new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                isConnected = false;
            }
        });

        mSocket.on("response", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                JSONObject responseJson = (JSONObject) args[0];
                Log.e(TAG, responseJson.toString());
                try {
                    String responseType = responseJson.getString("type");
                    switch (responseType) {
                        case RemoteInterface.RESP_SCHEDULE: {
                            JSONArray responseData = responseJson.getJSONArray("data");
                            deliverEvent(LocalInterface.EVENT_SCHEDULE, responseData);
                            break;
                        }
                        case RemoteInterface.RESP_TEAM_STATS: {
                            JSONArray responseData = responseJson.getJSONArray("data");
                            deliverEvent(LocalInterface.EVENT_TEAM_STATS, responseData);
                            break;
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void query(String type, String data) {
        JSONObject query_json = new JSONObject();
        try {
            query_json.put("type", type);
            query_json.put("data", data);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.e(TAG, "Sending Query to Server " + query_json.toString());
        mSocket.emit("query", query_json.toString());
    }

    public void connect(ServerEventListener listener) {
        mEventListener = listener;
        mSocket.connect();
    }

    public void disconnect() {
        mEventListener = null;
        if (isConnected) {
            mSocket.disconnect();
        }
    }

    public void getSchedule() {
        query(RemoteInterface.QUERY_SCHEDULE, "");
    }
    public void getTeamForm(ArrayList<String> teamNames, String matchFormat) {
        JSONObject queryJSONObject = new JSONObject();
        try {
            queryJSONObject.put("format", matchFormat);
            JSONArray teamJSONArray = new JSONArray();
            for (String teamName : teamNames) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("team_name", teamName);
                teamJSONArray.put(jsonObject);
            }
            queryJSONObject.put("teams", teamJSONArray);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        query(RemoteInterface.QUERY_TEAM_STATS, queryJSONObject.toString());
    }

    //    private void processScheduleJsonArray(JSONArray seriesJsonArray) {
//        for (int seriesIndex = 0; seriesIndex < seriesJsonArray.length(); seriesIndex++) {
//            try {
//                JSONObject seriesJsonObject = seriesJsonArray.getJSONObject(seriesIndex);
//                Log.e(TAG, "Series: " + seriesJsonObject.getString("series_title"));
//                JSONArray matchJsonArray = seriesJsonObject.getJSONArray("series_data");
//                for (int matchIndex = 0; matchIndex < matchJsonArray.length(); matchIndex++) {
//                    JSONObject matchJsonObject = matchJsonArray.getJSONObject(matchIndex);
//                    Log.e(TAG, "Match: " +
//                            " title-"+matchJsonObject.getString("match_title") +
//                            " format-"+matchJsonObject.getString("match_format")+
//                            " time-"+matchJsonObject.getString("match_time")+
//                            " gender-"+matchJsonObject.getString("match_gender")+
//                            " venue-"+matchJsonObject.getString("match_venue")
//                    );
//                    JSONArray teamJsonArray = matchJsonObject.getJSONArray("match_teams");
//                    for (int teamIndex = 0; teamIndex < teamJsonArray.length(); teamIndex++) {
//                        JSONObject teamJsonObject = teamJsonArray.getJSONObject(teamIndex);
//                        Log.e(TAG, "Team: " + teamJsonObject.getString("team_name"));
//                        JSONArray playerJsonArray = teamJsonObject.getJSONArray("team_squad");
//                        for (int playerIndex = 0; playerIndex < playerJsonArray.length(); playerIndex++) {
//                            JSONObject playerJsonObject = playerJsonArray.getJSONObject(playerIndex);
//                            Log.e(TAG, "Player: " +
//                                    " name-"+playerJsonObject.getString("player_name")+
//                                    " role-"+playerJsonObject.getString("player_role")+
//                                    " batting_style-"+playerJsonObject.getString("player_batting_style")+
//                                    " bowling_style-"+playerJsonObject.getString("player_bowling_style")+
//                                    " gender-"+playerJsonObject.getString("player_gender")
//                            );
//                        }
//                    }
//                }
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//        }
//    }
}
