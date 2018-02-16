package hw3.task2;

import java.util.concurrent.Semaphore;

public class Bear implements Runnable {

    private Semaphore consumerSem;
    private Semaphore producerSem;
    private Pot pot;

    public Bear(Semaphore consumerSem, Semaphore producerSem, Pot pot) {
        this.consumerSem = consumerSem;
        this.producerSem = producerSem;
        this.pot = pot;
    }

    @Override
    public void run() {
        while (true) {
            try {
                consumerSem.acquire();
                pot.eatAll();
                producerSem.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
