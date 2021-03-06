package s59.containers;

/**
 * A Pallet has a capacity of 7500 Bottles, because 25*20*15 = 7500. Even though the specification says 6000
 */
public class Pallet {
    /**
     * L:25, B:20, H:15
     */
    private final Bottle[][][] content;

    public Pallet() {
        content = new Bottle[25][20][15];
    }

    public Bottle[][][] getContent() {
        return content;
    }
}
