import java.util.concurrent.BlockingQueue;

public class SecondPlayer extends Player implements Runnable{

    public SecondPlayer(BlockingQueue<String> queue, String name) {
        super(queue, name);
    }

    @Override
    public void run()
    {
        while (getMessagesNumber() < 10) {
            String message = receiveMessage();
            replyMessage(message);
        }
    }
}
