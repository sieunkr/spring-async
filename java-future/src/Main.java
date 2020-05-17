import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

public class Main {

    private static final int THREAD_POOL_SIZE = 100;

    private static final Executor executor =
            Executors.newFixedThreadPool(THREAD_POOL_SIZE);

    public static void main(String[] args) {

        List<CompletableFuture<String>> completableFutures = new ArrayList<>();

        IntStream.range(0, 100).forEach(n -> completableFutures.add(
                CompletableFuture.supplyAsync(
                        () -> task(String.valueOf(n)),
                        executor
                )
        ));

        CompletableFuture.allOf(completableFutures.toArray(new CompletableFuture[completableFutures.size()]))
                .thenAccept(consumer -> {
                    completableFutures.forEach(c -> {
                        if (c.join().equals("return coffee:99")) {
                            System.out.println("last task...");
                        }
                    });
                    System.out.println("completed");
                });

        System.out.println("non-blocking");
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
