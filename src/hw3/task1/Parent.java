package hw3.task1;

import java.util.concurrent.Semaphore;

public class Parent implements Runnable {

    private final Semaphore producerSem;
    private final Semaphore consumerSem;
    private Pot food;

    public Parent(Semaphore producerSem, Semaphore consumerSem, Pot food) {
        this.producerSem = producerSem;
        this.consumerSem = consumerSem;
        this.food = food;
    }

    @Override
    public void run() {
        // This will happen indefinitely
        while (true) {
            try {
                producerSem.acquire();
                food.refill();
                consumerSem.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
