package s59.containers;

public class CentralStorage {
    /**
     * L:10, B:10
     */
    private final StoragePlace[][] storagePlaces;


    public CentralStorage(StoragePlace[][] storagePlaces) {
        checkDimensions(storagePlaces);
        this.storagePlaces = storagePlaces;
    }

    public static CentralStorage getCentralStorageWithEmptyBottlePallets() {
        StoragePlace[][] storagePlaces = new StoragePlace[10][10];
        for (StoragePlace[] width : storagePlaces) {
            for (int i = 0; i < width.length; i++) {
                width[i] = StoragePlace.getStoragePlaceWithEmptyBottlePallets();
            }
        }

        return new CentralStorage(storagePlaces);
    }

    private static void checkDimensions(StoragePlace[][] storagePlaces) {
        if (storagePlaces.length != 10) throw new IllegalArgumentException("Length must be 10.");
        for (StoragePlace[] width : storagePlaces) {
            if (width.length != 10) throw new IllegalArgumentException("Width must be 10.");
        }
    }

    public StoragePlace[][] getStoragePlaces() {
        return storagePlaces;
    }
}
