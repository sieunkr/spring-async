package com.example.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

@Slf4j
@Service
public class CoffeeService {

    @Async
    public CompletableFuture<Integer> getPriceAsync(String name) {

        try {
            log.info("start getPrice");
            Thread.sleep(3000);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return new AsyncResult<>(1200).completable();
    }
}
