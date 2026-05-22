package com.peak.renegade.core.index;

import com.peak.renegade.api.game.level.GameLayer;
import com.peak.renegade.api.game.level.GameLevel;
import com.peak.renegade.core.Renegade;
import net.fabricmc.fabric.api.event.registry.FabricRegistryBuilder;
import net.fabricmc.fabric.api.event.registry.RegistryAttribute;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;

/**
 * @author Chemthunder
 */
public interface RenegadeRegistries {
    RegistryKey<Registry<GameLevel>> levelKey = RegistryKey.ofRegistry(Renegade.id("game_level"));
    Registry<GameLevel> GAME_LEVEL = FabricRegistryBuilder.createSimple(levelKey)
            .attribute(RegistryAttribute.MODDED)
            .buildAndRegister();

    RegistryKey<Registry<GameLayer>> layerKey = RegistryKey.ofRegistry(Renegade.id("game_layer"));
    Registry<GameLayer> GAME_LAYER = FabricRegistryBuilder.createSimple(layerKey)
            .attribute(RegistryAttribute.MODDED)
            .buildAndRegister();

    static void init() {}
}
