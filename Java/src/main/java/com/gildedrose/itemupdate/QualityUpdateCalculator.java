package com.gildedrose.itemupdate;

import lombok.AllArgsConstructor;

/**
 * Most items follow the same algorithm for increase/decrease in quality, and this helps in the calculation
 */
@AllArgsConstructor
class QualityUpdateCalculator {
    private static int DEFAULT_UPDATE_BY = 1;
    private static int DEFAULT_UPDATE_FACTOR = 1;
    private static int MAX_QUALITY = 50;
    private static int MIN_QUALITY = 0;

    private final int currentSellInNrOfDays;
    private final int currentQuality;
    private final UPDATE_DIRECTION updateDirection;
    private final int updateFactor;

    public QualityUpdateCalculator(int currentSellInNrOfDays, int currentQuality, UPDATE_DIRECTION updateDirection) {
        this.currentSellInNrOfDays = currentSellInNrOfDays;
        this.currentQuality = currentQuality;
        this.updateDirection = updateDirection;
        this.updateFactor = DEFAULT_UPDATE_FACTOR;
    }

    int getNewQuality() {
        final int updateBy = isPastSellBy() ? DEFAULT_UPDATE_BY * 2 : DEFAULT_UPDATE_BY;
        int newQuality = updateDirection == UPDATE_DIRECTION.INCREASE ? increase(updateBy) : decrease(updateBy);
        return capQuality(newQuality);
    }

    private boolean isPastSellBy() {
        return this.currentSellInNrOfDays <= 0;
    }

    private int decrease(int updateBy) {
        return currentQuality - updateBy * updateFactor;
    }

    private int increase(int updateBy) {
        return currentQuality + updateBy * updateFactor;
    }

    static int capQuality(int quality) {
        return Math.max(MIN_QUALITY, Math.min(quality, MAX_QUALITY));
    }

    enum UPDATE_DIRECTION {
        DECREASE, INCREASE
    }
}
