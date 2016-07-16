package com.nari.sungang.legendof24.mathparser.operator.behaviour;

import com.nari.sungang.legendof24.mathparser.operator.OperatorBehaviour;

/**
 * Created by sungang on 2016/1/7.
 */
public final class OperatorBehaviourFactory {
    private OperatorBehaviourFactory() {
    }


    public static OperatorBehaviour createPlusOperatorBehaviour(String symbol, int neededArguments) {
        return new PlusOperatorBehaviour(symbol, neededArguments);
    }

    public static OperatorBehaviour createMinusOperatorBehaviour(String symbol, int neededArguments) {
        return new MinusOperatorBehaviour(symbol, neededArguments);
    }

    public static OperatorBehaviour createMultiplyOperatorBehaviour(String symbol, int neededArguments) {
        return new MultiplyOperatorBehaviour(symbol, neededArguments);
    }

    public static OperatorBehaviour createDivisionOperatorBehaviour(String symbol, int neededArguments) {
        return new DivisionOperatorBehaviour(symbol, neededArguments);
    }


    public static OperatorBehaviour createNoOperatorBehaviour() {
        return new NoOperatorBehaviour();
    }

}
