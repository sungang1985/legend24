package com.nari.sungang.legendof24.mathparser.operator;

import com.nari.sungang.legendof24.mathparser.token.TokenStack;

/**
 * Created by sungang on 2016/1/7.
 */
public interface OperatorBehaviour {
    public Double calculate(TokenStack tokenStack);
}
