package hw4.task1;

public class SupplyVehicle extends SpaceVehicle implements Runnable {

    private final int REFILL_N_CARGO_AMOUNT;
    private final int REFiLL_Q_CARGO_AMOUNT;

    private CompositeFuel cargoFuel;

    public SupplyVehicle(CompositeFuel initialFuel, CompositeFuel cargoFuel, SpaceStation spaceStation) {
        super(initialFuel, spaceStation);
        REFILL_N_CARGO_AMOUNT = cargoFuel.getAmountN();
        REFiLL_Q_CARGO_AMOUNT = cargoFuel.getAmountQ();
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
                System.out.println("SUPPLYVEHICLE: Refilling my cargo fuel on planet earth\n");
                cargoFuel.setAmountN(REFILL_N_CARGO_AMOUNT);
                cargoFuel.setAmountQ(REFiLL_Q_CARGO_AMOUNT);
                restockSpaceStation();
            }
            refuel();
            try {
                long sleepTime = (long) (Math.random() * 5000) + 5000;
                System.out.printf("zzzz SUPPLYVEHICLE #%d sleeping for %.02f seconds\n\n", getID(), sleepTime/1000.0);
                Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
