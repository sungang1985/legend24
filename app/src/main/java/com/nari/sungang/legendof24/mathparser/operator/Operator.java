package com.nari.sungang.legendof24.mathparser.operator;


/**
 * Created by sungang on 2016/1/7.
 */
public class Operator implements Comparable<Operator> {
    private String symbol;
    private int precedence;
    private OperatorAssociativity operatorAssociativity;
    private int neededArguments;
    private OperatorBehaviour operatorBehaviour;

    public Operator(String symbol, int precedence, OperatorAssociativity operatorAssociativity, int neededArguments, OperatorBehaviour operatorBehaviour) {
        this.symbol = symbol;
        this.precedence = precedence;
        this.operatorAssociativity = operatorAssociativity;
        this.neededArguments = neededArguments;
        this.operatorBehaviour = operatorBehaviour;
    }

    public int getNeededArguments() {
        return neededArguments;
    }

    public OperatorAssociativity getOperatorAssociativity() {
        return operatorAssociativity;
    }

    public OperatorBehaviour getOperatorBehaviour() {
        return operatorBehaviour;
    }

    public int getPrecedence() {
        return precedence;
    }

    public String getSymbol() {
        return symbol;
    }

    @Override
    public int compareTo(Operator o) {
        if (this.precedence > o.precedence) {
            return 1;
        } else if (this.precedence < o.precedence) {
            return -1;
        } else {
            return 0;
        }
    }
}
