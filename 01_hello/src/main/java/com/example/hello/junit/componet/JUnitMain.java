package com.example.hello.junit.componet;


public class JUnitMain {
    public static void main(String[] args) {
        System.out.println("hello junit");

        Calculator calculator = new Calculator(new KrwCalculator());

        System.out.println(calculator.sum(10,20));
        System.out.println(calculator.minus(10,20));

        MarketApi marketApi = new MarketApi();
        DollarCalculator dollarCalculator = new DollarCalculator(marketApi);
        dollarCalculator.init();

        Calculator calculator1 = new Calculator(dollarCalculator);

        System.out.println(calculator1.sum(10,20));

    }
}
