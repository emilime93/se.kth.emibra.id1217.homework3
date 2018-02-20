package hw3.task2;

import java.util.concurrent.Semaphore;

public class BeeStarter {

    public static void main(String[] args) {
        boolean fair = true;
        int numBees = 5;
        Semaphore consumerSem = new Semaphore(0, fair);
        Semaphore producerSem = new Semaphore(1, fair);
        Pot pot = new Pot();

        Bear bear = new Bear(consumerSem, producerSem, pot);
        Honeybee[] beePool = new Honeybee[numBees];
        for (int i = 0; i < beePool.length; i++) {
            beePool[i] = new Honeybee(consumerSem, producerSem, pot);
        }

        Thread bearThread = new Thread(bear);
        Thread[] beeThreads = new Thread[beePool.length];

        bearThread.start();
        for (int i = 0; i < beeThreads.length; i++) {
            beeThreads[i] = new Thread(beePool[i]);
            beeThreads[i].start();
        }
    }

}
