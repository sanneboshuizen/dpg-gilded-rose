package com.gildedrose.itemupdate;

import static com.gildedrose.itemupdate.QualityUpdateCalculator.UPDATE_DIRECTION.DECREASE;

/**
 * "Conjured" items degrade in Quality twice as fast as normal items
 */
public class Conjured implements ItemUpdateBehaviour {

    @Override
    public int getNewQualityAfterUpdate(int currentSellInNrOfDays, int currentQuality) {
        return new QualityUpdateCalculator(currentSellInNrOfDays, currentQuality, DECREASE, 2).getNewQuality();
    }
}
