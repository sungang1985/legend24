package com.nari.sungang.legendof24.mathparser.operator.utils;

import com.nari.sungang.legendof24.mathparser.constants.OperatorConstants;
import com.nari.sungang.legendof24.mathparser.operator.Operator;
import com.nari.sungang.legendof24.mathparser.operator.OperatorAssociativity;

import java.util.Arrays;

/**
 * Created by sungang on 2016/1/8.
 */
public class OperatorUtils {

    private OperatorUtils() {
    }

    public static Operator getOperator(String operator) {
        try {
            int index = Arrays.asList(OperatorConstants.VALID_OPERATORS_SYMBOLS).indexOf(operator);
            return OperatorConstants.VALID_OPERATORS[index];
        } catch (IndexOutOfBoundsException e) {
            //Factories.getExceptionFactoryInstance().launchInvalidOperatorException();
            return null;
        }
    }

    public static boolean isAnValidSymbol(String candidate) {
        return Arrays.asList(OperatorConstants.VALID_OPERATORS_SYMBOLS).indexOf(candidate) != -1;
    }

    public static boolean isAParentheses(String candidate) {
        return Arrays.asList(OperatorConstants.PARENTHESES_SYMBOLS).indexOf(candidate) != -1;
    }

    public static boolean isAnOperator(String candidate) {
        return Arrays.asList(OperatorConstants.VALID_OPERATORS_SYMBOLS).indexOf(candidate) != -1;
    }

    public static boolean isLeftAssociativity(String operator) {
        return OperatorUtils.getOperator(operator).getOperatorAssociativity() == OperatorAssociativity.LEFT;
    }

    public static boolean isRightAssociativity(String operator) {
        return OperatorUtils.getOperator(operator).getOperatorAssociativity() == OperatorAssociativity.RIGHT;
    }

    public static int comparePrecedence(String operator1, String operator2) {
        Operator operatorObj1 = OperatorUtils.getOperator(operator1);
        Operator operatorObj2 = OperatorUtils.getOperator(operator2);

        return operatorObj1.compareTo(operatorObj2);
    }

    public static boolean isNumber(String candidate) {
        try {
            Integer.parseInt(candidate);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
