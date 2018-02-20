package hw4.task1;

public class CompositeFuel {
    private int amountN;
    private int amountQ;

    public CompositeFuel(int amountN, int amountQ) {
        this.amountN = amountN;
        this.amountQ = amountQ;
    }

    public int getAmountN() {
        return amountN;
    }

    public int getAmountQ() {
        return amountQ;
    }

    public void setAmountN(int amountN) {
        this.amountN = amountN;
    }

    public void setAmountQ(int amountQ) {
        this.amountQ = amountQ;
    }
}
