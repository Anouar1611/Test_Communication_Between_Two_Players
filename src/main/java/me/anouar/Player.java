package me.anouar;

import java.util.concurrent.BlockingQueue;

class Player
{
    protected String name;
    protected final BlockingQueue<String> queue;
    private int messagesNumber = 0;

    public Player(BlockingQueue<String> queue, String name)
    {
        this.queue = queue;
        this.name=name;
    }


    protected void replyMessage(String message)
    {
        String messageOfReply = message + " " + messagesNumber;
        try
        {
            queue.put(messageOfReply);
            System.out.printf(this.getName() + " player sent : " + messageOfReply + " successfully %n");
            incrementMessagesNumber();

            Thread.sleep(500);

        }
        catch (InterruptedException e)
        {

        }

    }



    protected String receiveMessage()
    {
        String message="";
        try
        {
            message = queue.take();
        }
        catch (InterruptedException e)
        {

        }
        return message;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    protected int getMessagesNumber() {
        return messagesNumber;
    }

    protected void incrementMessagesNumber() {
        messagesNumber++;
    }
}
