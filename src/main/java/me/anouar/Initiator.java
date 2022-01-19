package me.anouar;

import java.util.concurrent.BlockingQueue;

class Initiator extends Player implements Runnable{

    private static final String FIRST_MESSAGE = "Hello";


    public Initiator(BlockingQueue<String> queue, String name) {
        super(queue, name);
    }

    @Override
    public void run() {
        firstMessage();

        while (getMessagesNumber()<11) {
            String message = receiveMessage();
            replyMessage(message);
        }
    }

    private void firstMessage() {

        try {
                queue.put(FIRST_MESSAGE);
                System.out.printf(this.getName() + " player sent : " + FIRST_MESSAGE + " successfully %n");
                incrementMessagesNumber();

        } catch (InterruptedException e) {

        }

    }

}