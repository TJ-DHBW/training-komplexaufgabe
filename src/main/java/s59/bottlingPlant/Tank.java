package s59.bottlingPlant;

public class Tank implements IHoseConnectable {
    private final String id;
    private final char[][][] content;

    public Tank(String id, int length, int width, int height) {
        this.id = id;
        this.content = new char[length][width][height];
    }

    public void fill(char contentChar){
        while(push(contentChar));
    }

    @Override
    public boolean push(char contentChar) {
        for(char[][] width : content){
            for(char[] height : width){
                for(int i = 0; i < height.length; i++){
                    if(height[i] == 0){
                        height[i] = contentChar;
                        return true;
                    }
                }
            }
        }

        return false;
    }

    @Override
    public char pull() {
        for(char[][] width : content){
            for(char[] height : width){
                for(int i = 0; i < height.length; i++){
                    if(height[i] != 0){
                        char tmp = height[i];
                        height[i] = 0;
                        return tmp;
                    }
                }
            }
        }

        return 0;
    }
}
