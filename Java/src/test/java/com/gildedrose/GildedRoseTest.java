package com.gildedrose;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Tests the updates to the inventory (i.e. items) of the gilded rose inn")
class GildedRoseTest {

    @DisplayName("Tests updates to all different item types")
    @Nested
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    class UpdateTests {
        private Stream<Arguments> standardBehaviourTestCases() {
            List<String> standardItems = List.of("+5 Dexterity Vest", "Elixir of the Mongoose", "Standard item");
            return Stream.of(
                // testCaseDescription, items, currentSellIn, currentQuality, expectedSellInAfterUpdate, expectedQualityAfterUpdate
                Arguments.of("quality_degrades_one_point_per_day", standardItems, 10, 20, 9, 19),
                Arguments.of("once_sellbydate_has_passed_quality_decreases_twice_as_fast", standardItems, 0, 20, -1, 18),
                Arguments.of("the_quality_of_an_item_should_never_go_below_zero", standardItems, 10, 0, 9, 0)
            );
        }

        private Stream<Arguments> getsBetterWithAgeTestCases() {
            List<String> agedBrie = List.of("Aged Brie");
            return Stream.of(
                // testCaseDescription, items, currentSellIn, currentQuality, expectedSellInAfterUpdate, expectedQualityAfterUpdate
                Arguments.of("aged_brie_gets_better_with_age", agedBrie, 2, 0, 1, 1),
                Arguments.of("the_quality_of_an_item_is_never_more_than_50", agedBrie, 10, 50, 9, 50)
            );
        }

        private Stream<Arguments> legendaryItemTestCases() {
            List<String> legendaryItems = List.of("Sulfuras, Hand of Ragnaros");
            return Stream.of(
                // testCaseDescription, legendaryItems, currentSellIn, currentQuality, expectedSellInAfterUpdate, expectedQualityAfterUpdate
                Arguments.of("legendary_items_have_constant_quality_of_80_and_thus_never_expire_test1", legendaryItems, 0, 80, 0, 80),
                Arguments.of("legendary_items_have_constant_quality_of_80_and_thus_never_expire_test2", legendaryItems, -1, 80, -1, 80)
            );
        }

        private Stream<Arguments> backstagePassesTestCases() {
            List<String> backstagePasses = List.of("Backstage passes to a TAFKAL80ETC concert");
            return Stream.of(
                // testCaseDescription, backstagePasses, currentSellIn, currentQuality, expectedSellInAfterUpdate, expectedQualityAfterUpdate
                Arguments.of("quality_increases_by_1_when_there_are_more_than_10_days_left", backstagePasses, 15, 20, 14, 21),
                Arguments.of("quality_increases_by_2_when_there_are_10_or_less_days_left_test1", backstagePasses, 10, 40, 9, 42),
                Arguments.of("quality_increases_by_2_when_there_are_10_or_less_days_left_test2", backstagePasses, 6, 40, 5, 42),
                Arguments.of("quality_increases_by_2_when_there_are_10_or_less_days_left_to_max_50", backstagePasses, 10, 49, 9, 50),
                Arguments.of("quality_increases_by_3_when_there_are_5_or_less_days_left", backstagePasses, 5, 40, 4, 43),
                Arguments.of("worthless_after_the_event", backstagePasses, 0, 14, -1, 0)
            );
        }

        private Stream<Arguments> conjuredItemTestCases() {
            List<String> conjuredItems = List.of("Conjured Mana Cake");
            return Stream.of(
                // testCaseDescription, items, currentSellIn, currentQuality, expectedSellInAfterUpdate, expectedQualityAfterUpdate
                Arguments.of("quality_degrades_two_points_per_day", conjuredItems, 10, 20, 9, 18),
                Arguments.of("once_sellbydate_has_passed_quality_decreases_by_4_points", conjuredItems, 0, 20, -1, 16),
                Arguments.of("the_quality_of_an_item_should_never_go_below_zero", conjuredItems, 10, 0, 9, 0)
            );
        }

        @MethodSource({"standardBehaviourTestCases", "getsBetterWithAgeTestCases", "legendaryItemTestCases", "backstagePassesTestCases", "conjuredItemTestCases"})
        @ParameterizedTest(name = "{0}")
        void testUpdateBehaviour(String testCaseDescription, List<String> itemNames, int currentSellIn, int currentQuality, int expectedSellInAfterUpdate, int expectedQualityAfterUpdate) {
            itemNames.stream().forEach(itemName -> {
                testItemUpdate(itemName, currentSellIn, currentQuality, expectedSellInAfterUpdate, expectedQualityAfterUpdate);
            });
        }

        private void testItemUpdate(String itemName, int currentSellIn, int currentQuality, int expectedSellInAfterUpdate, int expectedQualityAfterUpdate) {
            // given
            Item[] items = new Item[]{new Item(itemName, currentSellIn, currentQuality)};
            GildedRose gildedRose = new GildedRose(items);

            // when
            gildedRose.updateQuality();

            // then
            assertThat(expectedSellInAfterUpdate).as(itemName).isEqualTo(gildedRose.items[0].sellIn);
            assertThat(expectedQualityAfterUpdate).as(itemName).isEqualTo(gildedRose.items[0].quality);
        }
    }


}
