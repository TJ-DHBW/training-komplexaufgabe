package s59.commands;

import s59.bottlingPlant.IBottlingPlant;
import s59.containers.Bottle;

public class Info implements ICommand {
    private IBottlingPlant target;

    @Override
    public void execute() {
        StringBuilder infoString = new StringBuilder();

        infoString.append("BottlingPlant Status:\n");

        if (target.getEmptyBottlesLane().getLaneContent()[0] != null) {
            infoString.append("Input lane is filled.\n");
        } else infoString.append("Input lane is empty.\n");

        int counter = 0;
        for (Bottle bottle : target.getBottleWaitingArea()) {
            if (bottle != null) counter++;
        }
        infoString.append("The machine still has ").append(counter).append(" empty bottles available.\n");

        if (target.getStorageForPalletWithEmptyBottles() != null) {
            infoString.append("The machine has a Pallet of empty bottles nearby.\n");
        } else infoString.append("The machine has no Pallet of empty bottles nearby.\n");

        infoString.append("The machine has filled ").append(target.getTotalFilledBottles()).append(" bottles so far.");

        System.out.println(infoString.toString());
    }

    @Override
    public void setTarget(IBottlingPlant target) {
        this.target = target;
    }
}
