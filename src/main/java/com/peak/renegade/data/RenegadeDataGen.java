package com.peak.renegade.data;

import com.peak.omnia.api.registration.DataInitializer;
import com.peak.renegade.core.Renegade;
import com.peak.renegade.data.provider.resources.RenegadeLangGen;
import com.peak.renegade.data.provider.resources.RenegadeModelGen;
import com.peak.renegade.data.provider.tag.RenegadeItemTagGen;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.registry.RegistryBuilder;
import net.minecraft.registry.RegistryWrapper;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class RenegadeDataGen implements DataGeneratorEntrypoint {
    public static final DataInitializer DATA = new DataInitializer(Renegade.MOD_ID, List.of(
    ));

	public void onInitializeDataGenerator(FabricDataGenerator fdg) {
        var pack = fdg.createPack();

        pack.addProvider(Dyn::new);

        pack.addProvider(RenegadeLangGen::new);
        pack.addProvider(RenegadeModelGen::new);

        pack.addProvider(RenegadeItemTagGen::new);
	}

    public void buildRegistry(RegistryBuilder registryBuilder) {
        DATA.buildRegistries(registryBuilder);
    }

    public static class Dyn extends FabricDynamicRegistryProvider {
        public Dyn(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
            super(output, registriesFuture);
        }

        protected void configure(RegistryWrapper.WrapperLookup wrapperLookup, Entries entries) {
            DATA.loadConfigurations(wrapperLookup, entries);
        }

        public String getName() {
            return "Renegade Registries";
        }
    }
}
