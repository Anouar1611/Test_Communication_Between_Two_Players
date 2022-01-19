## The project is a Java application which contains a simple communication between two players.

There are 3 main classes I implemented:

* ***Player*** : This class has 3 fields name, queue and number of messages, as well as **receiveMessage** method which is responsible to take the message from the queue and return it, and the **replyMessage** method put the message in the queue and display the message of the successful sending as well as increment the number of messages of this player.  

* ***Initiator*** : Is a subclass of me.anouar.Player representing the first player and implements **Runnable** interface in order to create a Thread regarding me.anouar.Initiator class is extending from ***me.anouar.Player*** class, so then we cannot extend other class like ***Thread*** for example. This class has additional method **firstMessage** which send the very first message. 

* ***SecondPlayer*** : Is a subclass also of me.anouar.Player representing teh second player and implements **Runnable**.

Now, let's have a look for the ***main*** class:

* In the main class, I created a **SychronousQueue** which is a pure Java API simply used to have an exchange between threads with a high level of what we call ***Thread Safety***.
In general, SynchronousQueue uses two main methods: ***put()*** to put an element and ***take()*** to extract an element from the queue, and both are blocking which means when an operation is executing, it blocks until another thread for the other operation gives the signal that it is ready.

* ***BlockingQueue*** interface is very useful when we have the concurrence between **Consumer-Producer** threads in our example.

* Use of ***Thread*** class to run the two instances in several threads and then stop them when finishing.

Finally, to run the program, you have:

1- First, to run the command line: ``` chmod +x ./script.sh``` to have the accesses to the shell script.


2- Second, go to the ***src/main/java*** directory and run the command line ``` ./script.sh ```.

> ***Note***: I used Linux environment to run the shell script. 