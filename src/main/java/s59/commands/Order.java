package s59.commands;

import s59.Configuration;
import s59.bottlingPlant.IBottlingPlant;
import s59.containers.Bottle;

public class Order implements ICommand {
    private final int amount;
    private final String type;
    private IBottlingPlant target;

    public Order(int amount, String type) {
        this.amount = amount;
        this.type = type;
    }

    @Override
    public void execute() {
        Bottle[] bottles = new Bottle[amount];
        int filledBefore = target.getTotalFilledBottles();

        for (int i = 0; i < bottles.length; i++) {
            Bottle bottleToFill = target.getNextBottle();
            target.fillBottle(bottleToFill);
            bottles[i] = bottleToFill;
        }

        int filledAfter = target.getTotalFilledBottles();
        System.out.println("Filled " + (filledAfter - filledBefore) + " bottles with " + type + ".");

        if (Configuration.instance.verbose) {
            System.out.print("Hashes: ");
            for (Bottle bottle : bottles) {
                System.out.print(Integer.toHexString(bottle.hashCode()) + ", ");
            }
        }
    }

    @Override
    public void setTarget(IBottlingPlant target) {
        this.target = target;
    }
}
