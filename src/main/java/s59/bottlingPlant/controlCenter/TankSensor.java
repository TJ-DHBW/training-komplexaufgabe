package s59.bottlingPlant.controlCenter;

import s59.bottlingPlant.tank.ITankObserver;
import s59.bottlingPlant.tank.Tank;

public class TankSensor implements ITankObserver {
    private final Tank observedTank;
    private final ControlCenter controlCenter;

    private float triggerRatio;

    public TankSensor(Tank observedTank, ControlCenter controlCenter) {
        this.observedTank = observedTank;
        this.controlCenter = controlCenter;
    }

    @Override
    public void levelChanged(int newLevel) {
        float fillRatio = (float)newLevel / observedTank.getMaxLevel();
        if(fillRatio <= triggerRatio){
            controlCenter.refillTank(observedTank);
        }
    }

    public void setTriggerRatio(float triggerRatio) {
        this.triggerRatio = triggerRatio;
    }
}
