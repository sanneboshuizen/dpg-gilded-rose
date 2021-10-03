package com.gildedrose.itemupdate;

import static com.gildedrose.itemupdate.QualityUpdateCalculator.capQuality;

/**
 * "BackstagePass" items improve in value when the date of the event nears but become worthless after the event
 */
public class BackstagePass implements ItemUpdateBehaviour {
    @Override
    public int getNewQualityAfterUpdate(int currentSellInNrOfDays, int currentQuality) {
        final int newQuality;
        if (currentSellInNrOfDays <= 0) {
            newQuality = 0;
        } else if (currentSellInNrOfDays <= 5) {
            newQuality = currentQuality + 3;
        } else if (currentSellInNrOfDays <= 10) {
            newQuality = currentQuality + 2;
        } else {
            newQuality = currentQuality + 1;
        }
        return capQuality(newQuality);
    }
}
