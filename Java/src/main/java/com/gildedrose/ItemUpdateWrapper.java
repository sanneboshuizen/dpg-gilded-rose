package com.gildedrose;

import com.gildedrose.itemupdate.BackstagePass;
import com.gildedrose.itemupdate.Conjured;
import com.gildedrose.itemupdate.GetsBetterWithAge;
import com.gildedrose.itemupdate.ItemUpdateBehaviour;
import com.gildedrose.itemupdate.Legendary;
import com.gildedrose.itemupdate.Standard;
import lombok.Value;

@Value
public class ItemUpdateWrapper {
    private static final Legendary LEGENDARY = new Legendary();
    private static final GetsBetterWithAge GETS_BETTER_WITH_AGE = new GetsBetterWithAge();
    private static final BackstagePass BACKSTAGE_PASS = new BackstagePass();
    private static final Conjured CONJURED = new Conjured();
    private static final Standard STANDARD = new Standard();

    public ItemUpdateWrapper(Item item) {
        this.item = item;
        this.itemUpdateBehaviour =
            switch (item.name) {
                case "Sulfuras, Hand of Ragnaros" -> LEGENDARY;
                case "Aged Brie" -> GETS_BETTER_WITH_AGE;
                case "Backstage passes to a TAFKAL80ETC concert" -> BACKSTAGE_PASS;
                case "Conjured Mana Cake" -> CONJURED;
                default -> STANDARD;
            };
    }

    Item item;
    ItemUpdateBehaviour itemUpdateBehaviour;
}
