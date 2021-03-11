package s59.bottlingPlant.controlCenter;

import s59.bottlingPlant.BottlingPlant;
import s59.bottlingPlant.BottlingPlantProxy;
import s59.commands.ICommand;

public class Terminal {
    private BottlingPlantProxy bottlingPlant;

    public void sendCommand(ICommand command) {
        command.setTarget(bottlingPlant);
        command.execute();
    }

    public BottlingPlantProxy getBottlingPlant() {
        return bottlingPlant;
    }

    public void setBottlingPlant(BottlingPlant bottlingPlant) {
        this.bottlingPlant = new BottlingPlantProxy(bottlingPlant);
    }
}
