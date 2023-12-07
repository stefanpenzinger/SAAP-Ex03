package at.hagenberg.fh.calculator;

import java.util.concurrent.Callable;

public class CalcWorker implements Callable<Float> {
    private final String calculation;

    public CalcWorker(String calculation) {
        this.calculation = calculation;
    }

    @Override
    public Float call() throws Exception {
        var calcParts = splitCalc();
        float sum = 1f;

        for (String number:calcParts) {
            sum *= Float.parseFloat(number);
        }

        System.out.println("The result of " + Thread.currentThread().getName() + " calculation is: " + sum);
        return sum;
    }

    private String[] splitCalc() {
        return calculation.split("\\*");
    }
}
