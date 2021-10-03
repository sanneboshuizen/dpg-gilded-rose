package com.gildedrose.itemupdate;

import static com.gildedrose.itemupdate.QualityUpdateCalculator.UPDATE_DIRECTION.DECREASE;

/**
 * Items with "standard" degradation decrease in value with every update, and decrease twice as fast when the sell by date has passed
 */
public class Standard implements ItemUpdateBehaviour {

    @Override
    public int getNewQualityAfterUpdate(int currentSellInNrOfDays, int currentQuality) {
        return new QualityUpdateCalculator(currentSellInNrOfDays, currentQuality, DECREASE).getNewQuality();
    }

}
