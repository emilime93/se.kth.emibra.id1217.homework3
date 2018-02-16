package hw3.task2;

public class Pot {

    public static final int MAXIMUM_CAPACITY = 10;
    private int amountOfFood = 0;

    public boolean addFood(Honeybee bee) {
        if (amountOfFood == MAXIMUM_CAPACITY) {
            System.out.println("Tried to bring more honey, but the pot was full!");
            return false;
        }
        System.out.printf("Bee #%d brought in one piece of honey, making it %.02f%% full.\n", bee.getID(), (double)amountOfFood/MAXIMUM_CAPACITY);
        amountOfFood++;
        return true;
    }

    public void eatAll() {
        System.out.println("NOM NOM NOM, bear ate all hard gathered honey!");
        amountOfFood = 0;
    }
}
