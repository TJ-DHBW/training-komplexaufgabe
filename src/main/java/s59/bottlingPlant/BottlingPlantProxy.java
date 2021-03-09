package s59.bottlingPlant;

import s59.containers.Bottle;

public class BottlingPlantProxy implements IBottlingPlant {
    private final BottlingPlant bottlingPlant;

    private final String userName = "root";
    //TODO encrypt
    private final String password = "pa55w0rd";
    private boolean loggedIn;

    public BottlingPlantProxy(BottlingPlant bottlingPlant) {
        this.bottlingPlant = bottlingPlant;
        this.loggedIn = false;
    }

    public boolean logIn(String userName, String password){
        if(userName.equals(this.userName)){
            //TODO check password
            loggedIn = true;
        }

        return false;
    }

    @Override
    public Bottle getNextBottle() {
        if (!loggedIn){
            System.out.println("Please log in first.");
            return null;
        }
        return bottlingPlant.getNextBottle();
    }

    @Override
    public void fillBottle(Bottle bottleToFill) {
        if (!loggedIn){
            System.out.println("Please log in first.");
            return;
        }
        bottlingPlant.fillBottle(bottleToFill);
    }

    @Override
    public void setStarted(boolean value) {
        if (!loggedIn){
            System.out.println("Please log in first.");
            return;
        }
        bottlingPlant.setStarted(value);
    }
}
