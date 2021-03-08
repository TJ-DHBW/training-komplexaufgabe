package s59.bottlingPlant;

public interface IHoseConnectable {
    boolean push(char contentChar);

    char pull();
}
