package com.nari.sungang.legendof24.mathparser.token;

import java.util.List;

/**
 * Created by sungang on 2016/1/7.
 */
public interface TokenQueue {
    public List<String> getTokens();

    public void addToken(String token);

    public int size();

    public String toString();

    public String toExpressionString();

    public String getTokenAt(int pointer);

    public boolean hasMoreTokens();

    public String getLastToken();
}
