package com.nari.sungang.legendof24.mathparser.token;

import com.nari.sungang.legendof24.mathparser.token.impl.TokenQueueImpl;
import com.nari.sungang.legendof24.mathparser.token.impl.TokenStackImpl;

/**
 * Created by sungang on 2016/1/7.
 */
public final class TokenFactory {
    private TokenFactory() {
    }

    public static TokenQueue createQueue() {
        return new TokenQueueImpl();
    }

    public static TokenStack createStack() {
        return new TokenStackImpl();
    }
}

