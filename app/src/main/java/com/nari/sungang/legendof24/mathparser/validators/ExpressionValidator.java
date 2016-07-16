package com.nari.sungang.legendof24.mathparser.validators;

import com.nari.sungang.legendof24.mathparser.constants.OperatorConstants;
import com.nari.sungang.legendof24.mathparser.constants.utils.MathParserUtils;
import com.nari.sungang.legendof24.mathparser.operator.Operator;
import com.nari.sungang.legendof24.mathparser.operator.utils.OperatorUtils;

/**
 * Created by sungang on 2016/1/8.
 */
public class ExpressionValidator extends Validator {
    private Object expressionObject;
    private String expression;

    @Override
    public void validate(Object value) {
        expressionObject = value;
        evalIfExpressionIsNullOrEmpty();
        evalIfExpressionIsWellFormed();
    }

    private void evalIfExpressionIsWellFormed() {
        expression = expressionObject.toString().trim();
        expression = MathParserUtils.transformGropCharsIntoParentheses(expression);
        evalIfExpressionStartIsCorrect();
        evalIfExpressionEndIsCorrect();
    }

    private void evalIfExpressionEndIsCorrect() {
        String expressionChar = findFirstExpressionCharThatIsNotARightParenthesesFromTheEnding();
        if (OperatorUtils.isAnOperator(expressionChar)) {
            Operator operator = OperatorUtils.getOperator(expressionChar);
            if (isPlusOperator(operator) || isMinusOperator(operator) || isMultiplyOperator(operator) || isDivisionOperator(operator)) {
                //TODO
            }
        }
    }

    private String findFirstExpressionCharThatIsNotARightParenthesesFromTheEnding() {
        char[] expressionArray = expression.toCharArray();
        int pointer = expressionArray.length - 1;
        String expressionChar = "";
        do {
            expressionChar = String.valueOf(expressionArray[pointer]);
            pointer -= 1;
        } while (pointer >= 0 && (expressionChar.equals("") || expressionChar.equals(")")));

        if (pointer < 0) {
            expressionChar = "";
        }
        return expressionChar;
    }

    private void evalIfExpressionStartIsCorrect() {
        String firstChar = findFirstExpressionCharThatIsNotALeftParenthesesFromTheStart();
        if (OperatorUtils.isAnOperator(firstChar)) {
            Operator operator = OperatorUtils.getOperator(firstChar);
            if (isMultiplyOperator(operator) || isDivisionOperator(operator)) {
                //TODO
            }
        }
    }

    private String findFirstExpressionCharThatIsNotALeftParenthesesFromTheStart() {
        char[] expressionArray = expression.toCharArray();
        int pointer = 0;
        String expressionChar = "";

        do {
            expressionChar = String.valueOf(expressionArray[pointer]);
            pointer++;
        }
        while (pointer < expressionArray.length && (expressionChar.equals("") || expressionChar.equals("(")));

        if (pointer == expressionArray.length) {
            expressionChar = "";
        }

        return expressionChar;
    }

    private void evalIfExpressionIsNullOrEmpty() {
        if (expressionObject == null || expressionObject.toString().length() == 0) {

        }
    }

    private boolean isPlusOperator(Operator operator) {
        return OperatorConstants.PLUS.equals(operator);
    }

    private boolean isMinusOperator(Operator operator) {
        return OperatorConstants.MINUS.equals(operator);
    }

    private boolean isMultiplyOperator(Operator operator) {
        return OperatorConstants.MULTIPLY.equals(operator);
    }


    private boolean isDivisionOperator(Operator operator) {
        return OperatorConstants.DIVISION.equals(operator);
    }
}
