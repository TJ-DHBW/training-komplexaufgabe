package s59.commands;

import s59.bottlingPlant.BottlingPlantProxy;
import s59.bottlingPlant.IBottlingPlant;

public class Login implements ICommand {
    private final String userName;
    private final String password;
    private BottlingPlantProxy target;

    public Login(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    @Override
    public void execute() {
        if (target.logIn(userName, password)) {
            System.out.println("Login successful");
        } else {
            System.out.println("Wrong credentials, try again");
        }
    }

    @Override
    public void setTarget(IBottlingPlant target) {
        if (target.getClass() != BottlingPlantProxy.class)
            throw new IllegalArgumentException("Login can only be called on Proxies.");
        this.target = (BottlingPlantProxy) target;
    }
}
