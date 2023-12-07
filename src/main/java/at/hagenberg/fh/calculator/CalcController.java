package at.hagenberg.fh.calculator;

import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;

public class CalcController {
    private String calculation = "";
    private ExecutorService executorService;

    private LinkedBlockingQueue<Future<Float>> queue = new LinkedBlockingQueue<>();

    public void setCalculation(String calculation) {
        this.calculation = calculation;
        divideWork();
    }

    private void divideWork() {
        if (calculation.isBlank()) {
            System.out.println("Nothing to calculate :(");
        }

        var splits = calculation.split("\\+");
        executorService = Executors.newFixedThreadPool(splits.length);
        callWorkers(splits);
    }

    private void callWorkers(String[] calcs) {
        for (String calc:calcs) {
            queue.add(executorService.submit(new CalcWorker(calc)));
        }

        executorService.shutdown();
        combineResults();
    }

    private void combineResults() {
        float finalSum = 0f;

        while(queue.peek() != null) {
            if (queue.peek().isDone()) {
                finalSum += Objects.requireNonNull(queue.poll()).resultNow();
            }
        }

        System.out.println("The result of the final calculatin is: " + finalSum);
    }
}
