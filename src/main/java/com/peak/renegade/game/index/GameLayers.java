package com.peak.renegade.game.index;

import com.peak.renegade.api.game.level.GameLayer;
import com.peak.renegade.core.Renegade;
import com.peak.renegade.core.index.RenegadeRegistries;
import net.acoyt.acornlib.api.util.MiscUtils;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.registry.Registry;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Chemthunder
 */
public interface GameLayers {
    List<GameLayer> LAYERS = new ArrayList<>();

    GameLayer LOBBY = registerLayer("lobby", 0xFFffffff);
    GameLayer PROLOGUE = registerLayer("prologue", 0xFFffffff);

    GameLayer INTO_THE_BLAZE = registerLayer("into_the_blaze", 0xFF9d3434);
    GameLayer EVER_DOWNWARD = registerLayer("ever_downward", 0xFF9aedb3);
    GameLayer FIRE_WHEN_READY = registerLayer("fire_when_ready", 0xFFa90f0f);
    GameLayer SPARROWFLIGHT = registerLayer("sparrowflight", 0xFF6ba8c0);
    GameLayer MORRIGAN = registerLayer("morrigan", 0xFF8e6bc0);
    GameLayer CRESCENDO = registerLayer("crescendo", 0xFF1e7f53);
    GameLayer DIURNAL_WAYFINDER = registerLayer("diurnal_wayfinder", 0xFFee5ae8);

    GameLayer FINALE = registerLayer("finale", 0xFFc2af49);
    GameLayer CREDITS = registerLayer("credits", 0xFFffffff);

    static GameLayer registerLayer(String name, int color) {
        GameLayer built = new GameLayer(name, color);
        LAYERS.add(built);
        return built;
    }

    static void index() {
        LAYERS.forEach(gameLayer -> {
            Registry.register(RenegadeRegistries.GAME_LAYER, Renegade.id(gameLayer.name()), gameLayer);
        });
    }
    
    static String createTranslationKey(GameLayer level) {
        return "layer." + level.name();
    }

    static void pairWithLang(FabricLanguageProvider.TranslationBuilder translationBuilder) {
        LAYERS.forEach(layer -> {
            translationBuilder.add(createTranslationKey(layer), MiscUtils.formatString(layer.name()));
        });
    }
}
