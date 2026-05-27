package com.peak.renegade.data.provider.tag;

import com.peak.renegade.core.index.RenegadeItems;
import com.peak.renegade.core.index.tag.RenegadeItemTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

/**
 * @author Chemthunder
 */
public class RenegadeItemTagGen extends FabricTagProvider.ItemTagProvider {
    public RenegadeItemTagGen(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    protected void configure(RegistryWrapper.WrapperLookup registries) {
        this.valueLookupBuilder(RenegadeItemTags.WEAPONS)
                .add(RenegadeItems.REVOLVER)
                .add(RenegadeItems.KNIFE)
                .setReplace(false);
    }

    public String getName() {
        return "Renegade Item Tags";
    }
}
