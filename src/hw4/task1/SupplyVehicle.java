package hw4.task1;

public class SupplyVehicle extends SpaceVehicle implements Runnable {

    private static final int REFILL_N_CARGO_AMOUNT = 300;
    private static final int REFiLL_Q_CARGO_AMOUNT = 300;

    private CompositeFuel cargoFuel;

    public SupplyVehicle(CompositeFuel initialFuel, CompositeFuel cargoFuel, SpaceStation spaceStation) {
        super(initialFuel, spaceStation);
        this.cargoFuel = cargoFuel;
    }

    public void restockSpaceStation() {
        try {
            this.spaceStation.restockFuel(cargoFuel, this);
        } catch (InterruptedException e) {
            System.out.println("SupplyVehicle got interrupted while resupplying.");
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (true) {
            if (cargoFuel.getAmountN() > 0 || cargoFuel.getAmountQ() > 0) {
                restockSpaceStation();
            } else {
                System.out.println("SUPPLYVEHICLE: Refilling my cargo fuel on planet earth");
                System.out.println();
                cargoFuel.setAmountN(REFILL_N_CARGO_AMOUNT);
                cargoFuel.setAmountQ(REFiLL_Q_CARGO_AMOUNT);
                restockSpaceStation();
            }
            refuel();
            try {
                long sleepTime = (long) (Math.random() * 5000) + 5000;
                System.out.printf("SupplyVehicle #%d sleeping for %.02f seconds\n", getID(), sleepTime/1000.0);
                Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
