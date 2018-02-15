package hw3;

public class Food {

    private int amount;

    public Food(int amount) {
        this.amount = amount;
    }

    public void eat(BabyBird babyBird) {
        amount--;
        System.out.println();
    }

    public void refill() {
        amount += 7;
        System.out.println("Refilled food, now :" + amount + " food in bowl.");
    }

    public int getAmount() {
        return amount;
    }
}
