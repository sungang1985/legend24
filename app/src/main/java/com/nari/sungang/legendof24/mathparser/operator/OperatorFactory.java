package com.nari.sungang.legendof24.mathparser.operator;

import com.nari.sungang.legendof24.mathparser.operator.behaviour.OperatorBehaviourFactory;

/**
 * Created by sungang on 2016/1/7.
 */
public final class OperatorFactory {
    //Basic Math Symbols
    private static final String PLUS = "+";
    private static final String MINUS = "-";
    private static final String MULTIPLY = "*";
    private static final String DIVISION = "/";

    //括号
    private static final String LEFT_PARENTHESES = "(";
    private static final String RIGHT_PARENTHESES = ")";

    //参数
    private static final int ZERO_ARGUMENTS = 0;
    private static final int TWO_ARGUMENTS = 2;

    //优先级
    private static final int PARENTHESES_PRECEDENCE = 1;
    private static final int PLUS_OP_PRECEDENCE = 2;
    private static final int MINUS_OP_PRECEDENCE = 2;
    private static final int MULTIPLY_OP_PRECEDENCE = 3;
    private static final int DIVISION_OP_PRECEDENCE = 3;

    private static String symbol;
    private static int neededArguments;
    private static int precedence;


    private OperatorFactory() {
    }

    private static Operator createOperator(String symbol, int precedence, OperatorAssociativity associativity, int neededArguments, OperatorBehaviour operatorBehaviour) {
        return new Operator(symbol, precedence, associativity, neededArguments, operatorBehaviour);
    }

    public static Operator createPlusOperator() {
        symbol = PLUS;
        neededArguments = TWO_ARGUMENTS;
        precedence = PLUS_OP_PRECEDENCE;
        return OperatorFactory.createOperator(symbol, precedence, OperatorAssociativity.LEFT, neededArguments, OperatorBehaviourFactory.createPlusOperatorBehaviour(symbol, neededArguments));
    }

    public static Operator createMinusOperator() {
        symbol = MINUS;
        neededArguments = TWO_ARGUMENTS;
        precedence = MINUS_OP_PRECEDENCE;
        return OperatorFactory.createOperator(symbol, precedence, OperatorAssociativity.LEFT, neededArguments, OperatorBehaviourFactory.createMinusOperatorBehaviour(symbol, neededArguments));
    }

    public static Operator createMultiplyOperator() {
        symbol = MULTIPLY;
        neededArguments = TWO_ARGUMENTS;
        precedence = MULTIPLY_OP_PRECEDENCE;
        return OperatorFactory.createOperator(symbol, precedence, OperatorAssociativity.LEFT, neededArguments, OperatorBehaviourFactory.createMultiplyOperatorBehaviour(symbol, neededArguments));
    }

    public static Operator createDivisionOperator() {
        symbol = DIVISION;
        neededArguments = TWO_ARGUMENTS;
        precedence = DIVISION_OP_PRECEDENCE;
        return OperatorFactory.createOperator(symbol, precedence, OperatorAssociativity.LEFT, neededArguments, OperatorBehaviourFactory.createDivisionOperatorBehaviour(symbol, neededArguments));
    }

    public static Operator createLeftParentheses() {
        symbol = LEFT_PARENTHESES;
        neededArguments = ZERO_ARGUMENTS;
        precedence = PARENTHESES_PRECEDENCE;
        return OperatorFactory.createOperator(symbol, precedence, OperatorAssociativity.LEFT, neededArguments, OperatorBehaviourFactory.createNoOperatorBehaviour());
    }

    public static Operator createRightParentheses() {
        symbol = RIGHT_PARENTHESES;
        neededArguments = ZERO_ARGUMENTS;
        precedence = PARENTHESES_PRECEDENCE;
        return OperatorFactory.createOperator(symbol, neededArguments, OperatorAssociativity.LEFT, precedence, OperatorBehaviourFactory.createNoOperatorBehaviour());
    }

}
