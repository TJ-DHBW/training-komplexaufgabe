package s59.bottlingPlant.robot;

import s59.bottlingPlant.BottlingPlant;
import s59.containers.CentralStorage;
import s59.containers.Pallet;
import s59.containers.StoragePlace;

public class R01 extends Robot {
    private final CentralStorage originOfPallets;
    private final BottlingPlant destinationOfPallets;

    public R01(CentralStorage originOfPallets, BottlingPlant destinationOfPallets) {
        this.originOfPallets = originOfPallets;
        this.destinationOfPallets = destinationOfPallets;
    }

    public void carryPalletIfSpace(){
        if(destinationOfPallets.getStorageForPalletWithEmptyBottles() == null){
            for(StoragePlace[] width : originOfPallets.getStoragePlaces()){
                for(StoragePlace storagePlace : width){
                    Pallet pallet = storagePlace.retrieveNextPallet();
                    if(pallet != null){
                        destinationOfPallets.setStorageForPalletWithEmptyBottles(pallet);
                    }
                }
            }
        }
    }

    @Override
    public void work() {
        carryPalletIfSpace();
    }
}
