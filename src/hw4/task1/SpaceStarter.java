package hw4.task1;

public class SpaceStarter {

    public static void main(String[] args) {
        // Create space station with initial fuel
        SpaceStation spaceStation = new SpaceStation(500, 500, 1000, 700);

        int numberOfSpaceships = 20;
        int numberOfSupplyVehicles = 3;

        // Create a couple of spaceships and initialize them
        // And one supply vehicle
        Spaceship[] spaceship = new Spaceship[numberOfSpaceships];
        for (int i = 0; i < spaceship.length; i++) {
            spaceship[i] = new Spaceship(new CompositeFuel(50, 50), spaceStation);
        }
        SupplyVehicle[] supplyVehicles = new SupplyVehicle[numberOfSupplyVehicles];
        for (int i = 0; i < supplyVehicles.length; i++) {
            supplyVehicles[i] = new SupplyVehicle(new CompositeFuel(50, 50), new CompositeFuel(300, 300), spaceStation);
        }

        // Create Threads for them and start them
        Thread[] spaceshipThreads = new Thread[spaceship.length];
        for (int i = 0; i < spaceshipThreads.length; i++) {
            spaceshipThreads[i] = new Thread(spaceship[i]);
            spaceshipThreads[i].start();
        }

        Thread[] supplyThreads = new Thread[supplyVehicles.length];
        for (int i = 0; i < supplyThreads.length; i++) {
            supplyThreads[i] = new Thread(supplyVehicles[i]);
            supplyThreads[i].start();
        }
    }
}
