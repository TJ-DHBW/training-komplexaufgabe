package s59.bottlingPlant;

import s59.Configuration;
import s59.bottlingPlant.controlCenter.ControlCenter;
import s59.bottlingPlant.hose.Hose;
import s59.bottlingPlant.hose.IHoseConnectable;
import s59.bottlingPlant.lane.Lane;
import s59.bottlingPlant.robot.Robot;
import s59.containers.Bottle;
import s59.containers.Pallet;

public class BottlingPlant implements IHoseConnectable, IBottlingPlant {
    private final Lane emptyBottlesLane;
    private final Robot palletRefillRobot;
    private final Robot laneRefillRobot;
    private final Hose concentrateConnection;
    private final Hose waterConnection;
    private final ControlCenter controlCenter;

    private Pallet storageForPalletWithEmptyBottles;
    private Bottle[] bottleWaitingArea;
    private char internalWaterStorage;
    private char internalConcentrateStorage;
    private boolean isStarted;
    private int totalFilledBottles;

    public BottlingPlant(Robot palletRefillRobot, Robot laneRefillRobot, Lane emptyBottlesLane, Hose concentrateConnection, Hose waterConnection, ControlCenter controlCenter) {
        this.palletRefillRobot = palletRefillRobot;
        this.laneRefillRobot = laneRefillRobot;
        this.emptyBottlesLane = emptyBottlesLane;
        this.concentrateConnection = concentrateConnection;
        this.waterConnection = waterConnection;
        this.controlCenter = controlCenter;

        this.bottleWaitingArea = new Bottle[0];
        this.internalWaterStorage = 0;
        this.internalConcentrateStorage = 0;
        this.isStarted = false;
        this.totalFilledBottles = 0;
    }


    private void rotateBottlesIn() {
        bottleWaitingArea = emptyBottlesLane.pullEmptyBottles();
    }

    @Override
    public Bottle getNextBottle() {
        if (!isStarted) throw new IllegalStateException("Cant provide Bottles while off.");

        for (int i = 1; i < bottleWaitingArea.length; i++) {
            if (bottleWaitingArea[i] != null) {
                Bottle tmp = bottleWaitingArea[i];
                bottleWaitingArea[i] = null;
                return tmp;
            }
        }

        rotateBottlesIn();
        Bottle tmp = bottleWaitingArea[0];
        bottleWaitingArea[0] = null;
        return tmp;
    }

    private char getWater() {
        if (internalWaterStorage == 0) waterConnection.pull();
        char tmp = internalWaterStorage;
        internalWaterStorage = 0;
        return tmp;
    }

    private char getConcentrate() {
        if (internalConcentrateStorage == 0) concentrateConnection.pull();
        char tmp = internalConcentrateStorage;
        internalConcentrateStorage = 0;
        return tmp;
    }

    @Override
    public void setStarted(boolean started) {
        isStarted = started;
    }

    @Override
    public void fillBottle(Bottle bottle) {
        if (!isStarted) throw new IllegalStateException("Cant fill bottles while off.");

        int size = bottle.getSize();

        int waterAmount = (int) Math.floor(size * Configuration.instance.waterRatio);
        for (int i = 0; i < waterAmount; i++) {
            bottle.insertFluid(getWater());
        }

        int concentrateAmount = (int) Math.floor(size * Configuration.instance.concentrateRatio);
        for (int i = 0; i < concentrateAmount; i++) {
            bottle.insertFluid(getConcentrate());
        }

        totalFilledBottles++;
    }

    @Override
    public boolean push(char contentChar) {
        switch (contentChar) {
            case 'w':
                if (internalWaterStorage == 0) {
                    internalWaterStorage = contentChar;
                    return true;
                }
            case 'c':
                if (internalConcentrateStorage == 0) {
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

    public Bottle[] getBottleWaitingArea() {
        return bottleWaitingArea;
    }

    public int getTotalFilledBottles() {
        return totalFilledBottles;
    }

    public ControlCenter getControlCenter() {
        return controlCenter;
    }

    public Robot getLaneRefillRobot() {
        return laneRefillRobot;
    }

    //endregion
}
