package com.peak.renegade.core.index;

import com.peak.renegade.core.Renegade;
import com.peak.renegade.core.index.util.RenegadeColors;
import com.peak.renegade.game.index.GameItems;
import net.acoyt.acornlib.api.registrants.ItemGroupRegistrant;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;

/**
 * @author Chemthunder
 */
public interface RenegadeItemGroups {
    ItemGroupRegistrant GROUPS = new ItemGroupRegistrant(Renegade.MOD_ID);

    RegistryKey<ItemGroup> MAIN_KEY = RegistryKey.of(RegistryKeys.ITEM_GROUP, Renegade.id(Renegade.MOD_ID));
    ItemGroup MAIN_GROUP = GROUPS.register(MAIN_KEY.getValue().getPath(), FabricItemGroup.builder()
            .icon(() -> new ItemStack(GameItems.SHRIEKING_STONE))
            .displayName(Text.translatable("itemGroup." + Renegade.MOD_ID).withColor(RenegadeColors.END_COLOR))
            .build());

    static void init() {
        ItemGroupEvents.modifyEntriesEvent(MAIN_KEY).register(RenegadeItemGroups::buildItemGroup);
    }

    private static void buildItemGroup(FabricItemGroupEntries entries) {
        GameItems.ITEMS.toRegister.forEach(entries::add);
    }
}
