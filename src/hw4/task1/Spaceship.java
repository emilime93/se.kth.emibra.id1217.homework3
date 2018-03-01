package hw4.task1;

public class Spaceship extends SpaceVehicle implements Runnable {
    public Spaceship(CompositeFuel initialFuel, SpaceStation spaceStation) {
        super(initialFuel, spaceStation);
        this.spaceStation = spaceStation;
    }

    @Override
    public void run() {
        while (true) {

            refuel();

            try {
                // Sleeping for 5-10 seconds
                long sleepTime = (long) (Math.random() * 5000) + 5000;
                System.out.printf("zzzz Spaceship #%d sleeping for %.02f seconds\n\n", getID(), sleepTime/1000.0);
                Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
