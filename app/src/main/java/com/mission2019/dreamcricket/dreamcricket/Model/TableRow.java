package com.mission2019.dreamcricket.dreamcricket.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TableRow {
    private String colOne = null;
    private String colTwo = null;
    private String colThree = null;
    private String colFour = null;
    private String colFive = null;
    private String colSix = null;

    public TableRow(String colOne) {
        this.colOne = colOne;
    }

    public TableRow(String colOne, String colTwo) {
        this.colOne = colOne;
        this.colTwo = colTwo;
    }

    public TableRow(String colOne, String colTwo, String colThree) {
        this.colOne = colOne;
        this.colTwo = colTwo;
        this.colThree = colThree;
    }

    public TableRow(String colOne, String colTwo, String colThree, String colFour) {
        this.colOne = colOne;
        this.colTwo = colTwo;
        this.colThree = colThree;
        this.colFour = colFour;
    }

    public TableRow(String colOne, String colTwo, String colThree, String colFour, String colFive) {
        this.colOne = colOne;
        this.colTwo = colTwo;
        this.colThree = colThree;
        this.colFour = colFour;
        this.colFive = colFive;
    }

    public TableRow(String colOne, String colTwo, String colThree, String colFour, String colFive, String colSix) {
        this.colOne = colOne;
        this.colTwo = colTwo;
        this.colThree = colThree;
        this.colFour = colFour;
        this.colFive = colFive;
        this.colSix = colSix;
    }

    public String getColOne() {
        return colOne;
    }

    public String getColTwo() {
        return colTwo;
    }

    public String getColThree() {
        return colThree;
    }

    public String getColFour() {
        return colFour;
    }

    public String getColFive() {
        return colFive;
    }

    public String getColSix() {
        return colSix;
    }

    @Override
    public String toString() {
        return "TableRow {" +
                "col_1 :" + colOne +
                " col_2 :" + colTwo +
                " col_3 :" + colThree +
                " col_4 :" + colFour +
                " col_5 :" + colFive +
                " col_6 :" + colSix +
                "}";
    }
}
