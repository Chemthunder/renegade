package com.peak.renegade.datagen;

import com.peak.omnia.api.registration.DataInitializer;
import com.peak.renegade.core.Renegade;
import com.peak.renegade.datagen.provider.resources.RenegadeLangGen;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.registry.RegistryBuilder;

import java.util.List;

public class RenegadeDataGen implements DataGeneratorEntrypoint {
    public static final DataInitializer DATA = new DataInitializer(Renegade.MOD_ID, List.of(

    ));

	public void onInitializeDataGenerator(FabricDataGenerator fdg) {
        var pack = fdg.createPack();

        pack.addProvider(RenegadeLangGen::new);
	}

    public void buildRegistry(RegistryBuilder registryBuilder) {
    //    DATA.buildRegistries(registryBuilder);
    }
}
