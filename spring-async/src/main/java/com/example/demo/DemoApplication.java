package com.example.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@Slf4j
@EnableAsync
@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

    private final CoffeeService coffeeService;

    public DemoApplication(CoffeeService coffeeService) {
        this.coffeeService = coffeeService;
    }

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws ExecutionException, InterruptedException {

        /*
        coffeeService.order("latte");
        log.info("non blocking : ...");
         */

        /*
        CompletableFuture<Integer> future = coffeeService.getPriceAsyncWithoutAnnotation("latte");
        log.info("non blocking 1 : ...");
        future.thenAccept(p -> log.info("latte's price is : " + p));
        log.info("non blocking 2 : ...");

         */

        /*
        Future<Integer> future = coffeeService.getPriceAsyncWithFuture("latte");
        System.out.println("blocking..latte's price is : " + future.get());
        */

        /*
        ListenableFuture<Integer> future = coffeeService.getPriceAsyncWithListenableFuture("latte");
        future.addCallback(s -> log.info("latte's price is :" + s), e-> log.info(e.getMessage()));
        log.info("non blocking");

         */


        CompletableFuture<Integer> future = coffeeService.getPriceAsyncWithCompletableFuture("latte");
        log.info("non blocking : ...");
        log.info("blocking : latte's price is " + future.get());
        log.info("after blocking...");

        /*
        CompletableFuture<Integer> future = coffeeService.getPriceAsyncWithCompletableFuture("latte");
        log.info("non blocking 1 : ...");
        future.thenAccept(p -> log.info("latte's price is : " + p));
        log.info("non blocking 2 : ...");
         */
    }
}