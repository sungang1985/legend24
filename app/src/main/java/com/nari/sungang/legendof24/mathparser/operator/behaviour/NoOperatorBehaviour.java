package com.nari.sungang.legendof24.mathparser.operator.behaviour;

import com.nari.sungang.legendof24.mathparser.operator.OperatorBehaviour;
import com.nari.sungang.legendof24.mathparser.token.TokenStack;

/**
 * Created by sungang on 2016/1/8.
 */
public class NoOperatorBehaviour implements OperatorBehaviour {
    NoOperatorBehaviour() {
    }

    @Override
    public Double calculate(TokenStack tokenStack) {
        return null;
    }
}
