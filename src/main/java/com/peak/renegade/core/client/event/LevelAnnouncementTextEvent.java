package com.peak.renegade.core.client.event;

import com.peak.renegade.api.game.scene.ClientScene;
import com.peak.renegade.core.cca.core.HudInstance;
import com.peak.renegade.core.cca.core.PlayerInstance;
import com.peak.renegade.core.cca.core.WorldInstance;
import com.peak.renegade.core.networking.c2s.BeginCountdownPayload;
import com.peak.renegade.game.index.GameLayers;
import com.peak.renegade.game.index.GameLevels;
import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElement;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.text.Text;

/**
 * @author Chemthunder
 */
public class LevelAnnouncementTextEvent implements HudElement {
    public void render(DrawContext context, RenderTickCounter renderTickCounter) {
        HudInstance instance = HudInstance.getInstance(MinecraftClient.getInstance().player);
        WorldInstance world = WorldInstance.getInstance(MinecraftClient.getInstance().world);
        PlayerInstance player = PlayerInstance.getInstance(MinecraftClient.getInstance().player);

        Text layer = Text.translatable(GameLayers.createTranslationKey(world.getCurrentLayer()));
        Text level = Text.translatable(GameLevels.createTranslationKey(world.getCurrentLevel()));

        Text combined = layer.copy().append(Text.translatable("renegade.lvlannounce.spacer")).append(level);


        if (instance.getScene() == ClientScene.LEVEL_ANNOUNCE) {
            int age = instance.getAge();

            if (age > 0) {
                context.getMatrices().pushMatrix();

                context.getMatrices().translate(-(float) context.getScaledWindowWidth() / 2, -110);
                context.getMatrices().scale(2.0f);

                context.drawCenteredTextWithShadow(
                        MinecraftClient.getInstance().textRenderer,
                        combined,
                        context.getScaledWindowWidth() / 2,
                        context.getScaledWindowHeight() / 2 - 40,
                        world.getCurrentLayer().color()
                );

                context.getMatrices().popMatrix();
            }

            if (age >= 100) {
                context.drawCenteredTextWithShadow(
                        MinecraftClient.getInstance().textRenderer,
                        Text.translatable("renegade.lvlannounce.clock"),
                        context.getScaledWindowWidth() / 2,
                        context.getScaledWindowHeight() / 2 - 10,
                        world.getCurrentLayer().color()
                );
            }

            if (age >= 150) {
                context.drawCenteredTextWithShadow(
                        MinecraftClient.getInstance().textRenderer,
                        Text.translatable("renegade.slogan.0"),
                        context.getScaledWindowWidth() / 2,
                        context.getScaledWindowHeight() / 2 + 10,
                        world.getCurrentLayer().color()
                );
            }

            if (age >= 175) {
                context.drawCenteredTextWithShadow(
                        MinecraftClient.getInstance().textRenderer,
                        Text.translatable("renegade.slogan.1"),
                        context.getScaledWindowWidth() / 2,
                        context.getScaledWindowHeight() / 2 + 30,
                        world.getCurrentLayer().color()
                );
            }

            if (age >= 200) {
                context.drawCenteredTextWithShadow(
                        MinecraftClient.getInstance().textRenderer,
                        Text.translatable("renegade.slogan.2"),
                        context.getScaledWindowWidth() / 2,
                        context.getScaledWindowHeight() / 2 + 50,
                        world.getCurrentLayer().color()
                );
            }

            if (age >= 250) {
                instance.end(ClientScene.PLAYING);
                BeginCountdownPayload.send();
            }
        }
    }
}
