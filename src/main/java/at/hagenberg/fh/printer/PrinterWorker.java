package at.hagenberg.fh.printer;

import java.util.concurrent.Callable;

public class PrinterWorker implements Callable<String> {
    private final String toPrint;

    public PrinterWorker(String toPrint) {
        this.toPrint = toPrint;
    }

    private void processCommand() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String call() throws Exception {
        processCommand();
        return Thread.currentThread().getName() + " is done printing: " + toPrint;
    }
}
