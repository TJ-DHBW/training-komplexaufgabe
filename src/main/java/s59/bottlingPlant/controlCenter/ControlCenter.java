package s59.bottlingPlant.controlCenter;

import s59.Configuration;
import s59.bottlingPlant.TankTruck;
import s59.bottlingPlant.tank.Tank;

import java.util.ArrayList;

public class ControlCenter {
    private final ArrayList<TankTruck> availableTrucks;
    private final TankSensor[] sensors;
    private final Terminal terminal;

    public ControlCenter(int numberOfTrucks, TankSensor[] sensors, Terminal terminal) {
        this.availableTrucks = new ArrayList<>();
        this.sensors = sensors;
        this.terminal = terminal;
    }

    public void refillTank(Tank tank) {
        int missingAmount = tank.getMaxLevel() - tank.getCurrentLevel();
        char refillChar = 0;
        switch (tank.getId()) {
            case "T01":
                refillChar = 'c';
                break;
            case "T02":
                refillChar = 'w';
                break;
        }
        if (refillChar == 0) throw new RuntimeException("Tank is not known: " + tank.getId());
        System.out.println("Sending truck to refill " + missingAmount + " units of " + refillChar + ".");

        requestTankTrucksForDeliveryOfSize(missingAmount);
        sendOrderToTrucks(refillChar, missingAmount, tank);
    }

    private void sendOrderToTrucks(char productChar, int amount, Tank destination) {
        ArrayList<TankTruck> notChosenTrucks = new ArrayList<>(availableTrucks);
        ArrayList<TankTruck> chosenTrucks = new ArrayList<>();

        while (amount > 0) {
            int truckIndex = Configuration.instance.r.nextInt(notChosenTrucks.size());
            TankTruck chosenTruck = notChosenTrucks.get(truckIndex);
            notChosenTrucks.remove(chosenTruck);

            if (amount >= Configuration.instance.tankTruckSize) {
                chosenTruck.fill(productChar, Configuration.instance.tankTruckSize);
                amount -= Configuration.instance.tankTruckSize;
            } else {
                chosenTruck.fill(productChar, amount);
                amount -= amount;
            }
            chosenTrucks.add(chosenTruck);
        }

        for (TankTruck truck : chosenTrucks) {
            truck.deliverContents(destination);
        }
    }

    private void requestTankTrucksForDeliveryOfSize(int requestSize) {
        while (availableTrucks.size() * Configuration.instance.tankTruckSize < requestSize) {
            availableTrucks.add(new TankTruck(Configuration.instance.tankTruckSize));
        }
    }

    public Terminal getTerminal() {
        return terminal;
    }
}
