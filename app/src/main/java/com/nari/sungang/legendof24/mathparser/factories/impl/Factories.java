package com.nari.sungang.legendof24.mathparser.factories.impl;

import com.nari.sungang.legendof24.mathparser.factories.calculator.CalculatorFactory;
import com.nari.sungang.legendof24.mathparser.factories.parser.ParserFactory;

/**
 * Created by sungang on 2016/1/9.
 */
public final class Factories {
    private static ParserFactory parserFactoryInstance;
    private static CalculatorFactory calculatorFactoryInstance;

    static {
        parserFactoryInstance = new ParserFactoryImpl();
        calculatorFactoryInstance = new CalculatorFactoryImpl();
    }

    private Factories() {
    }

    public static ParserFactory getParserFactoryInstance() {
        return parserFactoryInstance;
    }

    public static CalculatorFactory getCalculatorFactoryInstance() {
        return calculatorFactoryInstance;
    }
}