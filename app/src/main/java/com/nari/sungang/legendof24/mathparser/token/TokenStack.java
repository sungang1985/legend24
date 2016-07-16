package com.nari.sungang.legendof24.mathparser.token;

/**
 * Created by sungang on 2016/1/7.
 */
public interface TokenStack {
    public void push(String token);

    public String pop();

    public String getTop();

    public boolean hasMoreElements();

    public int size();
}
