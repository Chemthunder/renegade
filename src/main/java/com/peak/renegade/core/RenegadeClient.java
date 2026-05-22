package com.peak.renegade.core;

import com.peak.renegade.core.client.event.LevelAnnouncementTextEvent;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElementRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.hud.VanillaHudElements;
import net.minecraft.client.MinecraftClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.peak.renegade.core.Renegade.MOD_ID;

/**
 * @author Chemthunder
 */
@Environment(EnvType.CLIENT)
public class RenegadeClient implements ClientModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID + "-client");

    public void onInitializeClient() {
        LOGGER.info("Init completed.");

        HudElementRegistry.attachElementAfter(
                VanillaHudElements.INFO_BAR,
                Renegade.id("level_announcement_text"),
                new LevelAnnouncementTextEvent()
        );
    }

    public static boolean isGameMember() {
        return !MinecraftClient.getInstance().player.isCreative();
    }
}
