package com.peak.renegade.core;

import com.peak.renegade.core.command.RenegadeCommand;
import com.peak.renegade.core.index.*;
import com.peak.renegade.game.GameInit;
import net.acoyt.acornlib.api.ALib;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.metadata.ModMetadata;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Renegade implements ModInitializer {
	public static final String MOD_ID = "renegade";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public void onInitialize() {
        RenegadeRegistries.init();
        RenegadeItemGroups.init();
        RenegadeComponentTypes.init();
        RenegadeBlockEntityTypes.init();
        RenegadeItems.init();
        RenegadeBlocks.init();

        RenegadeNetworking.registerTypes();
        RenegadeNetworking.registerC2SPackets();

        CommandRegistrationCallback.EVENT.register(new RenegadeCommand());

		LOGGER.info("RUN FOR THE FUTURE");

        try {
            GameInit.bootstrap();
        } catch (Exception e) {
            LOGGER.info("Unable to bootstrap Game instance!");
        }

        createALibCompat();
	}

    private void createALibCompat() {
        ALib.registerModMenu(MOD_ID, 0xFFff0000);
    }

    public static Identifier id(String path) {
        return Identifier.of(MOD_ID, path);
    }

    public static ModMetadata getModData() {
        return FabricLoader.getInstance().getModContainer(MOD_ID).get().getMetadata();
    }
}