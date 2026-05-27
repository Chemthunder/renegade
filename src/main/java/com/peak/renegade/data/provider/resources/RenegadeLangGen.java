package com.peak.renegade.data.provider.resources;

import com.peak.renegade.core.index.RenegadeItems;
import com.peak.renegade.core.index.tag.RenegadeItemTags;
import com.peak.renegade.core.index.RenegadeBlocks;
import com.peak.renegade.game.index.GameLayers;
import com.peak.renegade.game.index.GameLevels;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.registry.RegistryWrapper;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

/**
 * @author Chemthunder
 */
public class RenegadeLangGen extends FabricLanguageProvider {
    public RenegadeLangGen(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    public void generateTranslations(RegistryWrapper.WrapperLookup wrapperLookup, TranslationBuilder translationBuilder) {
        GameLevels.pairWithLang(translationBuilder);
        GameLayers.pairWithLang(translationBuilder);

        RenegadeItems.ITEMS.registerLang(wrapperLookup, translationBuilder);
        RenegadeBlocks.BLOCKS.registerLang(wrapperLookup, translationBuilder);

        RenegadeItemTags.TAG.registerLang(wrapperLookup, translationBuilder);

        translationBuilder.add("feedback.renegade.layer", "Current layer is %s");
        translationBuilder.add("feedback.renegade.level", "Current level is %s");

        translationBuilder.add("renegade.lvlannounce.spacer", " : // : ");
        translationBuilder.add("renegade.lvlannounce.clock", "YOUR CLOCK STARTS NOW");

        translationBuilder.add("renegade.slogan.0", "RUN FOR THE FUTURE");
        translationBuilder.add("renegade.slogan.1", "FORSAKE THE CONGLOMERATE");
        translationBuilder.add("renegade.slogan.2", "EMBRACE THE ADRENALINE");

        translationBuilder.add("block.renegade.gateway.desc_0", "Upon interaction by a game player,");
        translationBuilder.add("block.renegade.gateway.desc_1", "their countdown will be reset to the max.");

        translationBuilder.add("block.renegade.enemy_spawner.enemies", "Spawns %s enemies!");
    }

    public @NotNull String getName() {
        return "Renegade Language";
    }
}
