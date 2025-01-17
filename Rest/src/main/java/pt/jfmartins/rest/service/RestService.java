package pt.jfmartins.rest.service;


import model.CalculatorOpsEnum;

public interface RestService {
    double calculator_op(CalculatorOpsEnum op, double a, double b);
}
