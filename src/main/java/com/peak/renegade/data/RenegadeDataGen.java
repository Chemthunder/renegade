package com.peak.renegade.data;

import com.peak.omnia.api.registration.DataInitializer;
import com.peak.renegade.core.Renegade;
import com.peak.renegade.data.provider.resources.RenegadeLangGen;
import com.peak.renegade.data.provider.resources.RenegadeModelGen;
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
        pack.addProvider(RenegadeModelGen::new);
	}

    public void buildRegistry(RegistryBuilder registryBuilder) {
    //    DATA.buildRegistries(registryBuilder);
    }
}
