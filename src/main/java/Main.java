import javax.naming.InitialContext;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import javax.naming.*;
import javax.jms.*;

import java.lang.IllegalStateException;
import java.math.BigInteger;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Main
{
    // Blocking queue looks superfluous for single message. But such a queue saves us from cumbersome
    // synchronization of the threads.
    private static final int MAX_MESSAGES_IN_QUEUE = 1;

    public static void main(String[] args)
    {
        BlockingQueue<String> queue = new LinkedBlockingQueue<String>(MAX_MESSAGES_IN_QUEUE);

        // Both players use the same queues symmetrically.
        Initiator firstPlayer = new Initiator(queue, 1);
        Player secondPlayer = new Player(queue, 2);

        // Please note that we can start threads in reverse order. But thankfully to
        // blocking queues the second player will wait for initialization message from
        // the first player.

        new Thread(firstPlayer).start();
        new Thread(secondPlayer).start();
    }
}

