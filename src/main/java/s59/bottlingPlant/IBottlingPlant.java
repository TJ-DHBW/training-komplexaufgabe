package s59.bottlingPlant;

import s59.bottlingPlant.lane.Lane;
import s59.containers.Bottle;
import s59.containers.Pallet;

public interface IBottlingPlant {
    Bottle getNextBottle();

    void fillBottle(Bottle bottleToFill);

    void setStarted(boolean value);

    Lane getEmptyBottlesLane();

    Pallet getStorageForPalletWithEmptyBottles();

    Bottle[] getBottleWaitingArea();

    int getTotalFilledBottles();
}
