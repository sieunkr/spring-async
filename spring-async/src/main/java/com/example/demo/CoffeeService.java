package com.example.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
public class CoffeeService {

    @Async
    public CompletableFuture<Integer> getPriceAsync(String name) {

        try {
            log.info("start getPrice..." + name);
            Thread.sleep(3000);

        } catch (InterruptedException e) {
            log.info(e.getMessage());
        }

        return new AsyncResult<>(1200).completable();
    }

    //리턴을 하지 않는 경우
    @Async
    public void order(String name) {

        try {
            log.info("start order..." + name);
            Thread.sleep(3000);

        } catch (InterruptedException e) {
            log.info(e.getMessage());
        }
    }
}
