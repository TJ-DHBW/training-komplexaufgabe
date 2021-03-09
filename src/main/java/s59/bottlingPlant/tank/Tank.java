package s59.bottlingPlant.tank;

import s59.bottlingPlant.IHoseConnectable;

import java.util.ArrayList;

public class Tank implements IHoseConnectable {
    private final String id;
    private final char[][][] content;
    private final int maxLevel;
    private int currentLevel;

    private final ArrayList<ITankObserver> observers;

    public Tank(String id, int length, int width, int height) {
        this.id = id;
        this.content = new char[length][width][height];
        this.maxLevel = length * width * height;
        this.currentLevel = 0;

        this.observers = new ArrayList<>();
    }

    public void fill(char contentChar){
        while(push(contentChar));
    }

    public void addObserver(ITankObserver observer){
        observers.add(observer);
    }

    public void removeObserver(ITankObserver observer){
        observers.remove(observer);
    }

    private void notifyObservers(){
        for(ITankObserver observer : observers){
            observer.levelChanged(currentLevel);
        }
    }

    @Override
    public boolean push(char contentChar) {
        for(char[][] width : content){
            for(char[] height : width){
                for(int i = 0; i < height.length; i++){
                    if(height[i] == 0){
                        height[i] = contentChar;
                        currentLevel++;
                        notifyObservers();
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
                        currentLevel--;
                        notifyObservers();
                        return tmp;
                    }
                }
            }
        }

        return 0;
    }

    public int getMaxLevel() {
        return maxLevel;
    }

    public int getCurrentLevel() {
        return currentLevel;
    }

    public String getId() {
        return id;
    }
}
