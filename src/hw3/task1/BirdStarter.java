package hw3.task1;

import java.util.concurrent.Semaphore;

public class BirdStarter {

    public static void main(String[] args) {
        boolean fair = true;
        int numBabyBirds = 5;
        int amountOfFood = Pot.REFILL_AMOUNT;
        Semaphore producerSem = new Semaphore(0, fair);
        Semaphore consumerSem = new Semaphore(1, fair);
        Pot food = new Pot(amountOfFood);

        // Create the Bird parent and its children
        Parent parent = new Parent(producerSem, consumerSem, food);
        BabyBird[] babyBirds = new BabyBird[numBabyBirds];
        for (int i = 0; i < babyBirds.length; i++) {
            babyBirds[i] = new BabyBird(producerSem, consumerSem, food);
        }

        // Create threads for the birds to run on
        Thread parentThread = new Thread(parent);
        Thread[] babyThreads = new Thread[numBabyBirds];

        // Start the threads with the birds
        for (int i = 0; i < babyThreads.length; i++) {
            babyThreads[i] = new Thread(babyBirds[i]);
            babyThreads[i].start();
        }
        parentThread.start();
    }
}
