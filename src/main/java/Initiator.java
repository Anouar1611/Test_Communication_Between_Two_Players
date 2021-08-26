import java.util.concurrent.BlockingQueue;

class Initiator extends Player {

    private static final String INIT_MESSAGE = "initiator";


    public Initiator(BlockingQueue<String> sent, int number) {
        super(sent, number);
    }

    @Override
    public void run() {
        sendInitMessage();

        while (getMessagesNumber()<11) {
            String receivedMessage = receive();
            reply(receivedMessage);
        }
        try {
            queue.put(POISON_PILL);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private void sendInitMessage() {
//        String InitMessage= INIT_MESSAGE;

        try {
                queue.put(INIT_MESSAGE);
                System.out.printf("Player " + this.getNumber() + " sent message " + INIT_MESSAGE + " successfully %n");
                incrementMessagesNumber();

        } catch (InterruptedException e) {

        }

    }

}