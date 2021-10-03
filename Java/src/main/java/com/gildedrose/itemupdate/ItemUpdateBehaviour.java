package com.gildedrose.itemupdate;

public interface ItemUpdateBehaviour {

    int getNewQualityAfterUpdate(int currentSellInNrOfDays, int currentQuality);

    default int getNewSellInAfterUpdate(int currentSellInNrOfDays) {
        return currentSellInNrOfDays - 1;
    }

}
