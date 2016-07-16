package com.nari.sungang.legendof24.mathparser.parser.calculation.impl;

import com.nari.sungang.legendof24.mathparser.parser.calculation.Result;

/**
 * Created by sungang on 2016/1/7.
 */
public class ResultImpl implements Result {
    private Double result;

    public ResultImpl() {
        result = null;
    }

    @Override
    public Double doubleValue() {
        return result;
    }

    @Override
    public void setResult(Object result) {
        this.result = (Double) result;
    }
}
