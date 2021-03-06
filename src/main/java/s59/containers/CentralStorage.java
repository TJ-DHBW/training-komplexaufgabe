package s59.containers;

public class CentralStorage {
    /**
     * L:10, B:10
     */
    private final StoragePlace[][] storagePlaces;

    public CentralStorage() {
        storagePlaces = new StoragePlace[10][10];
    }

    public StoragePlace[][] getStoragePlaces() {
        return storagePlaces;
    }
}
