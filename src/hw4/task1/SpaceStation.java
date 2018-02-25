package hw4.task1;

public class SpaceStation {

    private static final int MAX_NITROGEN_CAPACITY = 500;
    private static final int MAX_QUANTUM_CAPACITY = 300;
    private static final int MAX_CONCURRENT_VEHICLES = 4;

    private int currentVisitors = 0;

    private Object concurrentLock = new Object();

    private volatile int currentNAmount;
    private volatile int currentQAmount;

    public SpaceStation(int currentNAmount, int currentQAmount) {
        this.currentNAmount = (currentNAmount > MAX_NITROGEN_CAPACITY) ? MAX_NITROGEN_CAPACITY : currentNAmount;
        this.currentQAmount = (currentQAmount > MAX_QUANTUM_CAPACITY) ? MAX_QUANTUM_CAPACITY : currentQAmount;

        System.out.printf("SpaceStation with N= %d and Q= %d created\n", this.currentNAmount, this.currentQAmount);
        System.out.println();
    }

    public CompositeFuel getFuel(CompositeFuel requestedFuel, SpaceVehicle spaceVehicle) throws InterruptedException {
        synchronized (concurrentLock) {
            boolean enough = requestedFuel.getAmountN() <= currentNAmount && requestedFuel.getAmountQ() <= currentQAmount;
            while (currentVisitors >= MAX_CONCURRENT_VEHICLES || !enough) {
                concurrentLock.wait();
                enough = requestedFuel.getAmountN() <= currentNAmount && requestedFuel.getAmountQ() <= currentQAmount;
            }
            currentVisitors++;

            System.out.printf("SPACESTATION: Spaceship #%d wants to fuel up\n", spaceVehicle.getID());
            System.out.printf("SPACESTATION: Fuel: N= %d Q= %d\n", currentNAmount, currentQAmount);
            System.out.println();

            Thread.sleep(2000); // Super realistic refuel time!

            currentNAmount -= requestedFuel.getAmountN();
            currentQAmount -= requestedFuel.getAmountQ();

            System.out.printf("SPACESTATION: Spaceship #%d successfully fueled up!\n", spaceVehicle.getID());
            System.out.printf("SPACESTATION: Fuel: N= %d Q= %d\n", currentNAmount, currentQAmount);
            System.out.println();
        }


        synchronized (concurrentLock) {
            currentVisitors--;
            concurrentLock.notifyAll();
        }

        return new CompositeFuel(requestedFuel.getAmountN(), requestedFuel.getAmountQ());
    }

    public void restockFuel(CompositeFuel deliveredFuel, SupplyVehicle supplyVehicle) throws InterruptedException {

        synchronized (concurrentLock) {
            while (currentVisitors >= MAX_CONCURRENT_VEHICLES) {
                concurrentLock.wait();
            }
            currentVisitors++;
        }

        int restockedN = deliveredFuel.getAmountN();
        int restockedQ =  deliveredFuel.getAmountQ();

        synchronized (concurrentLock) {
            boolean fuelFits = ((restockedN + currentNAmount) <= MAX_NITROGEN_CAPACITY) &&
                    ((restockedQ + currentQAmount) <= MAX_QUANTUM_CAPACITY);
            while (!fuelFits) {
                concurrentLock.wait();
                fuelFits = ((restockedN + currentNAmount) <= MAX_NITROGEN_CAPACITY) &&
                        ((restockedQ + currentQAmount) <= MAX_QUANTUM_CAPACITY);
            }
            this.currentNAmount += restockedN;
            this.currentQAmount += restockedQ;
            deliveredFuel.setAmountN(0);
            deliveredFuel.setAmountQ(0);
            System.out.printf("SPACESTATION: SupplyVehicle #%d refueled spacestation.\n", supplyVehicle.getID());
            System.out.printf("SPACESTATION: Fuel: N= %d Q= %d\n", currentNAmount, currentQAmount);
            System.out.println();
            currentVisitors--;
            concurrentLock.notifyAll();
        }
    }

}
