package s59.bottlingPlant;

import s59.bottlingPlant.lane.Lane;
import s59.containers.Bottle;
import s59.containers.Pallet;

public class BottlingPlantProxy implements IBottlingPlant {
    private final BottlingPlant bottlingPlant;

    private final String userName = "root";
    private final String password = SHA256.toHexString(SHA256.getSHA("pa55w0rd"));
    private boolean loggedIn;

    public BottlingPlantProxy(BottlingPlant bottlingPlant) {
        this.bottlingPlant = bottlingPlant;
        this.loggedIn = false;
    }

    public boolean logIn(String userName, String password) {
        if (userName.equals(this.userName)) {
            if (SHA256.toHexString(SHA256.getSHA(password)).equals(this.password)) {
                loggedIn = true;
                return true;
            }
        }

        return false;
    }

    @Override
    public Bottle getNextBottle() {
        if (!loggedIn) {
            System.out.println("Please log in first.");
            return null;
        }
        return bottlingPlant.getNextBottle();
    }

    @Override
    public void fillBottle(Bottle bottleToFill) {
        if (!loggedIn) {
            System.out.println("Please log in first.");
            return;
        }
        bottlingPlant.fillBottle(bottleToFill);
    }

    @Override
    public void setStarted(boolean value) {
        if (!loggedIn) {
            System.out.println("Please log in first.");
            return;
        }
        bottlingPlant.setStarted(value);
    }

    @Override
    public Lane getEmptyBottlesLane() {
        if (!loggedIn) {
            System.out.println("Please log in first.");
            return null;
        }
        return bottlingPlant.getEmptyBottlesLane();
    }

    @Override
    public Pallet getStorageForPalletWithEmptyBottles() {
        if (!loggedIn) {
            System.out.println("Please log in first.");
            return null;
        }
        return bottlingPlant.getStorageForPalletWithEmptyBottles();
    }

    @Override
    public Bottle[] getBottleWaitingArea() {
        if (!loggedIn) {
            System.out.println("Please log in first.");
            return null;
        }
        return bottlingPlant.getBottleWaitingArea();
    }

    @Override
    public int getTotalFilledBottles() {
        if (!loggedIn) {
            System.out.println("Please log in first.");
            return -1;
        }
        return bottlingPlant.getTotalFilledBottles();
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }
}
