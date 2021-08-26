import java.util.concurrent.*;

public class Main
{
    // Blocking queue looks superfluous for single message. But such a queue saves us from cumbersome
    // synchronization of the threads.

    public static void main(String[] args)
    {
        ExecutorService executor = Executors.newFixedThreadPool(2);
        BlockingQueue<String> queue = new SynchronousQueue<String>(true);

        Initiator firstPlayer = new Initiator(queue, 1);
        Player secondPlayer = new Player(queue,2);

        executor.execute(firstPlayer);
        executor.execute(secondPlayer);

        executor.shutdown();


    }
}

