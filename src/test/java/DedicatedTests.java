import org.junit.Assert;
import org.junit.Test;
import s59.Application;
import s59.bottlingPlant.tank.Tank;
import s59.commands.Order;
import s59.commands.Start;
import s59.containers.Bottle;
import s59.containers.Pallet;
import s59.containers.StoragePlace;

/* IMPORTANT: Tests have never been run, because they use to much space:
Java heap space
java.lang.OutOfMemoryError: Java heap space
	at s59.containers.Bottle.<init>(Bottle.java:11)
	at s59.containers.Pallet.getPalletWithEmptyBottles(Pallet.java:23)
	at s59.containers.StoragePlace.getStoragePlaceWithEmptyBottlePallets(StoragePlace.java:16)
	at s59.containers.CentralStorage.getCentralStorageWithEmptyBottlePallets(CentralStorage.java:19)
	at s59.Application.init(Application.java:33)
	at DedicatedTests.fillingOfBottles(DedicatedTests.java:106)
	at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
	at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	at java.base/java.lang.reflect.Method.invoke(Method.java:566)
	at org.junit.runners.model.FrameworkMethod$1.runReflectiveCall(FrameworkMethod.java:50)
	at org.junit.internal.runners.model.ReflectiveCallable.run(ReflectiveCallable.java:12)
	at org.junit.runners.model.FrameworkMethod.invokeExplosively(FrameworkMethod.java:47)
	at org.junit.internal.runners.statements.InvokeMethod.evaluate(InvokeMethod.java:17)
	at org.junit.runners.ParentRunner.runLeaf(ParentRunner.java:325)
	at org.junit.runners.BlockJUnit4ClassRunner.runChild(BlockJUnit4ClassRunner.java:78)
	at org.junit.runners.BlockJUnit4ClassRunner.runChild(BlockJUnit4ClassRunner.java:57)
	at org.junit.runners.ParentRunner$3.run(ParentRunner.java:290)
	at org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:71)
	at org.junit.runners.ParentRunner.runChildren(ParentRunner.java:288)
	at org.junit.runners.ParentRunner.access$000(ParentRunner.java:58)
	at org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:268)
	at org.junit.runners.ParentRunner.run(ParentRunner.java:363)
	at org.gradle.api.internal.tasks.testing.junit.JUnitTestClassExecutor.runTestClass(JUnitTestClassExecutor.java:110)
	at org.gradle.api.internal.tasks.testing.junit.JUnitTestClassExecutor.execute(JUnitTestClassExecutor.java:58)
	at org.gradle.api.internal.tasks.testing.junit.JUnitTestClassExecutor.execute(JUnitTestClassExecutor.java:38)
	at org.gradle.api.internal.tasks.testing.junit.AbstractJUnitTestClassProcessor.processTestClass(AbstractJUnitTestClassProcessor.java:62)
	at org.gradle.api.internal.tasks.testing.SuiteTestClassProcessor.processTestClass(SuiteTestClassProcessor.java:51)
	at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
	at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	at java.base/java.lang.reflect.Method.invoke(Method.java:566)
 */

public class DedicatedTests {

    @Test
    public void correctCreationOfBottlingPlant() {
        Application app = new Application();
        app.init();

        // Central Storage is full
        Assert.assertNotNull(app.getCentralStorage());
        for (StoragePlace[] width : app.getCentralStorage().getStoragePlaces()) {
            for (StoragePlace storagePlace : width) {
                Assert.assertNotNull(storagePlace);
                for (Pallet pallet : storagePlace.getPalletsAsArray()) {
                    Assert.assertNotNull(pallet);
                    for (Bottle[][] pWidth : pallet.getContent()) {
                        for (Bottle[] pHeight : pWidth) {
                            for (Bottle bottle : pHeight) {
                                Assert.assertNotNull(bottle);
                            }
                        }
                    }
                }
            }
        }

        // Starts off
        Assert.assertNotNull(app.getBottlingPlant());
        Assert.assertFalse(app.getBottlingPlant().isStarted());

        // Has robots
        Assert.assertNotNull(app.getBottlingPlant().getPalletRefillRobot());
        Assert.assertNotNull(app.getBottlingPlant().getLaneRefillRobot());

        // Has right tanks
        Assert.assertNotNull(app.getBottlingPlant().getConcentrateConnection());
        Assert.assertNotNull(app.getBottlingPlant().getConcentrateConnection().getConnection2());
        Tank concentrateTank = (Tank) app.getBottlingPlant().getConcentrateConnection().getConnection2();
        Assert.assertEquals("T01", concentrateTank.getId());
        Assert.assertEquals(100, concentrateTank.getContent().length);
        for (char[][] width : concentrateTank.getContent()) {
            Assert.assertEquals(100, width.length);
            for (char[] height : width) {
                Assert.assertEquals(100, height.length);
                for (char c : height) {
                    Assert.assertEquals('c', c);
                }
            }
        }

        Assert.assertNotNull(app.getBottlingPlant().getWaterConnection());
        Assert.assertNotNull(app.getBottlingPlant().getWaterConnection().getConnection2());
        Tank waterTank = (Tank) app.getBottlingPlant().getWaterConnection().getConnection2();
        Assert.assertEquals("T02", waterTank.getId());
        Assert.assertEquals(1000, waterTank.getContent().length);
        for (char[][] width : waterTank.getContent()) {
            Assert.assertEquals(500, width.length);
            for (char[] height : width) {
                Assert.assertEquals(500, height.length);
                for (char c : height) {
                    Assert.assertEquals('w', c);
                }
            }
        }

        Assert.assertEquals('c', waterTank.getContent()[0][0][0]);

    }

    @Test
    public void bottlingPlantReadyToRun() {
        Application app = new Application();
        app.init();
        app.prepare();

        Assert.assertTrue(app.getBottlingPlant().getControlCenter().getTerminal().getBottlingPlant().isLoggedIn());
        for (Bottle bottle : app.getBottlingPlant().getEmptyBottlesLane().getLaneContent()) {
            Assert.assertNotNull(bottle);
        }
    }

    @Test
    public void startingOfBottlingPlant() {
        Application app = new Application();
        app.init();
        app.prepare();

        app.getBottlingPlant().getControlCenter().getTerminal().sendCommand(new Start());

        Assert.assertTrue(app.getBottlingPlant().isStarted());
    }

    @Test
    public void fillingOfBottles() {
        Application app = new Application();
        app.init();
        app.prepare();
        app.getBottlingPlant().getControlCenter().getTerminal().sendCommand(new Start());

        app.getBottlingPlant().getControlCenter().getTerminal().sendCommand(new Order(500, "TestType"));

        Assert.assertEquals(500, app.getBottlingPlant().getTotalFilledBottles());

        app.getBottlingPlant().getControlCenter().getTerminal().sendCommand(new Order(1234, "TestType2"));

        Assert.assertEquals(1734, app.getBottlingPlant().getTotalFilledBottles());
    }
}
