package com.peak.renegade.api.item;

import com.peak.renegade.core.client.screen.RenegadeTitleScreen;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

/**
 * @author Chemthunder
 */
public class RenegadeUtilItem extends Item {
    public RenegadeUtilItem(Settings settings) {
        super(settings);
    }

    public ActionResult use(World world, PlayerEntity user, Hand hand) {
        if (world.isClient()) {
            MinecraftClient.getInstance().setScreen(new RenegadeTitleScreen());
        }

        return super.use(world, user, hand);
    }
}
