package com.peak.renegade.core.manager;

import com.peak.renegade.core.cca.alternative.ConfigComponent;
import com.peak.renegade.core.index.RenegadeComponentTypes;
import com.peak.renegade.core.index.tag.RenegadeItemTags;
import com.peak.renegade.core.index.util.RenegadeColors;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gl.RenderPipelines;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;

/**
 * @author Chemthunder
 */
public class RenegadeContext {

    public static void drawItemVariants(DrawContext context, TextRenderer renderer, int x, int y, ItemStack stack) {
        PlayerEntity player = MinecraftClient.getInstance().player;
        if (player == null) return;

        if (stack.isIn(RenegadeItemTags.WEAPONS)) {
            int mx = x + 2;
            int my = y + 1;

            int start = getStart(stack, player);
            int end = getEnd(stack, player);

            context.fillGradient(
                    mx,
                    my,
                    mx + 2,
                    my + 2,
                    start,
                    end
            );
        }
    }

    private static int getStart(ItemStack stack, PlayerEntity player) {
        int current = stack.getOrDefault(RenegadeComponentTypes.WEAPON_VARIATION, 0);
        ConfigComponent c = ConfigComponent.KEY.get(player);

        return switch (current) {
            case (0) -> c.weaponVariation1Start;
            case (1) -> c.weaponVariation2Start;
            default -> 0;
        };
    }

    private static int getEnd(ItemStack stack, PlayerEntity player) {
        int current = stack.getOrDefault(RenegadeComponentTypes.WEAPON_VARIATION, 0);
        ConfigComponent c = ConfigComponent.KEY.get(player);

        return switch (current) {
            case (0) -> c.weaponVariation1End;
            case (1) -> c.weaponVariation2End;
            default -> 0;
        };
    }

    public static void renderTargetIndicator(DrawContext context, Entity target) {
        int x = context.getScaledWindowWidth() / 2 - (16 / 2);
        int y = context.getScaledWindowHeight() / 2 + 10;

        context.fill(
                x,
                y,
                x + 15,
                y + 1,
                0xFFffffff
        );
    }
}
