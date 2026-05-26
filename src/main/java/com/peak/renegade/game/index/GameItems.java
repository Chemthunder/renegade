package com.peak.renegade.game.index;

import com.peak.renegade.api.item.RenegadeUtilItem;
import com.peak.renegade.core.Renegade;
import net.acoyt.acornlib.api.registrants.ItemRegistrant;
import net.minecraft.item.Item;

/**
 * @author Chemthunder
 */
public interface GameItems {
    ItemRegistrant ITEMS = new ItemRegistrant(Renegade.MOD_ID);

    Item SHRIEKING_STONE = ITEMS.register("shrieking_stone", RenegadeUtilItem::new, new Item.Settings().maxCount(1));

    static void index() {}
}
