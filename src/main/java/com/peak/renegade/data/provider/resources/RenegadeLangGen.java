package com.peak.renegade.data.provider.resources;

import com.peak.renegade.game.GameInit;
import com.peak.renegade.game.index.GameItems;
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

        GameItems.ITEMS.registerLang(wrapperLookup, translationBuilder);

        translationBuilder.add("feedback.renegade.layer", "Current layer is %s");
        translationBuilder.add("feedback.renegade.level", "Current level is %s");

        translationBuilder.add("renegade.lvlannounce.spacer", " : // : ");
        translationBuilder.add("renegade.lvlannounce.clock", "YOUR CLOCK STARTS NOW");

        translationBuilder.add("renegade.slogan.0", "RUN FOR THE FUTURE");
        translationBuilder.add("renegade.slogan.1", "FORSAKE THE CONGLOMERATE");
        translationBuilder.add("renegade.slogan.2", "EMBRACE THE ANALOG");
    }

    public @NotNull String getName() {
        return "Renegade Language";
    }
}
