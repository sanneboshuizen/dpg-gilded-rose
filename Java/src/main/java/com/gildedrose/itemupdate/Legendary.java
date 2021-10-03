package com.gildedrose.itemupdate;

/**
 * "Legendary" items have a constant quality and therefore don't expire
 */
public class Legendary implements ItemUpdateBehaviour {
    @Override
    public int getNewQualityAfterUpdate(int currentSellInNrOfDays, int currentQuality) {
        return currentQuality;
    }

    @Override
    public int getNewSellInAfterUpdate(int currentSellInNrOfDays) {
        return currentSellInNrOfDays;
    }
}
