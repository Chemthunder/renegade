package com.peak.renegade.core.manager;

import com.peak.renegade.core.Renegade;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

/**
 * @author Chemthunder
 */
public class RenegadeCrosshairFetcher {
    public static Identifier fetch(PlayerEntity player, Identifier original) {
        ItemStack stack = player.getStackInHand(player.getActiveHand());

        if (stack.isEmpty()) {
            return Renegade.id("crosshairs/weaponless_crosshair");
        }

        return original;
    }
}
