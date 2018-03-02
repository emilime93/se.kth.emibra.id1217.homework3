package hw4.task1;

public class SpaceStation {

    private final int MAX_NITROGEN_CAPACITY;
    private final int MAX_QUANTUM_CAPACITY;
    private static final int MAX_CONCURRENT_VEHICLES = 3;

    private int currentVisitors = 0;

    private volatile int currentNAmount;
    private volatile int currentQAmount;

    public SpaceStation(int currentNAmount, int currentQAmount, int maxNAmount, int maxQAmount) {
        MAX_NITROGEN_CAPACITY = maxNAmount;
        MAX_QUANTUM_CAPACITY = maxQAmount;
        this.currentNAmount = (currentNAmount > MAX_NITROGEN_CAPACITY) ? MAX_NITROGEN_CAPACITY : currentNAmount;
        this.currentQAmount = (currentQAmount > MAX_QUANTUM_CAPACITY) ? MAX_QUANTUM_CAPACITY : currentQAmount;

        System.out.printf("SpaceStation created with fuel:\nN= %d and Q= %d created\n\n", this.currentNAmount, this.currentQAmount);
    }

    public synchronized CompositeFuel getFuel(CompositeFuel requestedFuel, SpaceVehicle spaceVehicle) throws InterruptedException {
//        System.out.println("im here");
        boolean enough = requestedFuel.getAmountN() <= currentNAmount && requestedFuel.getAmountQ() <= currentQAmount;
        while (currentVisitors == MAX_CONCURRENT_VEHICLES || !enough) {
            wait();
            enough = requestedFuel.getAmountN() <= currentNAmount && requestedFuel.getAmountQ() <= currentQAmount;
        }
        currentVisitors++;

        System.out.printf("SPACESHIP #%d wants to fuel up\n-->SPACESTATION: Fuel: N= %d Q= %d\n\n",
                spaceVehicle.getID(), currentNAmount, currentQAmount);

        Thread.sleep(2000); // Super realistic refuel time!

        currentNAmount -= requestedFuel.getAmountN();
        currentQAmount -= requestedFuel.getAmountQ();

        System.out.printf("SPACESHIP #%d successfully fueled up\n-->SPACESTATION: Fuel: N= %d Q= %d\n\n",
                spaceVehicle.getID(), currentNAmount, currentQAmount);


        currentVisitors--;
        notifyAll();
        return new CompositeFuel(requestedFuel.getAmountN(), requestedFuel.getAmountQ());
    }

    public synchronized void restockFuel(CompositeFuel deliveredFuel, SupplyVehicle supplyVehicle) throws InterruptedException {
        int restockedN = deliveredFuel.getAmountN();
        int restockedQ =  deliveredFuel.getAmountQ();

        boolean fuelFits = ((restockedN + currentNAmount) <= MAX_NITROGEN_CAPACITY) &&
                ((restockedQ + currentQAmount) <= MAX_QUANTUM_CAPACITY);

        while (currentVisitors >= MAX_CONCURRENT_VEHICLES || !fuelFits) {
            wait();
            fuelFits = ((restockedN + currentNAmount) <= MAX_NITROGEN_CAPACITY) &&
                    ((restockedQ + currentQAmount) <= MAX_QUANTUM_CAPACITY);
        }
        currentVisitors++;


//        while (!fuelFits) {
//            wait();
//
//        }
        this.currentNAmount += restockedN;
        this.currentQAmount += restockedQ;
        deliveredFuel.setAmountN(0);
        deliveredFuel.setAmountQ(0);
        System.out.printf("SUPPLYVEHICLE #%d refueled spacestation.\n-->SPACESTATION: Fuel: N= %d Q= %d\n\n",
                supplyVehicle.getID(), currentNAmount, currentQAmount);
        currentVisitors--;
        notifyAll();
    }

}
