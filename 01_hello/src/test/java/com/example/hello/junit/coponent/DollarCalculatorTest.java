package com.example.hello.junit.coponent;

import com.example.hello.junit.componet.Calculator;
import com.example.hello.junit.componet.DollarCalculator;
import com.example.hello.junit.componet.MarketApi;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;


//Mocking 처리 특정한 객체가 호출될떄 원하는 리턴값 호출 가능
//@ExtendWith(MockitoExtension.class) // 순수 자바일 경우
@SpringBootTest
//@Import({MarketApi.class, DollarCalculator.class}) //SpringBootTest 되면 빈이 다올라감
class DollarCalculatorTest {

    @MockBean //Spring은 Bean으로 처리되므로
    private MarketApi marketApi;

//    @Mock //자바일 경우
//    public MarketApi marketApi;

    @Autowired
    private Calculator calculator;

    @Test
    public void dollarCaculatorTest() {
        Mockito.when(marketApi.connect()).thenReturn(3000);

        int sum = calculator.sum(10, 10);
        int minus = calculator.minus(10, 10);
        Assertions.assertEquals(60000, sum);
        Assertions.assertEquals(0, minus);
    }

    @Test
    public void testHello() {
        System.out.println("hello");
    }

    @BeforeEach
    public void init() {
        Mockito.lenient().when(marketApi.connect()).thenReturn(300);
    }

    @Test
    public void dollarTest() {
        MarketApi marketApi = new MarketApi();
        DollarCalculator dollarCalculator = new DollarCalculator(marketApi);
        dollarCalculator.init();

        Calculator calculator1 = new Calculator(dollarCalculator);

        System.out.println(calculator1.sum(10, 20));
        Assertions.assertEquals(33000, calculator1.sum(10, 20));
        Assertions.assertEquals(11000, calculator1.minus(20, 10));
    }

    @Test
    public void mockTest() {
        DollarCalculator dollarCalculator = new DollarCalculator(this.marketApi);
        dollarCalculator.init();

        Calculator calculator1 = new Calculator(dollarCalculator);

        System.out.println(calculator1.sum(10, 20));
        Assertions.assertEquals(9000, calculator1.sum(10, 20));
        Assertions.assertEquals(6000, calculator1.minus(30, 10));
    }
}