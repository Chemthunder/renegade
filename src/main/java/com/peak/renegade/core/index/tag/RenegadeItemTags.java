package com.peak.renegade.core.index.tag;

import com.peak.renegade.core.Renegade;
import net.acoyt.acornlib.api.builder.TagBuilder;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;

/**
 * @author Chemthunder
 */
public interface RenegadeItemTags {
    TagBuilder<Item> TAG = new TagBuilder<>(Renegade.MOD_ID, RegistryKeys.ITEM);

    TagKey<Item> WEAPONS = TAG.register("weapons");
}
