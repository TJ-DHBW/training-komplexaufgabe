package s59.bottlingPlant.controlCenter;

import s59.Configuration;
import s59.bottlingPlant.TankTruck;
import s59.bottlingPlant.tank.Tank;

public class ControlCenter {
    private final TankTruck[] availableTrucks;
    private final TankSensor[] sensors;

    public ControlCenter(int numberOfTrucks, TankSensor[] sensors) {
        this.availableTrucks = new TankTruck[numberOfTrucks];
        for(int i = 0; i < availableTrucks.length; i++){
            availableTrucks[i] = new TankTruck(25000);
        }
        this.sensors = sensors;
    }

    public void refillTank(Tank tank){
        int missingAmount = tank.getMaxLevel() - tank.getCurrentLevel();
        char refillChar = 0;
        switch (tank.getId()){
            case "T01":
                refillChar = 'c';
            case "T02":
                refillChar = 'w';
        }
        if(refillChar == 0) throw new RuntimeException("Tank is not known: " + tank.getId());

        int truckIndex = Configuration.instance.r.nextInt(availableTrucks.length);
        TankTruck chosenTruck = availableTrucks[truckIndex];
        chosenTruck.fill(refillChar, missingAmount);
        chosenTruck.deliverContents(tank);
    }
}
