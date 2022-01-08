package com.example.hello.junit.componet;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DollarCalculator implements ICalculator {
    private int price = 1;
    private final MarketApi marketApi;

    public void init() {
        this.price = marketApi.connect();
    }

    /*
        public int connect(){
            //naver
            //kakao
            // 에서 환율 계산
            return 1100;
        }*/

    @Override
    public int sum(int x, int y) {
        init();
        x *= price;
        y *= price;
        return x + y;
    }

    @Override
    public int minus(int x, int y) {
        init();
        x *= price;
        y *= price;
        return x - y;
    }
}
