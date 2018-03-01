package hw4.task1;

public class SpaceVehicle {

    private static int id = 0;

    private static final int N_REFUEL_AMOUNT = 50;
    private static final int Q_REFUEL_AMOUNT = 50;

    private int myID;
    private CompositeFuel myFuel;
    protected SpaceStation spaceStation;

    public SpaceVehicle(CompositeFuel myFuel, SpaceStation spaceStation) {
        this.myID = id++;
        this.myFuel = myFuel;
        this.spaceStation = spaceStation;
    }

    public void refuel() {
        CompositeFuel requestedFuel = new CompositeFuel(N_REFUEL_AMOUNT,Q_REFUEL_AMOUNT);
        CompositeFuel compositeFuel = null;

//        System.out.printf("SPACEVEHICLE: Before #%d got N= %d Q= %d\n", myID, myFuel.getAmountN(), myFuel.getAmountQ());
//        System.out.println();
//        for (int i = 0; i < 1000000000; i++);

        try {
            compositeFuel = spaceStation.getFuel(requestedFuel, this);
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println("i got interrupted!");
        }
        if (compositeFuel == null) {
            System.out.println("Got no fuel. But exited.");
        } else {
            myFuel.setAmountN(myFuel.getAmountN() + compositeFuel.getAmountN());
            myFuel.setAmountQ(myFuel.getAmountQ() + compositeFuel.getAmountQ());

            System.out.printf("SPACEVEHICLE: After #%d got N= %d Q= %d\n", myID, myFuel.getAmountN(), myFuel.getAmountQ());
            System.out.println();
        }
    }

    public int getID() {
        return this.myID;
    }
}
