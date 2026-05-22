package com.peak.renegade.core;

import com.peak.renegade.core.command.RenegadeCommand;
import com.peak.renegade.core.index.RenegadeRegistries;
import com.peak.renegade.game.GameInit;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Renegade implements ModInitializer {
	public static final String MOD_ID = "renegade";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public void onInitialize() {
        RenegadeRegistries.init();

        CommandRegistrationCallback.EVENT.register(new RenegadeCommand());

		LOGGER.info("RUN FOR THE FUTURE");

        try {
            GameInit.bootstrap();
        } catch (Exception e) {
            LOGGER.info(e.getLocalizedMessage());
        }
	}

    public static Identifier id(String path) {
        return Identifier.of(MOD_ID, path);
    }
}