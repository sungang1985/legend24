package com.nari.sungang.legendof24.mathparser.token.impl;

import com.nari.sungang.legendof24.mathparser.token.TokenQueue;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sungang on 2016/1/7.
 */
public class TokenQueueImpl implements TokenQueue {
    private List<String> tokens;
    private StringBuffer expression;


    public TokenQueueImpl() {
        tokens = new ArrayList<String>();
        expression = new StringBuffer();
    }

    @Override
    public List<String> getTokens() {
        return tokens;
    }

    @Override
    public void addToken(String token) {
        tokens.add(token);
        expression.append(token);
    }

    @Override
    public int size() {
        return tokens.size();
    }

    @Override
    public String toExpressionString() {
        return expression.toString();
    }

    @Override
    public String getTokenAt(int pointer) {
        return tokens.get(pointer);
    }

    @Override
    public boolean hasMoreTokens() {
        return tokens.size() > 0;
    }

    @Override
    public String getLastToken() {
        if (hasMoreTokens()) {
            return tokens.get(tokens.size() - 1);
        }
        return null;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("[");
        for (int i = 0; i < tokens.size(); i++) {
            if (i == tokens.size() - 1) {
                sb.append(" ".concat(tokens.get(i)));
            } else {
                sb.append(" ".concat(tokens.get(i).concat(";")));
            }
        }
        sb.append("]");
        return sb.toString();
    }
}
