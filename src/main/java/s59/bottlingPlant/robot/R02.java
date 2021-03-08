package s59.bottlingPlant.robot;

import s59.bottlingPlant.BottlingPlant;
import s59.containers.Bottle;

public class R02 extends Robot {
    private final BottlingPlant bottlingPlant;
    private final LaneSensor laneSensor;

    public R02(BottlingPlant bottlingPlant) {
        this.bottlingPlant = bottlingPlant;

        this.laneSensor = new LaneSensor(this);
    }

    private void refillEmptyBottlesIfEmpty(){
        if(!bottlingPlant.getEmptyBottlesLane().isLaneEmpty()) return;

        putBottlesInMachine(rotateBottles(retrieveEmptyBottlesFromPallet()));
    }

    private Bottle[][] retrieveEmptyBottlesFromPallet(){
        if(bottlingPlant.getStorageForPalletWithEmptyBottles() == null || bottlingPlant.getStorageForPalletWithEmptyBottles().isEmpty()){
            bottlingPlant.setStorageForPalletWithEmptyBottles(null);
            bottlingPlant.getPalletRefillRobot().work();
        }

        Bottle[][][] emptyBottles = bottlingPlant.getStorageForPalletWithEmptyBottles().getContent();
        Bottle[][] bottles = new Bottle[1][25];
        for(int i = 0; i < emptyBottles[0].length; i++){
            for(int j = emptyBottles[0][i].length - 1; j >= 0; j--){
                if(emptyBottles[0][i][j] != null){
                    for(int k = 0; k < emptyBottles.length; k++){
                        bottles[0][k] = emptyBottles[k][i][j];
                        emptyBottles[k][i][j] = null;
                    }
                    return bottles;
                }
            }
        }

        throw new IllegalStateException("This method should always be able to receive the empty Bottles.");
    }

    /**
     * Turns a [1][x] into a [x][1]
     * @param initialBottles Array to turn.
     * @return               The rotated Array.
     */
    private static Bottle[][] rotateBottles(Bottle[][] initialBottles){
        if(initialBottles.length != 1) throw new IllegalArgumentException("the first array must only have one element.");

        Bottle[][] ret = new Bottle[initialBottles.length][1];
        for(int i = 0; i < initialBottles[0].length; i++){
            ret[i][0] = initialBottles[0][i];
        }

        return ret;
    }

    private void putBottlesInMachine(Bottle[][] bottles){
        if(bottles == null || bottles.length != bottlingPlant.getEmptyBottlesLane().getLaneContent().length) throw new IllegalArgumentException("The amount of bottles to put in the BottlingPlant must be " + bottlingPlant.getEmptyBottlesLane().getLaneContent().length);

        for(int i = 0; i < bottles.length; i++){
            bottlingPlant.getEmptyBottlesLane().getLaneContent()[i] = bottles[i][0];
        }
    }

    @Override
    public void work() {
        refillEmptyBottlesIfEmpty();
    }

    public LaneSensor getLaneSensor() {
        return laneSensor;
    }
}
