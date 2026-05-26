package com.peak.renegade.api.utility;

import com.peak.renegade.core.cca.core.HudInstance;
import com.peak.renegade.core.cca.core.PlayerInstance;
import com.peak.renegade.core.cca.core.WorldInstance;
import net.minecraft.entity.player.PlayerEntity;

/**
 * @author Chemthunder
 * A way to get the World, Hud, and Player instances from one variable
 */
public class RenegadeInstance {
    private final PlayerEntity player;

    public RenegadeInstance(PlayerEntity player) {
        this.player = player;
    }

    public PlayerEntity self() {
        return this.player;
    }

    public PlayerInstance player() {
        return PlayerInstance.getInstance(player);
    }

    public WorldInstance world() {
        return WorldInstance.getInstance(player.getEntityWorld());
    }

    public HudInstance hud() {
        return HudInstance.getInstance(player);
    }
}
