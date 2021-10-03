package com.gildedrose.itemupdate;

import static com.gildedrose.itemupdate.QualityUpdateCalculator.UPDATE_DIRECTION.INCREASE;

public class GetsBetterWithAge implements ItemUpdateBehaviour {

    @Override
    public int getNewQualityAfterUpdate(int currentSellInNrOfDays, int currentQuality) {
        return new QualityUpdateCalculator(currentSellInNrOfDays, currentQuality, INCREASE).getNewQuality();
    }
}
