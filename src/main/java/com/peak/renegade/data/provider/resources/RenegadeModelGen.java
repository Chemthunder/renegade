package com.peak.renegade.data.provider.resources;

import com.peak.renegade.game.index.GameItems;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.client.data.BlockStateModelGenerator;
import net.minecraft.client.data.ItemModelGenerator;
import net.minecraft.client.data.Models;

/**
 * @author Chemthunder
 */
public class RenegadeModelGen extends FabricModelProvider {
    public RenegadeModelGen(FabricDataOutput output) {
        super(output);
    }

    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {}

    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(GameItems.SHRIEKING_STONE, Models.GENERATED);
    }
}
