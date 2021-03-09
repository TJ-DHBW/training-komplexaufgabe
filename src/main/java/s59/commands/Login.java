package s59.commands;

import s59.bottlingPlant.BottlingPlantProxy;

public class Login implements ICommand {
    private final String userName;
    private final String password;

    public Login(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    private BottlingPlantProxy proxy;

    //TODO Test
    @Override
    public void execute() {
        if(proxy.logIn(userName, password)){
            System.out.println("Login successful");
        }else{
            System.out.println("Wrong credentials, try again");
        }
    }

    public void setProxy(BottlingPlantProxy proxy) {
        this.proxy = proxy;
    }
}
