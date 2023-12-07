package at.hagenberg.fh.printer;

import java.util.Scanner;
import java.util.concurrent.ExecutionException;

public class Main {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        PrinterController printerController = new PrinterController();
        Thread thread = new Thread(printerController);
        thread.start();

        var input = "";
        Scanner sc = new Scanner(System.in);

        while (!input.equals("0")) {
            System.out.print("Add a print job: ");
            input = sc.next();

            printerController.addPrintJob(input);
        }
    }
}
