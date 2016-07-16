package com.nari.sungang.legendof24.mathparser.operator.behaviour;

import com.nari.sungang.legendof24.mathparser.operator.OperatorBehaviour;
import com.nari.sungang.legendof24.mathparser.token.TokenFactory;
import com.nari.sungang.legendof24.mathparser.token.TokenStack;

/**
 * Created by sungang on 2016/1/7.
 */
public class MinusOperatorBehaviour implements OperatorBehaviour {
    private Double argument1;
    private Double argument2;
    private String symbol;
    private int neededArguments;

    MinusOperatorBehaviour(String symbol, int neededArguments) {
        this.symbol = symbol;
        this.neededArguments = neededArguments;
    }

    @Override
    public Double calculate(TokenStack tokenStack) {
        buildArguments(tokenStack);
        return argument1 - argument2;
    }

    private void buildArguments(TokenStack tokenStack) {
        TokenStack duplicateStack = tokenStack;
        TokenStack invertStack = TokenFactory.createStack();

        for (int i = 0; i < neededArguments; i++) {
            if (duplicateStack.hasMoreElements()) {
                invertStack.push(duplicateStack.pop());
            }

            if (hasSufficientArguments(invertStack)) {
                argument1 = Double.parseDouble((invertStack.size() > 0) ? invertStack.pop() : "0.0");
                argument1 = Double.parseDouble((invertStack.size() > 0) ? invertStack.pop() : "0.0");
            } else {

            }
        }
    }

    private boolean hasSufficientArguments(TokenStack tokenStack) {
        return tokenStack.size() >= 1;
    }
}
