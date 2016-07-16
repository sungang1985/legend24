package com.nari.sungang.legendof24.mathparser.factories.calculator;

import com.nari.sungang.legendof24.mathparser.parser.calculation.RPNCalculator;
import com.nari.sungang.legendof24.mathparser.parser.calculation.Result;

/**
 * Created by sungang on 2016/1/9.
 */
public interface CalculatorFactory {
    public Result createResult();

    public RPNCalculator createRPNCalculator();
}
