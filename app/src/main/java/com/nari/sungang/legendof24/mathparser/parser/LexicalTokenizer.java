package com.nari.sungang.legendof24.mathparser.parser;

import com.nari.sungang.legendof24.mathparser.constants.OperatorConstants;
import com.nari.sungang.legendof24.mathparser.constants.utils.MathParserUtils;
import com.nari.sungang.legendof24.mathparser.token.TokenFactory;
import com.nari.sungang.legendof24.mathparser.token.TokenQueue;
import com.nari.sungang.legendof24.mathparser.validators.Validator;
import com.nari.sungang.legendof24.mathparser.validators.ValidatorsFactory;

/**
 * Created by sungang on 2016/1/8.
 */
public class LexicalTokenizer {
    private Validator expressionValidator = ValidatorsFactory.createExpressionValidator();
    private TokenQueue tokens;

    private String expression;
    private int lengthExpression;

    private int pointer;

    private StringBuffer queue;
    private String actualChar;

    public void setExpression(String expression) {
        cleanOldAttributes();
        assignNewExpression(expression);
    }

    public TokenQueue getTokens() {
        splitExpressionInTokens();
        return tokens;
    }

    private void splitExpressionInTokens() {
        cleanQueue();
        while (hasNextCaracter()) {
            obtainNextChar();
            saveTokenOrPushQueueForNextNumber();
            updatePointer();
        }
    }

    private void saveTokenOrPushQueueForNextNumber() {
        if (actualCharIsNumber()) {
            pushActualCharInQueue();
        } else if (actualCharIsDecimalSeparator()) {

        }
    }

    private boolean actualCharIsDecimalSeparator() {
        return actualChar.equalsIgnoreCase(OperatorConstants.DEFAULT_DECIMAL_SEPARATOR);
    }

    private void pushActualCharInQueue() {
        queue.append(actualChar);
    }

    private boolean actualCharIsNumber() {
        try {
            Integer.parseInt(actualChar);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private void updatePointer() {
        pointer++;
    }

    private void obtainNextChar() {
        actualChar = String.valueOf(expression.charAt(pointer));
    }

    private void cleanQueue() {
        queue = new StringBuffer();
    }

    private boolean hasNextCaracter() {
        return pointer < lengthExpression;
    }

    private void assignNewExpression(String expression) {
        this.expression = expression;
        validateExpression();
        trimExpression();
        transformGropCharsIntoParentheses();
        addConversorsForPositiveModificators();
        addConversorsForNegativeModificators();
        removePositiveOperatorsExcedent();
        this.lengthExpression = this.expression.length();
    }

    private void removePositiveOperatorsExcedent() {
        this.expression = this.expression.replace("(+(", "((");
    }

    private void addConversorsForNegativeModificators() {
        this.expression = this.expression.replace("-", "+(0-1)*");
    }

    private void addConversorsForPositiveModificators() {

    }

    private void transformGropCharsIntoParentheses() {
        this.expression = MathParserUtils.transformGropCharsIntoParentheses(this.expression);
    }

    private void trimExpression() {
        this.expression = this.expression.trim().replace(" ", "");
    }

    private void validateExpression() {
        expressionValidator.validate(this.expression);
    }

    private void cleanOldAttributes() {
        tokens = TokenFactory.createQueue();
        pointer = 0;
        queue = null;
        actualChar = null;
    }

}
