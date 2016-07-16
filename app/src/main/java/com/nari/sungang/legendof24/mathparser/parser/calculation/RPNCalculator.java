package com.nari.sungang.legendof24.mathparser.parser.calculation;

import com.nari.sungang.legendof24.mathparser.token.TokenQueue;

/**
 * Created by sungang on 2016/1/7.
 */
public interface RPNCalculator {
    public void setRPNExpression(TokenQueue tokenQueue);

    public Result calculate24();
}
