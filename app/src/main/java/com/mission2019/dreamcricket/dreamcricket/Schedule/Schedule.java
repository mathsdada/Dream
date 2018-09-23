package com.mission2019.dreamcricket.dreamcricket.Schedule;

import java.util.ArrayList;

public class Schedule {
    private String mDate;
    private ArrayList<Series> mSerieses;

    public Schedule(String date) {
        mDate = date;
        mSerieses = new ArrayList<>();
    }

    public Schedule(String date, ArrayList<Series> serieses) {
        mDate = date;
        mSerieses = serieses;
    }

    public void addSeries(Series series) {
        mSerieses.add(series);
    }

    public String getDate() {
        return mDate;
    }

    public ArrayList<Series> getSerieses() {
        return mSerieses;
    }
}
