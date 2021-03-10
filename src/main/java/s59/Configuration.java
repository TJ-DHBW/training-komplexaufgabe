package s59;

import java.util.Random;

public enum Configuration {
    instance;

    public final Random r = new Random();

    public final float waterRatio = 0.9f;
    public final float concentrateRatio = 0.1f;
    public final int tankTruckSize = 25000;

    public final boolean verbose = false;
}
