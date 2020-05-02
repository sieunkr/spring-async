import java.io.IOException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

public class Main {

    private static final int THREAD_POOL_SIZE = 100;

    private static final Executor executor =
            Executors.newFixedThreadPool(THREAD_POOL_SIZE);

    public static void main(String[] args) throws IOException {

        /*
        IntStream.range(0, 100).forEach(n -> {

                    String threadName = Thread.currentThread().getName();
                    System.out.println(threadName + ":task start" + n);
                    task();
                    System.out.println(threadName + ":task completed" + n);

                }
        );

         */


        IntStream.range(0, 100).forEach(n -> executor.execute(() ->
                {
                    String threadName = Thread.currentThread().getName();
                    System.out.println(threadName + ":task start" + n);
                    task();
                    System.out.println(threadName + ":task completed" + n);
                })
        );
    }


    private static void task() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
