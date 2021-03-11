package s59;

import s59.bottlingPlant.BottlingPlant;
import s59.bottlingPlant.controlCenter.ControlCenter;
import s59.bottlingPlant.controlCenter.TankSensor;
import s59.bottlingPlant.controlCenter.Terminal;
import s59.bottlingPlant.hose.Hose;
import s59.bottlingPlant.lane.Lane;
import s59.bottlingPlant.robot.R01;
import s59.bottlingPlant.robot.R02;
import s59.bottlingPlant.tank.Tank;
import s59.commands.Info;
import s59.commands.Login;
import s59.commands.Order;
import s59.commands.Start;
import s59.containers.CentralStorage;

public class Application {
    private CentralStorage centralStorage;
    private BottlingPlant bottlingPlant;

    public static void main(String[] args) {
        Application application = new Application();

        application.init();
        application.prepare();
        application.commands();

        System.out.println("Done");
    }

    public void init() {
        centralStorage = CentralStorage.getCentralStorageWithEmptyBottlePallets();

        R01 r01 = new R01();
        R02 r02 = new R02();
        Lane lane = new Lane();

        Tank concentrateTank = new Tank("T01", 100, 100, 100);
        concentrateTank.fill('c');
        Hose concentrateHose = new Hose();
        TankSensor concentrateSensor = new TankSensor(concentrateTank);
        Tank waterTank = new Tank("T02", 1000, 500, 500);
        waterTank.fill('w');
        Hose waterHose = new Hose();
        TankSensor waterSensor = new TankSensor(waterTank);


        ControlCenter controlCenter = new ControlCenter(3, new TankSensor[]{concentrateSensor, waterSensor}, new Terminal());
        concentrateSensor.setControlCenter(controlCenter);
        concentrateSensor.setTriggerRatio(0.15f);
        concentrateTank.addObserver(concentrateSensor);
        waterSensor.setControlCenter(controlCenter);
        waterSensor.setTriggerRatio(0.2f);
        waterTank.addObserver(waterSensor);

        bottlingPlant = new BottlingPlant(r01,
                r02,
                lane,
                concentrateHose,
                waterHose,
                controlCenter);

        controlCenter.getTerminal().setBottlingPlant(bottlingPlant);

        r01.setOriginOfPallets(centralStorage);
        r01.setDestinationOfPallets(bottlingPlant);
        r02.setBottlingPlant(bottlingPlant);
        lane.addObserver(r02.getLaneSensor());

        concentrateHose.connect(bottlingPlant);
        concentrateHose.connect(concentrateTank);
        waterHose.connect(bottlingPlant);
        waterHose.connect(waterTank);
    }

    public void prepare() {
        bottlingPlant.getPalletRefillRobot().work();
        bottlingPlant.getLaneRefillRobot().work();
        bottlingPlant.getControlCenter().getTerminal().sendCommand(new Login("root", "pa55w0rd"));
    }

    private void commands() {
        bottlingPlant.getControlCenter().getTerminal().sendCommand(new Start());
        bottlingPlant.getControlCenter().getTerminal().sendCommand(new Order(690000, "Cola"));
        bottlingPlant.getControlCenter().getTerminal().sendCommand(new Order(420000, "IceTea"));
        bottlingPlant.getControlCenter().getTerminal().sendCommand(new Info());
    }

    public CentralStorage getCentralStorage() {
        return centralStorage;
    }

    public BottlingPlant getBottlingPlant() {
        return bottlingPlant;
    }
}
