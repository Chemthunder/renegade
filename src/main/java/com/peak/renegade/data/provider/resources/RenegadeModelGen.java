package com.peak.renegade.data.provider.resources;

import com.peak.renegade.core.Renegade;
import com.peak.renegade.core.client.item.RevolverTintSource;
import com.peak.renegade.core.index.RenegadeBlocks;
import com.peak.renegade.core.index.RenegadeItems;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.client.data.*;

import java.util.Optional;

/**
 * @author Chemthunder
 */
public class RenegadeModelGen extends FabricModelProvider {
    public static final Model KNIFE_BLOODY = new Model(Optional.of(Renegade.id("knife_bloody")), Optional.empty(), TextureKey.LAYER0);

    public RenegadeModelGen(FabricDataOutput output) {
        super(output);
    }

    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.registerSimpleState(RenegadeBlocks.GATEWAY);
        blockStateModelGenerator.registerItemModel(RenegadeBlocks.GATEWAY);

        blockStateModelGenerator.registerSimpleState(RenegadeBlocks.ENEMY_SPAWNER);
        blockStateModelGenerator.registerItemModel(RenegadeBlocks.ENEMY_SPAWNER);
    }

    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(RenegadeItems.SHRIEKING_STONE, Models.GENERATED);
        itemModelGenerator.registerWithTintedLayer(RenegadeItems.REVOLVER, "_tint", new RevolverTintSource());

        createRevolver(itemModelGenerator);
        createKnife(itemModelGenerator);
    }

    private static void createRevolver(ItemModelGenerator generator) {

    }

    private static void createKnife(ItemModelGenerator generator) {
        generator.register(RenegadeItems.KNIFE);
    }
}
