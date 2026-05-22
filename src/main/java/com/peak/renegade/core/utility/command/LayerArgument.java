package com.peak.renegade.core.utility.command;

import com.peak.renegade.api.game.level.GameLayer;
import net.minecraft.registry.entry.RegistryEntry;

/**
 * @author Chemthunder
 */
public class LayerArgument {
    private final RegistryEntry<GameLayer> event;

    public LayerArgument(RegistryEntry<GameLayer> event) {
        this.event = event;
    }

    public GameLayer getEvent() {
        return this.event.value();
    }

    private String getIdString() {
        return this.event.getKey().map(key -> key.getValue().toString()).orElseGet(() -> "unknown[" + this.event + "]");
    }
}
