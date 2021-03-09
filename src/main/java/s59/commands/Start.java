package s59.commands;

import s59.bottlingPlant.IBottlingPlant;

public class Start implements ICommand {
    private IBottlingPlant bottlingPlant;

    //TODO Test
    @Override
    public void execute() {
        bottlingPlant.setStarted(true);
    }

    public void setBottlingPlant(IBottlingPlant bottlingPlant) {
        this.bottlingPlant = bottlingPlant;
    }
}
