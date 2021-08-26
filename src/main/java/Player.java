import java.math.BigInteger;
import java.util.concurrent.BlockingQueue;

class Player implements Runnable
{
    private int number;
    protected final BlockingQueue<String> queue;
    protected final String POISON_PILL="POISON";

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
            while (getMessagesNumber() < 10) {
                String receivedMessage = receive();
                if (receivedMessage.equals(POISON_PILL) ) break;
                reply(receivedMessage);
            }

    }

    protected String receive()
    {
        String receivedMessage="";
        try
        {
            receivedMessage = queue.take();
        }
        catch (InterruptedException e)
        {

        }
        return receivedMessage;
    }

    protected void reply(String receivedMessage)
    {
        String reply = receivedMessage + " " + messagesNumber;
        try
        {
            queue.put(reply);
            System.out.printf("Player " + this.getNumber() + " sent message " + reply + " successfully %n");
            messagesNumber++;

            Thread.sleep(500);

        }
        catch (InterruptedException e)
        {

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
