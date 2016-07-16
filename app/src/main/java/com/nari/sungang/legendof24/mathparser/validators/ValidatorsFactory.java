package com.nari.sungang.legendof24.mathparser.validators;

/**
 * Created by sungang on 2016/1/9.
 */
public final class ValidatorsFactory {
    private ValidatorsFactory() {
    }

    public static Validator createExpressionValidator() {
        return new ExpressionValidator();
    }
}
