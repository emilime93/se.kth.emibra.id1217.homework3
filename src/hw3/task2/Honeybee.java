package hw3.task2;

import java.util.concurrent.Semaphore;

public class Honeybee implements Runnable {

    private static int id = 0;

    private Semaphore consumerSem;
    private Semaphore producerSem;
    private Pot pot;
    private int myID;

    public Honeybee(Semaphore consumerSem, Semaphore producerSem, Pot pot) {
        this.consumerSem = consumerSem;
        this.producerSem = producerSem;
        this.pot = pot;
        myID = id++;
    }

    public int getID() {
        return this.myID;
    }

    @Override
    public void run() {
        while (true) {
            try {
                producerSem.acquire();
                if (!pot.addFood(this)) {
                    consumerSem.release();
                } else {
                    producerSem.release();
                }
                long sleepTime = (long) (Math.random()*1000);
                System.out.printf("Bee #%d sleeping for %.04f seconds\n", myID, sleepTime/1000.0);
                Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
