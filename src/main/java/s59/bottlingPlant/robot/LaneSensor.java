package s59.bottlingPlant.robot;

import s59.bottlingPlant.lane.ILaneObserver;

public class LaneSensor implements ILaneObserver {
    private final Robot robot;

    public LaneSensor(Robot robot) {
        this.robot = robot;
    }

    @Override
    public void notifyLaneEmpty() {
        robot.work();
    }
}
