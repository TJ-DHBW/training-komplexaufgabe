package s59.containers;

/**
 * A Pallet has a capacity of 7500 Bottles, because 25*20*15 = 7500. Even though the specification says 6000
 */
public class Pallet {
    /**
     * L:25, B:20, H:15
     */
    private final Bottle[][][] content;


    public Pallet(Bottle[][][] content) {
        checkDimensions(content);
        this.content = content;
    }

    public static Pallet getPalletWithEmptyBottles() {
        Bottle[][][] bottles = new Bottle[25][20][15];
        for (Bottle[][] width : bottles) {
            for (Bottle[] height : width) {
                for (int i = 0; i < height.length; i++) {
                    height[i] = new Bottle();
                }
            }
        }

        return new Pallet(bottles);
    }

    private static void checkDimensions(Bottle[][][] content) {
        if (content.length != 25) throw new IllegalArgumentException("Length must be 25.");
        for (Bottle[][] width : content) {
            if (width.length != 20) throw new IllegalArgumentException("Width must be 20.");
            for (Bottle[] height : width) {
                if (height.length != 15) throw new IllegalArgumentException("Height must be 15.");
            }
        }
    }

    public boolean isEmpty() {
        for (Bottle[][] width : content) {
            for (Bottle[] height : width) {
                for (Bottle bottle : height) {
                    if (bottle != null) return false;
                }
            }
        }
        return true;
    }

    public Bottle[][][] getContent() {
        return content;
    }
}
