package s59.bottlingPlant;

import s59.containers.Bottle;

public interface IBottlingPlant {
    Bottle getNextBottle();

    void fillBottle(Bottle bottleToFill);

    void setStarted(boolean value);
}
