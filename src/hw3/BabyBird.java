package hw3;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class BabyBird implements Runnable {

    private static int id;

    private final Semaphore sem;
    private Food food;
    private int myId;

    public BabyBird(Semaphore sem, Food food) {
        this.sem = sem;
        this.food = food;
        myId = id++;
    }

    @Override
    public void run() {
        // This will happen indefinitley
        while (true) {
            try {
                sem.acquire();
                while (food.getAmount() <= 0) {
                    sem.wait();
                }
//                sem.acquire();
                food.eat(this);
                if (food.getAmount() <= 0)
                    sem.notifyAll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                sem.release();
                try {
                    Thread.sleep((long) (Math.random()*1000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
