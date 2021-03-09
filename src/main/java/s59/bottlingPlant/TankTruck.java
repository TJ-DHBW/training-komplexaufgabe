package s59.bottlingPlant;

import s59.bottlingPlant.tank.Tank;

public class TankTruck {
    public final char[] content;

    public TankTruck(int size) {
        this.content = new char[size];
    }

    public void fill(char contentChar, int amount) {
        for (int i = 0; i < content.length; i++) {
            if (amount > 0) {
                content[i] = contentChar;
                amount--;
            } else {
                content[i] = 0;
            }
        }

        if (amount > 0) throw new IllegalArgumentException("You cant fill more then the Trucksize into the Truck.");
    }

    public void deliverContents(Tank destination) {
        for (int i = 0; i < content.length; i++) {
            if (content[i] != 0) {
                boolean pushed = destination.push(content[i]);
                if (!pushed) throw new IllegalStateException("Truck could not unload all its content.");
                content[i] = 0;
            }
        }
    }
}
