package com.nari.sungang.legendof24.mathparser.constants;

import com.nari.sungang.legendof24.mathparser.operator.Operator;
import com.nari.sungang.legendof24.mathparser.operator.OperatorFactory;

/**
 * Created by sungang on 2016/1/8.
 */
public class OperatorConstants {
    public static final String DEFAULT_DECIMAL_SEPARATOR = ",";
    public static final String DEFAULT_JAVA_DECIMAL_SEPARATOR = ".";


    public static final Operator PLUS = OperatorFactory.createPlusOperator();
    public static final Operator MINUS = OperatorFactory.createMinusOperator();
    public static final Operator MULTIPLY = OperatorFactory.createMultiplyOperator();
    public static final Operator DIVISION = OperatorFactory.createDivisionOperator();

    public static final Operator LEFT_PARENTHESES = OperatorFactory.createLeftParentheses();
    public static final Operator RIGHT_PARENTHESES = OperatorFactory.createRightParentheses();

    public static final String[] VALID_OPERATORS_SYMBOLS = {
            PLUS.getSymbol(), MINUS.getSymbol(),
            MULTIPLY.getSymbol(), DIVISION.getSymbol(), LEFT_PARENTHESES.getSymbol(), RIGHT_PARENTHESES.getSymbol(), DEFAULT_DECIMAL_SEPARATOR};

    public static final Operator[] VALID_OPERATORS = {PLUS, MINUS, MULTIPLY, DIVISION, LEFT_PARENTHESES,
            RIGHT_PARENTHESES};

    public static final String[] PARENTHESES_SYMBOLS = {
            LEFT_PARENTHESES.getSymbol(), RIGHT_PARENTHESES.getSymbol()};

    OperatorConstants() {
    }
}
