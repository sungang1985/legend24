package com.nari.sungang.legendof24.mathparser.constants.utils;

/**
 * Created by sungang on 2016/1/8.
 */
public final class MathParserUtils {
    private MathParserUtils() {
    }

    public static String transformGropCharsIntoParentheses(String expression) {
        String expressionToReturn = expression;
        expressionToReturn = expressionToReturn.replaceAll("\\[", "(");
        expressionToReturn = expressionToReturn.replaceAll("]", ")");
        expressionToReturn = expressionToReturn.replaceAll("\\{", "(");
        expressionToReturn = expressionToReturn.replaceAll("}", ")");
        return expressionToReturn;
    }
}

