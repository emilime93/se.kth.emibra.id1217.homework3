package hw3.task1;

import java.util.concurrent.Semaphore;

public class BabyBird implements Runnable {

    private static int id;

    private final Semaphore producerSem;
    private final Semaphore consumerSem;
    private Food food;
    private int myID;

    public BabyBird(Semaphore producerSem, Semaphore consumerSem, Food food) {
        this.producerSem = producerSem;
        this.consumerSem = consumerSem;
        this.food = food;
        myID = id++;
    }

    public int getID() {
        return this.myID;
    }

    @Override
    public void run() {
        // This will happen indefinitely
        while (true) {
            try {
                consumerSem.acquire();
                if (!food.eat(this)) {
                    System.out.printf("Bird #%d tried to eat but there was no worms. CHIRP!\n", myID);
                    producerSem.release();
                } else {
                    consumerSem.release();
                }
                long sleepTime = (long) (Math.random() * 7000);
                System.out.printf("Baby #%d sleeping for %f seconds\n", myID, sleepTime/1000.0);
                Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
