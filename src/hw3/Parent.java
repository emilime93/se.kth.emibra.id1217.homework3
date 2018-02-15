package hw3;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class Parent implements Runnable {

    private final Semaphore sem;
    private Food food;

    public Parent(Semaphore sem, Food food) {
        this.sem = sem;
    }

    @Override
    public void run() {
        while (true) {
            try {
                sem.acquire();
                while (food.getAmount() > 0) {
                    sem.wait();
                }
//                sem.acquire();
                food.refill();
                sem.notifyAll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                sem.release();
            }
        }
    }
}
