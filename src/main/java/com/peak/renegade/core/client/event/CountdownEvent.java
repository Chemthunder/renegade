package com.peak.renegade.core.client.event;

import com.peak.renegade.api.game.scene.ClientScene;
import com.peak.renegade.api.utility.RenegadeInstance;
import com.peak.renegade.core.cca.core.HudInstance;
import com.peak.renegade.core.cca.core.PlayerInstance;
import com.peak.renegade.core.cca.core.WorldInstance;
import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElement;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.gui.screen.ingame.StatusEffectsDisplay;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.util.StringHelper;

/**
 * @author Chemthunder
 */
public class CountdownEvent implements HudElement {
    public void render(DrawContext context, RenderTickCounter tickCounter) {
        RenegadeInstance instance = new RenegadeInstance(MinecraftClient.getInstance().player);
        PlayerInstance player = instance.player();
        WorldInstance world = instance.world();
        HudInstance hud = instance.hud();

        if (hud.getScene() == ClientScene.PLAYING) {
            hud.setTickStatus(false);

            context.drawCenteredTextWithShadow(
                    MinecraftClient.getInstance().textRenderer,
                    StringHelper.formatTicks(
                            player.getCountdown(),
                            MinecraftClient.getInstance().world.getTickManager().getTickRate()
                    ),
                    context.getScaledWindowWidth() / 2,
                    10,
                    0xFFffffff
            );
        }
    }
}
