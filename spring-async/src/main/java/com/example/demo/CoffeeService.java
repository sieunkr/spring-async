package com.example.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

@Slf4j
@Service
public class CoffeeService {

    public CompletableFuture<Integer> getPriceAsyncWithoutAnnotation(String name) {

        log.info("(main thread..)start getPrice..." + name);

        return CompletableFuture.supplyAsync(() -> {

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info("(another thread..) end getPrice..." + name);
            return 1200;
        });
    }

    //리턴을 하지 않는 경우
    @Async
    public void order(String name) {

        try {
            log.info("start order..." + name);
            Thread.sleep(3000);
            log.info("end order..." + name);

        } catch (InterruptedException e) {
            log.info(e.getMessage());
        }
    }

    @Async
    public Future<Integer> getPriceAsyncWithFuture(String name) throws InterruptedException {

        log.info("start get... :" + name);

        Thread.sleep(3000);
        return new AsyncResult<>(1700);
    }

    @Async
    public ListenableFuture<Integer> getPriceAsyncWithListenableFuture(String name) throws InterruptedException {

        log.info("start get... :" + name);

        Thread.sleep(3000);
        return new AsyncResult<>(1700);
    }

    @Async
    public CompletableFuture<Integer> getPriceAsyncWithCompletableFuture(String name) {

        try {
            log.info("start getPrice..." + name);
            Thread.sleep(3000);
            log.info("end getPrice..." + name);

        } catch (InterruptedException e) {
            log.info(e.getMessage());
        }

        return new AsyncResult<>(1200).completable();
    }
}