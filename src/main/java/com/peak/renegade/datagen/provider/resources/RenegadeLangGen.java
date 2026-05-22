package com.peak.renegade.datagen.provider.resources;

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

        translationBuilder.add("feedback.renegade.layer", "Current layer is %s");
        translationBuilder.add("feedback.renegade.level", "Current level is %s");
    }

    public @NotNull String getName() {
        return "Renegade Language";
    }
}
