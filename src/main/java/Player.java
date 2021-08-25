import java.math.BigInteger;
import java.util.concurrent.BlockingQueue;

class Player implements Runnable
{
    private int number;
    protected final BlockingQueue<String> queue;

    // Please aware that integer field may overflow during prolonged run
    // of the program. So after 2147483647 we'll get -2147483648. We can
    // either use BigInteger or compare the field with Integer.MAX_VALUE
    // before each increment.
    //
    // Let's choose BigInteger for simplicity.
    private int messagesNumber = 0;

    public Player(BlockingQueue<String> queue, int number)
    {
        this.queue = queue;
        this.number=number;
    }

    @Override
    public void run()
    {
        while (getMessagesNumber()<10)
        {
            String receivedMessage = receive();
            reply(receivedMessage);
        }
    }

    protected String receive()
    {
        String receivedMessage;
        try
        {
            // Take message from the queue if available or wait otherwise.
            receivedMessage = queue.take();
        }
        catch (InterruptedException interrupted)
        {
            String error = String.format(
                    "Player [%s] failed to receive message on iteration [%d].",
                    this, messagesNumber);
            throw new IllegalStateException(error, interrupted);
        }
        return receivedMessage;
    }

    protected void reply(String receivedMessage)
    {
        String reply = receivedMessage + " " + messagesNumber;
        try
        {
            // Send message if the queue is not full or wait until one message
            // can fit.
            queue.put(reply);
            System.out.printf("Player " + this.getNumber() + " sent message " + reply + " successfully %n");
            reply="";
            messagesNumber++;

            // All players will work fine without this delay. It placed here just
            // for slowing the console output down.
            Thread.sleep(1500);
        }
        catch (InterruptedException interrupted)
        {
            String error = String.format(
                    "Player [%s] failed to send message [%s] on iteration [%d].",
                    this, reply, messagesNumber);
            throw new IllegalStateException(error);
        }
    }

    protected int getNumber() {
        return number;
    }

    protected void setNumber(int number) {
        this.number = number;
    }

    protected int getMessagesNumber() {
        return messagesNumber;
    }

    protected void incrementMessagesNumber() {
        messagesNumber++;
    }
}
