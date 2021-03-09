package s59.bottlingPlant.hose;

public interface IHoseConnectable {
    boolean push(char contentChar);

    char pull();
}
