package com.nari.sungang.legendof24.model;

/**
 * Created by sungang on 2016/1/3.
 */
public class Operation {
    private int resId;
    private String symbol;

    public Operation(int resId, String symbol) {
        this.resId = resId;
        this.symbol = symbol;
    }

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }
}
