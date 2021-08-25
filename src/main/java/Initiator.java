import java.util.concurrent.BlockingQueue;

class Initiator extends Player {

    private static final String INIT_MESSAGE = "initiator";

    public Initiator(BlockingQueue<String> sent, int number) {
        super(sent, number);
    }

    @Override
    public void run() {
        sendInitMessage();
        while (getMessagesNumber()<10) {
            String receivedMessage = receive();
            reply(receivedMessage);
        }
    }

    private void sendInitMessage() {
        String InitMessage= INIT_MESSAGE + " " + getMessagesNumber();
        try {
            queue.put(InitMessage);
            System.out.printf("Player " + this.getNumber() + " sent message " + InitMessage + " successfully %n");
            incrementMessagesNumber();
        } catch (InterruptedException interrupted) {
            String error = String.format(
                    "Player [%s] failed to sent message [%s].",
                    this, InitMessage);
            throw new IllegalStateException(error, interrupted);
        }
    }

}