import java.util.concurrent.*;

public class Main
{

    public static void main(String[] args) throws InterruptedException {

        BlockingQueue<String> queue = new SynchronousQueue<String>(true);

        Initiator firstPlayer = new Initiator(queue, "Initiator");
        SecondPlayer secondPlayer = new SecondPlayer(queue,"Second");

        Thread th1 = new Thread(firstPlayer);
        th1.start();
        Thread th2 = new Thread(secondPlayer);
        th2.start();

        Thread.sleep(10000);

        th1.stop();
        th2.stop();



    }
}

