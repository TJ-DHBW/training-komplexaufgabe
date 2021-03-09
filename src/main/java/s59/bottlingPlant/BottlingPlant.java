package s59.bottlingPlant;

import s59.bottlingPlant.controlCenter.ControlCenter;
import s59.bottlingPlant.hose.Hose;
import s59.bottlingPlant.hose.IHoseConnectable;
import s59.bottlingPlant.lane.Lane;
import s59.bottlingPlant.robot.Robot;
import s59.containers.Pallet;

public class BottlingPlant implements IHoseConnectable {
    private final Lane emptyBottlesLane;
    private final Robot palletRefillRobot;
    private final Robot laneRefillRobot;
    private final Hose concentrateConnection;
    private final Hose waterConnection;
    private final ControlCenter controlCenter;
    private Pallet storageForPalletWithEmptyBottles;
    private char internalWaterStorage;
    private char internalConcentrateStorage;

    public BottlingPlant(Robot palletRefillRobot, Robot laneRefillRobot, Lane emptyBottlesLane, Hose concentrateConnection, Hose waterConnection, ControlCenter controlCenter) {
        this.palletRefillRobot = palletRefillRobot;
        this.laneRefillRobot = laneRefillRobot;
        this.emptyBottlesLane = emptyBottlesLane;
        this.concentrateConnection = concentrateConnection;
        this.waterConnection = waterConnection;
        this.controlCenter = controlCenter;

        this.internalWaterStorage = 0;
        this.internalConcentrateStorage = 0;
    }

    //TODO

    @Override
    public boolean push(char contentChar) {
        switch (contentChar) {
            case 'w':
                if (internalWaterStorage != 0) {
                    internalWaterStorage = contentChar;
                    return true;
                }
            case 'c':
                if (internalConcentrateStorage != 0) {
                    internalConcentrateStorage = contentChar;
                    return true;
                }
            default:
                return false;
        }
    }

    @Override
    public char pull() {
        return 0;
    }

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
