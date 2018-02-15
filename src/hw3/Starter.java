package hw3;

import java.util.concurrent.Semaphore;

public class Starter {

    public static void main(String[] args) {
        int numBirdlings = 5;
        int amountOfFood = 7;
        Semaphore sem = new Semaphore(1);
        Food food = new Food(amountOfFood);

        // Create the Bird parent and its children
        Parent parent = new Parent(sem, food);
        BabyBird[] babyBirds = new BabyBird[numBirdlings];
        for (int i = 0; i < babyBirds.length; i++) {
            babyBirds[i] = new BabyBird(sem, food);
        }

        // Create threads for the birds to run on
        Thread parentThread = new Thread(parent);
        Thread[] birdlingThreads = new Thread[numBirdlings];

        // Start the threads with the birds
        for (int i = 0; i < birdlingThreads.length; i++) {
            birdlingThreads[i] = new Thread(babyBirds[i]);
            birdlingThreads[i].start();
        }
        parentThread.start();
    }
}
