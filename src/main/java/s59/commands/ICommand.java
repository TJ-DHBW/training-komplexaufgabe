package s59.commands;

import s59.bottlingPlant.IBottlingPlant;

public interface ICommand {
    void execute();

    void setTarget(IBottlingPlant bottlingPlant);
}
