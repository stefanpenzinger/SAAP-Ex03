package at.hagenberg.fh.printer;

import java.util.Objects;
import java.util.concurrent.*;


public class PrinterController implements Runnable {
    private final ExecutorService printService;
    private final LinkedBlockingQueue<Future<String>> queue = new LinkedBlockingQueue<>();

    public PrinterController() {
        int numberOfPrinters = 3;
        this.printService = Executors.newFixedThreadPool(numberOfPrinters);
    }

    public void addPrintJob(String input) {
        Future<String> message = printService.submit(new PrinterWorker(input));
        queue.add(message);
    }

    @Override
    public void run() {
        while (true) {
            try {
                iterateRecursivly();
                Thread.sleep(1000);
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void iterateRecursivly() throws ExecutionException, InterruptedException {
        if (queue.peek() == null) {
            return;
        }

        if (queue.peek().isDone()) {
            System.out.println(Objects.requireNonNull(queue.poll()).get());
            iterateRecursivly();
        }
    }
}
