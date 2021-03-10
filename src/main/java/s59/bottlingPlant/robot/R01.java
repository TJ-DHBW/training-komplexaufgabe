package s59.bottlingPlant.robot;

import s59.Configuration;
import s59.bottlingPlant.BottlingPlant;
import s59.containers.CentralStorage;
import s59.containers.Pallet;
import s59.containers.StoragePlace;

public class R01 extends Robot {
    private CentralStorage originOfPallets;
    private BottlingPlant destinationOfPallets;


    public void carryPalletIfSpace() {
        if (destinationOfPallets.getStorageForPalletWithEmptyBottles() == null) {
            for (StoragePlace[] width : originOfPallets.getStoragePlaces()) {
                for (StoragePlace storagePlace : width) {
                    Pallet pallet = storagePlace.retrieveNextPallet();
                    if (pallet != null) {
                        destinationOfPallets.setStorageForPalletWithEmptyBottles(pallet);
                        if (Configuration.instance.verbose)
                            System.out.println("Brought a new pallet of empty bottles.");
                        return;
                    }
                }
            }
        }

        System.out.println("No more pallets with empty bottles found.");
        throw new IllegalStateException("Please don't order that much at one. We dont have any empty Bottles left.");
    }

    @Override
    public void work() {
        carryPalletIfSpace();
    }

    public void setOriginOfPallets(CentralStorage originOfPallets) {
        this.originOfPallets = originOfPallets;
    }

    public void setDestinationOfPallets(BottlingPlant destinationOfPallets) {
        this.destinationOfPallets = destinationOfPallets;
    }
}
