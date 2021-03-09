package s59.commands;

import s59.bottlingPlant.IBottlingPlant;
import s59.containers.Bottle;

public class Order implements ICommand {
    private final int amount;
    private final String type;
    private IBottlingPlant bottlingPlant;

    public Order(int amount, String type) {
        this.amount = amount;
        this.type = type;
    }

    //TODO Test
    @Override
    public void execute() {
        Bottle[] bottles = new Bottle[amount];

        for(int i = 0; i < bottles.length; i++){
            Bottle bottleToFill = bottlingPlant.getNextBottle();
            bottlingPlant.fillBottle(bottleToFill);
            bottles[i] = bottleToFill;
        }

        for(Bottle bottle : bottles){
            System.out.println("Provided Bottle of " + type + " with content: " + new String(bottle.getContent()));
        }
    }

    public void setBottlingPlant(IBottlingPlant bottlingPlant) {
        this.bottlingPlant = bottlingPlant;
    }
}
