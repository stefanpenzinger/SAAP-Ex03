package at.hagenberg.fh.calculator;

public class Main {
    public static void main(String[] args) {
        CalcController calcController = new CalcController();

        String calculation = "1*3 + 2*4 + 3*5 + 4*6";
        System.out.println("The calculation: " + calculation);
        calcController.setCalculation(calculation);
    }
}
