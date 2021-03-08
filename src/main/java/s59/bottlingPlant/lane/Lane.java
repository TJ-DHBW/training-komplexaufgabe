package s59.bottlingPlant.lane;

import s59.containers.Bottle;

import java.util.ArrayList;
import java.util.Arrays;

public class Lane {
    private final Bottle[] laneContent;
    private final ArrayList<ILaneObserver> observers;

    public Lane() {
        this.laneContent = new Bottle[25];
        this.observers = new ArrayList<>();
    }

    public void addObserver(ILaneObserver observer){
        observers.add(observer);
    }

    public void removeObserver(ILaneObserver observer){
        observers.remove(observer);
    }

    public boolean isLaneEmpty(){
        for(Bottle bottle : laneContent){
            if(bottle != null) return false;
        }
        return true;
    }

    public Bottle[] pullEmptyBottles(){
        Bottle[] tmp = Arrays.copyOf(laneContent, laneContent.length);
        Arrays.fill(laneContent, null);
        notifyObservers();
        return tmp;
    }

    private void notifyObservers(){
        for(ILaneObserver observer : observers){
            observer.notifyLaneEmpty();
        }
    }

    public Bottle[] getLaneContent() {
        return laneContent;
    }
}
