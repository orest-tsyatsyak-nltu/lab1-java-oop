package org.orest.tsiatsiak;

import java.util.LinkedList;
import java.util.List;
import java.util.function.DoubleUnaryOperator;

import static java.lang.Math.cos;
import static java.lang.Math.exp;
import static java.lang.Math.pow;
import static java.lang.Math.sin;
import static java.lang.Math.sqrt;

public class FirstTask {

    private final TabulationParameters firstFunctionParams = new TabulationParameters(0, 1, 0.1);

    private final DoubleUnaryOperator firstTabulationFunction = x -> exp(sin(x) + cos(x));

    private final TabulationParameters secondFunctionParams = new TabulationParameters(0, 10, 1);

    private final DoubleUnaryOperator secondTabulationFunction = x -> {
        if (x < 5) {
            return sqrt(pow(x, 2) + 3);
        } else {
            return sqrt(pow(x, 3) + 2 * pow(x, 2));
        }
    };

    public void run() {
        System.out.println("First function tabulation result:\n");
        doTabulation(firstFunctionParams, firstTabulationFunction)
                .forEach(System.out::println);
        System.out.println("\nSecond function tabulation result:\n");
        doTabulation(secondFunctionParams, secondTabulationFunction)
                .forEach(System.out::println);
    }

    private List<Double> doTabulation(TabulationParameters parameters, DoubleUnaryOperator tabulationFunction) {
        List<Double> result = new LinkedList<>();
        for (double x = parameters.startValue(); x <= parameters.endValue(); x += parameters.step()) {
            result.add(tabulationFunction.applyAsDouble(x));
        }
        return result;
    }

    private record TabulationParameters(double startValue, double endValue, double step) {
    }

}
