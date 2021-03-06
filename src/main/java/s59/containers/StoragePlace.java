package s59.containers;

public class StoragePlace {
    private Pallet bottomPallet;
    private Pallet middlePallet;
    private Pallet topPallet;

    public StoragePlace() {
    }

    public StoragePlace(Pallet bottomPallet, Pallet middlePallet, Pallet topPallet) {
        this.bottomPallet = bottomPallet;
        this.middlePallet = middlePallet;
        this.topPallet = topPallet;
    }

    public Pallet retrieveNextPallet(){
        if(topPallet != null){
            Pallet tmp = topPallet;
            topPallet = null;
            return tmp;
        }
        else if(middlePallet != null) {
            Pallet tmp = middlePallet;
            middlePallet = null;
            return tmp;
        }
        else if(bottomPallet != null){
            Pallet tmp = bottomPallet;
            bottomPallet = null;
            return tmp;
        }

        return null;
    }

    public void setBottomPallet(Pallet bottomPallet) {
        this.bottomPallet = bottomPallet;
    }

    public void setMiddlePallet(Pallet middlePallet) {
        this.middlePallet = middlePallet;
    }

    public void setTopPallet(Pallet topPallet) {
        this.topPallet = topPallet;
    }
}
