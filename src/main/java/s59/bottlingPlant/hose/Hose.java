package s59.bottlingPlant.hose;

public class Hose {
    private IHoseConnectable connection1;
    private IHoseConnectable connection2;

    private char internalStorage;

    public Hose() {
        this.internalStorage = 0;
    }


    public void connect(IHoseConnectable connectable) {
        if (connection1 == null) {
            connection1 = connectable;
        } else if (connection2 == null) {
            connection2 = connectable;
        } else {
            throw new IllegalStateException("A hose can not be connected to more than 2 connections.");
        }
    }

    public void push() {
        if (internalStorage == 0) {
            internalStorage = connection1.pull();
        }
        if (internalStorage != 0) {
            if (connection2.push(internalStorage)) {
                internalStorage = 0;
            }
        } else {
            System.out.println("Connection 1 is empty");
        }
    }

    public void pull() {
        if (internalStorage == 0) {
            internalStorage = connection2.pull();
        }
        if (internalStorage != 0) {
            if (connection1.push(internalStorage)) {
                internalStorage = 0;
            }
        } else {
            System.out.println("Connection 2 is empty");
        }
    }
}
