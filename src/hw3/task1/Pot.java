package hw3.task1;

public class Pot {

    public final static int REFILL_AMOUNT = 5;

    private int amount;

    public Pot(int amount) {
        this.amount = amount;
    }

    public boolean eat(BabyBird babyBird) {
        if (amount <= 0) {
            return false;
        }
        amount--;
        System.out.println("Baby #" + babyBird.getID() + " ate. " + amount + " worms left.");
        return true;
    }

    public void refill() {
        amount += REFILL_AMOUNT;
        System.out.println("Refilled food, now " + amount + " worms in nest.");
    }
}
