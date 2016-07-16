package com.nari.sungang.legendof24.mathparser.token.impl;

import com.nari.sungang.legendof24.mathparser.token.TokenStack;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sungang on 2016/1/7.
 */
public class TokenStackImpl implements TokenStack {
    private List<String> tokens;

    public TokenStackImpl() {
        tokens = new ArrayList<String>();
    }

    @Override
    public void push(String token) {
        tokens.add(token);
    }

    @Override
    public String pop() {
        String value = tokens.get(tokens.size() - 1);
        tokens.remove(tokens.size() - 1);
        return value;
    }

    @Override
    public String getTop() {
        return tokens.get(tokens.size() - 1);
    }

    @Override
    public boolean hasMoreElements() {
        return tokens.size() > 0;
    }

    @Override
    public int size() {
        return tokens.size();
    }
}
