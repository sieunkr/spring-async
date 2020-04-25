package com.example.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.concurrent.CompletableFuture;
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
    public void run(String... args) throws Exception {

        CompletableFuture<Integer> future = coffeeService.getPriceAsync("latte");

        log.info("non blocking : ...");

        log.info("blocking : latte's price is " + future.get());

    }
}
