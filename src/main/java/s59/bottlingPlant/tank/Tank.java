package s59.bottlingPlant.tank;

import s59.bottlingPlant.hose.IHoseConnectable;

import java.util.ArrayList;
import java.util.Arrays;

public class Tank implements IHoseConnectable {
    private final String id;
    private final char[][][] content;
    private final int maxLevel;
    private final ArrayList<ITankObserver> observers;
    private int currentLevel;

    public Tank(String id, int length, int width, int height) {
        this.id = id;
        this.content = new char[length][width][height];
        this.maxLevel = length * width * height;
        this.currentLevel = 0;

        this.observers = new ArrayList<>();
    }

    public void fill(char contentChar) {
        for (char[][] width : content) {
            for (char[] height : width) {
                Arrays.fill(height, contentChar);
            }
        }
        currentLevel = maxLevel;
    }

    public void addObserver(ITankObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(ITankObserver observer) {
        observers.remove(observer);
    }

    private void notifyObservers() {
        for (ITankObserver observer : observers) {
            observer.levelChanged(currentLevel);
        }
    }

    @Override
    public boolean push(char contentChar) {
        if (contentChar == 'w') {
            int i = 0;
        }
        if (currentLevel >= maxLevel) return false;

        int length = (((currentLevel) / content[0][0].length) / content[0].length) % content.length;
        int width = ((currentLevel) / content[0][0].length) % content[0].length;
        int height = (currentLevel) % content[0][0].length;

        if (content[length][width][height] == 0) {
            content[length][width][height] = contentChar;
            currentLevel++;
            notifyObservers();
            return true;
        }

        throw new IllegalStateException("There is content where no content should be.");
        /* This is to slow
        for (char[][] width : content) {
            for (char[] height : width) {
                for (int i = 0; i < height.length; i++) {
                    if (height[i] == 0) {
                        height[i] = contentChar;
                        currentLevel++;
                        notifyObservers();
                        return true;
                    }
                }
            }
        }

        return false;
         */
    }

    @Override
    public char pull() {
        if (currentLevel <= 0) return 0;

        int length = (((currentLevel - 1) / content[0][0].length) / content[0].length) % content.length;
        int width = ((currentLevel - 1) / content[0][0].length) % content[0].length;
        int height = (currentLevel - 1) % content[0][0].length;

        if (content[length][width][height] != 0) {
            char tmp = content[length][width][height];
            content[length][width][height] = 0;
            currentLevel--;
            notifyObservers();
            return tmp;
        }

        throw new IllegalStateException("There is no content where content should be.");
        /* This is to slow
        for (char[][] width : content) {
            for (char[] height : width) {
                for (int i = 0; i < height.length; i++) {
                    if (height[i] != 0) {
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
         */
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
