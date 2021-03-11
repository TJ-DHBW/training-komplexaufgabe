package s59.commands;

import s59.bottlingPlant.IBottlingPlant;

public class Start implements ICommand {
    private IBottlingPlant target;

    @Override
    public void execute() {
        target.setStarted(true);
    }

    @Override
    public void setTarget(IBottlingPlant target) {
        this.target = target;
    }
}
