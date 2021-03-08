package s59.bottlingPlant;

import s59.bottlingPlant.lane.Lane;
import s59.bottlingPlant.robot.Robot;
import s59.containers.Pallet;

public class BottlingPlant {
    private Pallet storageForPalletWithEmptyBottles;
    private final Lane emptyBottlesLane;
    private final Robot palletRefillRobot;
    private final Robot laneRefillRobot;

    public BottlingPlant(Robot palletRefillRobot, Robot laneRefillRobot, Lane emptyBottlesLane) {
        this.palletRefillRobot = palletRefillRobot;
        this.laneRefillRobot = laneRefillRobot;
        this.emptyBottlesLane = emptyBottlesLane;
    }

    //TODO

    //region Getter and Setter
    public Pallet getStorageForPalletWithEmptyBottles() {
        return storageForPalletWithEmptyBottles;
    }

    public void setStorageForPalletWithEmptyBottles(Pallet storageForPalletWithEmptyBottles) {
        this.storageForPalletWithEmptyBottles = storageForPalletWithEmptyBottles;
    }

    public Lane getEmptyBottlesLane() {
        return emptyBottlesLane;
    }

    public Robot getPalletRefillRobot() {
        return palletRefillRobot;
    }
//endregion
}
