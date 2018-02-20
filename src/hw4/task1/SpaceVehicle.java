package hw4.task1;

public abstract class SpaceVehicle {

    private static int id = 0;

    private int myID;
    private CompositeFuel myFuel;
    protected SpaceStation spaceStation;

    public SpaceVehicle(CompositeFuel myFuel, SpaceStation spaceStation) {
        this.myID = id++;
        this.myFuel = myFuel;
        this.spaceStation = spaceStation;
    }

    public void refuel() {
        CompositeFuel requestedFuel = new CompositeFuel(50 ,50);
        CompositeFuel compositeFuel = null;

        System.out.printf("SPACEVEHICLE: Before #%d got N= %d Q= %d\n", myID, myFuel.getAmountN(), myFuel.getAmountQ());
        System.out.println();

        try {
            compositeFuel = spaceStation.getFuel(requestedFuel, this);
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println("i got interrupted!");
        }
        if (compositeFuel == null) {
            System.out.println("NULL fuel, something wrong");
        } else {
            myFuel.setAmountN(myFuel.getAmountN() + compositeFuel.getAmountN());
            myFuel.setAmountQ(myFuel.getAmountQ() + compositeFuel.getAmountQ());

            System.out.printf("SPACEVEHICLE: After #%d got N= %d Q= %d\n", myID, myFuel.getAmountN(), myFuel.getAmountQ());
            System.out.println();
        }
    }

    protected CompositeFuel getMyFuel() {
        return this.myFuel;
    }

    public int getID() {
        return this.myID;
    }
}
