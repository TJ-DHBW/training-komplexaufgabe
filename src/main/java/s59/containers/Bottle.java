package s59.containers;

import java.util.Arrays;

public class Bottle {
    private final char[] content;


    public Bottle() {
        content = new char[330];
        Arrays.fill(content, 'd');
    }

    public boolean insertFluid(char fluidChar){
        for(int i = 0; i < content.length; i++){
            if(content[i] == 'd'){
                content[i] = fluidChar;
                return true;
            }
        }
        return false;
    }

    public char[] getContent() {
        return content;
    }
}
