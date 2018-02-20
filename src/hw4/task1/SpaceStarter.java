package hw4.task1;

public class SpaceStarter {

    public static void main(String[] args) {
        // Create space station with initial fuel
        SpaceStation spaceStation = new SpaceStation(0, 0);

        // Create a couple of spaceships and initialize them
        // And one supply vehicle
        Spaceship[] spaceship = new Spaceship[6];
        for (int i = 0; i < spaceship.length; i++) {
            spaceship[i] = new Spaceship(new CompositeFuel(50, 50), spaceStation);
        }
        SupplyVehicle supplyVehicle = new SupplyVehicle(new CompositeFuel(50, 50), new CompositeFuel(300, 300), spaceStation);

        // Create Threads for them and start them
        Thread[] spaceshipThread = new Thread[spaceship.length];
        for (int i = 0; i < spaceshipThread.length; i++) {
            spaceshipThread[i] = new Thread(spaceship[i]);
            spaceshipThread[i].start();
        }
        Thread supplyVehicleThread = new Thread(supplyVehicle);
        supplyVehicleThread.start();
    }
}
