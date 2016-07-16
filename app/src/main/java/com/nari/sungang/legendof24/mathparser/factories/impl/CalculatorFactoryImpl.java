package com.nari.sungang.legendof24.mathparser.factories.impl;

import com.nari.sungang.legendof24.mathparser.factories.calculator.CalculatorFactory;
import com.nari.sungang.legendof24.mathparser.parser.calculation.RPNCalculator;
import com.nari.sungang.legendof24.mathparser.parser.calculation.Result;
import com.nari.sungang.legendof24.mathparser.parser.calculation.impl.RPNCalculatorImpl;
import com.nari.sungang.legendof24.mathparser.parser.calculation.impl.ResultImpl;

/**
 * Created by sungang on 2016/1/9.
 */
public class CalculatorFactoryImpl implements CalculatorFactory {
    CalculatorFactoryImpl() {
    }


    @Override
    public Result createResult() {
        return new ResultImpl();
    }

    @Override
    public RPNCalculator createRPNCalculator() {
        return new RPNCalculatorImpl();
    }
}
