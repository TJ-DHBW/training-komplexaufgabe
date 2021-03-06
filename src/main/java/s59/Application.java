package s59;

import s59.containers.CentralStorage;

public class Application {
    public static void main(String[] args) {
        CentralStorage centralStorage = CentralStorage.getCentralStorageWithEmptyBottlePallets();
        System.out.println("Done");
    }
}
