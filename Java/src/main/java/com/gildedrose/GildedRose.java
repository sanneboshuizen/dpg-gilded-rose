package com.gildedrose;

import com.gildedrose.itemupdate.ItemUpdateBehaviour;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class GildedRose {
    Item[] items;
    List<ItemUpdateWrapper> itemUpdateWrappers;

    public GildedRose(Item[] items) {
        this.items = items;
        this.itemUpdateWrappers = Arrays.asList(items).stream().map(item -> new ItemUpdateWrapper(item)).collect(Collectors.toList());
    }

    public void updateQuality() {
        itemUpdateWrappers.stream().forEach(itemUpdateWrapper -> updateItem(itemUpdateWrapper));

//        // THIS WAS THE OLD CODE
//        for (int i = 0; i < items.length; i++) {
//            Item item = items[i];
//            String itemName = item.name;
//            if (itemName.equals("Aged Brie") || itemName.equals("Backstage passes to a TAFKAL80ETC concert")) {
//                updateQuality(item, item.quality + 1);
//
//                if (itemName.equals("Backstage passes to a TAFKAL80ETC concert")) {
//                    if (item.sellIn < 11) {
//                        updateQuality(item, item.quality + 1);
//                    }
//                    if (item.sellIn < 6) {
//                        updateQuality(item, item.quality + 1);
//                    }
//                }
//            } else {
//                if (!itemName.equals("Sulfuras, Hand of Ragnaros")) {
//                    updateQuality(item, item.quality - 1);
//                }
//            }
//
//            if (!itemName.equals("Sulfuras, Hand of Ragnaros")) {
//                updateSellIn(item, item.sellIn - 1);
//            }
//
//            if (isPastSellBy(item)) {
//                if (itemName.equals("Aged Brie")) {
//                    updateQuality(item, item.quality + 1);
//                } else {
//                    if (itemName.equals("Backstage passes to a TAFKAL80ETC concert")) {
//                        updateQuality(item, 0);
//                    } else {
//                        if (!itemName.equals("Sulfuras, Hand of Ragnaros")) {
//                            updateQuality(item, item.quality - 1);
//                        }
//                    }
//                }
//            }
//        }

    }

    private boolean isPastSellBy(Item item) {
        return item.sellIn < 0;
    }

    private void updateQuality(Item item, int newQuality) {
        item.quality = Math.max(0, Math.min(newQuality, 50));
    }

    private void updateSellIn(Item item, int sellIn) {
        item.sellIn = sellIn;
    }

    private void updateItem(ItemUpdateWrapper itemUpdateWrapper) {
        ItemUpdateBehaviour itemUpdateBehaviour = itemUpdateWrapper.getItemUpdateBehaviour();
        Item item = itemUpdateWrapper.getItem();
        int newSellIn = itemUpdateBehaviour.getNewSellInAfterUpdate(item.sellIn);
        int newQuality = itemUpdateBehaviour.getNewQualityAfterUpdate(item.sellIn, item.quality);
        item.sellIn = newSellIn;
        item.quality = newQuality;
    }
}
