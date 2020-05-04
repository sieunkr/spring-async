package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

    @Autowired
    private Executor threadPoolTaskExecutor;

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        List<CompletableFuture<String>> completableFutures = new ArrayList<>();

        IntStream.range(0, 100).forEach(n -> completableFutures.add(
                CompletableFuture.supplyAsync(
                        () -> task(String.valueOf(n)),
                        threadPoolTaskExecutor
                )
        ));

        CompletableFuture.allOf(completableFutures.toArray(new CompletableFuture[completableFutures.size()]))
                .thenAccept(consumer -> completableFutures.forEach(c -> System.out.println(c.join())));
    }

    private static String task(String s) {

        try {
            System.out.println(Thread.currentThread().getName() + ":task started");

            Thread.sleep(5000);
            return "return coffee:" + s;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }
}
